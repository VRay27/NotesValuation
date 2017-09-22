package com.noteanalyzer.mvc.controller;

import static com.noteanalyzer.mvc.constant.NoteConstant.ACTIVE_USER_FLAG;
import static com.noteanalyzer.mvc.constant.NoteConstant.BLOCK_USER_FLAG;
import static com.noteanalyzer.mvc.constant.NoteConstant.CREATE_USER_EMAIL_CONTENT_BODY;
import static com.noteanalyzer.mvc.constant.NoteConstant.CREATE_USER_EMAIL_SUBJECT;
import static com.noteanalyzer.mvc.constant.NoteConstant.FORGOT_PASSWORD_EMAIL_CONTENT_BODY;
import static com.noteanalyzer.mvc.constant.NoteConstant.FORGOT_PASSWORD_EMAIL_SUBJECT;
import static com.noteanalyzer.mvc.constant.NoteConstant.IN_ACTIVE_USER_FLAG;

import java.util.Optional;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.noteanalyzer.mvc.model.AddressModel;
import com.noteanalyzer.mvc.model.UserModel;
import com.noteanalyzer.mvc.service.EmailService;
import com.noteanalyzer.mvc.service.NoteService;
import com.noteanalyzer.mvc.service.UserService;
import com.noteanalyzer.security.security.auth.ajax.LoginRequest;
import com.noteanalyzer.utility.NoteUtility;

/**
 * This class is responsible for all communication with UI for user related
 * activity. This is restful webservice can be called from any UI or third party
 * as and when needed.
 * 
 * @author Arvind Ray
 *
 */
@RestController
public class UserRestController {

	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;

	@Autowired
	NoteService noteService;

	private final static Logger LOG = Logger.getLogger(UserRestController.class.getName());


	
	/**
	 * This method will return the user details of logged in user. User name
	 * will fetched from the token passed by the browser.
	 * 
	 * @return
	 */
	@RequestMapping(value = "api/userDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserModel> getUserDetail() {
		String userName = NoteUtility.getLoggedInUserName();
		LOG.info("Fetching User with userName " + userName);
		Optional<UserModel> user = userService.getByUsername(userName);
		if (user.isPresent()) {
			UserModel userModel = user.get();
			if (StringUtils.isNotBlank(userModel.getZipCode())) {
				userModel.setAddressModel(noteService.getZipCodeDetails(userModel.getZipCode()).get());
			}
			return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
		}
		LOG.info("User with userName " + userName + " not found");
		return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
	}

	/**
	 * This method will crete an user with given input.
	 * 
	 * @param inputUser
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody UserModel inputUser, HttpServletRequest request) {
		LOG.info("Creating User " + inputUser);
		String userStatus = userService.getUserStatus(inputUser.getEmail());
		if (StringUtils.isNotBlank(userStatus)) {
			if (ACTIVE_USER_FLAG.equalsIgnoreCase(userStatus)) {
				LOG.info("A User with name " + inputUser.getEmail() + " already exist. ");
				return new ResponseEntity<Void>(HttpStatus.FOUND);
			} else if (IN_ACTIVE_USER_FLAG.equalsIgnoreCase(userStatus)) {
				LOG.info("A User with name " + inputUser.getEmail() + "registered with us, but not yet verified.");
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			} else if (BLOCK_USER_FLAG.equalsIgnoreCase(userStatus)) {
				LOG.info("A User with name " + inputUser.getEmail() + "registered with us, but in blocked state.");
				return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
			} else {
				LOG.info("A User with name " + inputUser.getEmail() + "have corrupted Data in user status column");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}

		String baseUrl = StringUtils.substringBefore(request.getRequestURL().toString(), request.getRequestURI());
		String verificationToken = NoteUtility.encodeResetToken(inputUser.getEmail(),
				RandomStringUtils.randomAlphanumeric(40).toUpperCase());
		inputUser.setVerificationToken(verificationToken);
		userService.createUser(inputUser);
		String subject = noteService.getParameterValue(CREATE_USER_EMAIL_SUBJECT, null).getParameterValue();
		String bodyText = noteService.getParameterValue(CREATE_USER_EMAIL_CONTENT_BODY, null).getParameterValue();
		String verificationLink = baseUrl + "/notes/verifyUser?verificationToken=" + verificationToken;
		bodyText = bodyText + "<p><a class='btn btn-success' href='" + verificationLink + "'>Verify User</a></p> Or <p>"
				+ " Click on the link: " + verificationLink + "</p>";
		if (emailService.sendEmail(inputUser.getEmail(), subject, bodyText)) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * This method will used for reset the password of non- logged in user.
	 * 
	 * @param inputUser
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<UserModel> resetPassword(@RequestBody UserModel inputUser, HttpServletRequest request) {
		LOG.info("Reset password for  " + inputUser);
		String resetToken = request.getParameter("resetToken");
		String emailId = NoteUtility.getUserNameFromResetToken(resetToken);
		String decodedResetToken = NoteUtility.decodeResetToken(resetToken);
		if (StringUtils.isBlank(resetToken) || StringUtils.isBlank(emailId) || StringUtils.isBlank(decodedResetToken)) {
			return new ResponseEntity<UserModel>(HttpStatus.BAD_REQUEST);
		}
		inputUser.setEmail(emailId);
		inputUser.setResetToken(decodedResetToken);
		Optional<UserModel> user = userService.changePassword(inputUser);
		if (user.isPresent()) {
			String statusCode = user.get().getErrorCode();
			if ("ResetTokenExpired".equalsIgnoreCase(statusCode)) {
				return new ResponseEntity<UserModel>(HttpStatus.FOUND);
			}
			return new ResponseEntity<UserModel>(HttpStatus.OK);
		} else {
			return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * This method will be used to change the password for logged in user.
	 * 
	 * @param inputUser
	 * @return
	 */
	@RequestMapping(value = "/api/changePassword", method = RequestMethod.POST)
	public ResponseEntity<UserModel> changePasswordForLoginUser(@RequestBody UserModel inputUser) {
		LOG.info("change password for  " + inputUser);
		String loggedInUser = NoteUtility.getLoggedInUserName();
		if (StringUtils.isBlank(loggedInUser)) {
			return new ResponseEntity<UserModel>(HttpStatus.FORBIDDEN);
		}
		inputUser.setEmail(loggedInUser);
		Optional<UserModel> user = userService.changePasswordForLoginUser(inputUser);
		if (user.isPresent()) {
			return new ResponseEntity<UserModel>(HttpStatus.OK);
		} else {
			return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * This method will be used to update the user details.
	 * 
	 * @param inputUser
	 * @return
	 */
	@RequestMapping(value = "/api/updateUser", method = RequestMethod.POST)
	public ResponseEntity<UserModel> updateUser(@RequestBody UserModel inputUser) {
		System.out.println("Updating User " + inputUser);
		Optional<UserModel> user = userService.updateUser(inputUser);

		if (user.isPresent()) {
			return new ResponseEntity<UserModel>(user.get(), HttpStatus.OK);
		}
		return new ResponseEntity<UserModel>(user.get(), HttpStatus.OK);
	}

	/**
	 * This method wil be use to send reset link password.
	 * 
	 * @param inputUser
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
	public ResponseEntity<Void> resetLinkPassword(@RequestBody LoginRequest inputUser, HttpServletRequest request) {
		System.out.println("Reset Password " + inputUser + " request url " + request.getRequestURL() + " context url "
				+ request.getContextPath());
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		String baseUrl = StringUtils.substringBefore(url.toString(), uri);

		Optional<UserModel> userModel = userService.resetUserPassword(inputUser.getUsername());
		if (!userModel.isPresent()) {
			System.out.println("No User exist with " + inputUser.getUsername());
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		String resetToken = NoteUtility.encodeResetToken(inputUser.getUsername(), userModel.get().getResetToken());
		String subject = noteService.getParameterValue(FORGOT_PASSWORD_EMAIL_SUBJECT, null).getParameterValue();
		String bodyText = noteService.getParameterValue(FORGOT_PASSWORD_EMAIL_CONTENT_BODY, null).getParameterValue();
		String resetPasswordLink = baseUrl + "/notes/#!/changePassword?resetToken=" + resetToken;
		bodyText = bodyText + "<p> <a class = 'btn btn-success' href=" + resetPasswordLink
				+ "> Reset Password</a></p><p>" + "Or Please click on this link: " + resetPasswordLink + "</p>";
		if (emailService.sendEmail(inputUser.getUsername(), subject, bodyText)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

	}

	/**
	 * This method will send the details of city and state for given zip code.
	 * 
	 * @param zipCode
	 * @return
	 */
	@RequestMapping(value = "getZipCodeDetails/{zipCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressModel> getZipCodeDetails(@PathVariable String zipCode) {
		LOG.info("Fetching Zip Code details with zipCode " + zipCode);
		Optional<AddressModel> address = noteService.getZipCodeDetails(zipCode);
		if (address.isPresent()) {
			return new ResponseEntity<AddressModel>(address.get(), HttpStatus.OK);
		}
		LOG.info("ZipCode with userName " + address + " not found");
		return new ResponseEntity<AddressModel>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * This method will send the details of city and state for given zip code.
	 * 
	 * @param zipCode
	 * @return
	 */
	@RequestMapping(value = "api/updateSubscription", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserModel> updateSubscription() {
		
		Optional<UserModel> userModel = userService.updateUserSubscription("P1");
		if (userModel.isPresent()) {
			return new ResponseEntity<UserModel>(userModel.get(), HttpStatus.OK);
		}
		return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
	}
	

}