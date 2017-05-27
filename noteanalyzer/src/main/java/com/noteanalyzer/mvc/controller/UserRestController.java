package com.noteanalyzer.mvc.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.noteanalyzer.mvc.model.UserModel;
import com.noteanalyzer.mvc.service.EmailService;
import com.noteanalyzer.mvc.service.UserService;
import com.noteanalyzer.security.security.auth.ajax.LoginRequest;
import com.noteanalyzer.utility.NoteUtility;

@RestController
public class UserRestController {

	@Autowired
	UserService userService; // Service which will do all data
								// retrieval/manipulation work

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
	public ResponseEntity<Void> createUser(@RequestBody UserModel inputUser) {
		System.out.println("Creating User " + inputUser);

		if (userService.getByUsername(inputUser.getEmail()).isPresent()) {
			System.out.println("A User with name " + inputUser.getEmail() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.createUser(inputUser);
		HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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
		String subject = "Reset Password Link For Notes Analyzer";
		String bodyText = "<p>Please use below link to reset your password for Note Analyzer.This token will expire in next 24 hours.</p><p>"
				+ baseUrl + "/notes/#!/changePassword?resetToken=" + resetToken + "</p>";
		if (EmailService.sendEmail(inputUser.getUsername(), subject, bodyText)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

	}

}