package com.noteanalyzer.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.noteanalyzer.entity.notes.NoteConfiguration;
import com.noteanalyzer.mvc.model.UserModel;
import com.noteanalyzer.mvc.service.EmailService;
import com.noteanalyzer.mvc.service.NoteService;
import com.noteanalyzer.mvc.service.UserService;
import com.noteanalyzer.security.security.auth.ajax.LoginRequest;
import com.noteanalyzer.utility.NoteUtility;

@RestController
public class UserRestController {

	@Autowired
	UserService userService; // Service which will do all data
								// retrieval/manipulation work
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	NoteService noteService;

	// -------------------Retrieve Single
	// User--------------------------------------------------------

	@RequestMapping(value = "api/userDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserModel> getUserDetail() {
		String userName = NoteUtility.getLoggedInUserName();
		System.out.println("Fetching User with userName " + userName);
		Optional<UserModel> user = userService.getByUsername(userName);
		if (user.isPresent()) {
			return new ResponseEntity<UserModel>(user.get(), HttpStatus.OK);
		}
		System.out.println("User with userName " + userName + " not found");
		return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
	}

	// -------------------Create a
	// User--------------------------------------------------------

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody UserModel inputUser, HttpServletRequest request) {
		System.out.println("Creating User " + inputUser);
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		String baseUrl = StringUtils.substringBefore(url.toString(), uri);
		
		if (userService.getByUsername(inputUser.getEmail()).isPresent()) {
			System.out.println("A User with name " + inputUser.getEmail() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		String verificationToken = NoteUtility.encodeResetToken(inputUser.getEmail(), RandomStringUtils.randomAlphanumeric(40).toUpperCase());
		inputUser.setVerificationToken(verificationToken);
		userService.createUser(inputUser);
		List<String> configCodeList = new ArrayList<>();
		configCodeList.add("CREATE_USER_EMAIL_SUBJECT");
		configCodeList.add("CREATE_USER_EMAIL_CONTENT_BODY");
		List<NoteConfiguration> configList = noteService.getConfigValue(configCodeList);
		String subject = null;
		String bodyText = null;
		for(NoteConfiguration config: configList){
			 if("CREATE_USER_EMAIL_SUBJECT".equals(config.getConfigCode())){
				 subject = config.getConfigValue();
			}else if("CREATE_USER_EMAIL_CONTENT_BODY".equals(config.getConfigCode())){
				bodyText = config.getConfigValue();
			}
		}
		bodyText = bodyText+"<p>"
				+ baseUrl + "/notes/verifyUser?verificationToken=" + verificationToken + "</p>";
		if (emailService.sendEmail(inputUser.getEmail(), subject, bodyText)) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<Void> changePassword(@RequestBody UserModel inputUser, HttpServletRequest request) {
		System.out.println("Creating User " + inputUser);

		String resetToken = request.getParameter("resetToken");
		inputUser.setEmail(NoteUtility.getUserNameFromResetToken(resetToken));
		inputUser.setResetToken(NoteUtility.decodeResetToken(resetToken));
		Optional<UserModel> user = userService.changePassword(inputUser,false);
		if (user.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}
	

	@RequestMapping(value = "/api/changePassword", method = RequestMethod.POST)
	public ResponseEntity<Void> changePasswordForLoginUser(@RequestBody UserModel inputUser) {
		System.out.println("Creating User " + inputUser);
		inputUser.setEmail( NoteUtility.getLoggedInUserName());
		inputUser.setResetToken(null);
		Optional<UserModel> user = userService.changePassword(inputUser,true);
		if (user.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "api/updateUser", method = RequestMethod.POST)
	public ResponseEntity<UserModel> updateUser(@RequestBody UserModel inputUser) {
		System.out.println("Updating User " + inputUser);
		Optional<UserModel> user = userService.updateUser(inputUser);

		if (user.isPresent()) {
			return new ResponseEntity<UserModel>(user.get(), HttpStatus.OK);
		}
		return new ResponseEntity<UserModel>(user.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
	public ResponseEntity<Void> resetPassword(@RequestBody LoginRequest inputUser, HttpServletRequest request) {
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
		List<String> configCodeList = new ArrayList<>();
		configCodeList.add("FORGOT_PASSWORD_EMAIL_SUBJECT");
		configCodeList.add("FORGOT_PASSWORD_EMAIL_CONTENT_BODY");
		List<NoteConfiguration> configList = noteService.getConfigValue(configCodeList);
		String subject = null;
		String bodyText = null;
		for(NoteConfiguration config: configList){
			 if("FORGOT_PASSWORD_EMAIL_SUBJECT".equals(config.getConfigCode())){
				 subject = config.getConfigValue();
			}else if("FORGOT_PASSWORD_EMAIL_CONTENT_BODY".equals(config.getConfigCode())){
				bodyText = config.getConfigValue();
			}
		}
		bodyText = bodyText+"<p>"
				+ baseUrl + "/notes/#!/changePassword?resetToken=" + resetToken + "</p>";
		if (emailService.sendEmail(inputUser.getUsername(), subject, bodyText)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

	}

}