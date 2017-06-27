package com.noteanalyzer.mvc.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.noteanalyzer.mvc.model.UserModel;
import com.noteanalyzer.mvc.service.UserService;
import com.noteanalyzer.utility.NoteUtility;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	UserService userService; 
	
	  @RequestMapping(method = RequestMethod.GET)
	    public String getIndexPage() {
		  System.out.println("Inside Arvind IndexController Code");
	        return "welcome";
	    }
	  
	  @RequestMapping(value = "/verifyUser", method = RequestMethod.GET)
		public String verifyUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
			UserModel inputUser = new UserModel();
			String verificationToken = request.getParameter("verificationToken");
			inputUser.setEmail(NoteUtility.getUserNameFromResetToken(verificationToken));
			inputUser.setVerificationToken(verificationToken);
			Optional<UserModel> user = userService.verifyUser(inputUser);
			if (!user.isPresent()) {
				return "genericError";
			}
			return "welcome";
		}

}