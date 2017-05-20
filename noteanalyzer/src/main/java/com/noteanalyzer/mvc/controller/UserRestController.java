package com.noteanalyzer.mvc.controller;
 
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.noteanalyzer.mvc.model.UserModel;
import com.noteanalyzer.mvc.service.EmailUtility;
import com.noteanalyzer.mvc.service.UserService;
import com.noteanalyzer.security.security.auth.ajax.LoginRequest;
 
@RestController
public class UserRestController {
 
    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work
 
    
 
    
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/userDetail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> getUserDetail(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
      //  User user = userService.findById(id);
        UserModel user = new UserModel();
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserModel>(user, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a User--------------------------------------------------------
     
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
 
    
     
     
    @RequestMapping(value = "api/updateUser", method = RequestMethod.PUT)
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user) {
        System.out.println("Updating User " + user);
        UserModel currentUser  = new UserModel();
       // User currentUser = userService.findById(id);
     /*   User currentUser  = new User();
        if (currentUser==null) {
           // System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 */
       // currentUser.setUsername(user.getUsername());
        //currentUser.setEmail(user.getEmail());
         
       // userService.updateUser(currentUser);
        return new ResponseEntity<UserModel>(currentUser, HttpStatus.OK);
    }
 
    
   @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
   public ResponseEntity<Void> resetPassword(@RequestBody LoginRequest inputUser,HttpServletRequest request) {
       System.out.println("Reset Password " + inputUser+" request url "+request.getRequestURL()+" context url "+request.getContextPath());
       StringBuffer url = request.getRequestURL();
       String uri = request.getRequestURI();
       String baseUrl = StringUtils.substringBefore(url.toString(), uri);
       
       
       Optional<UserModel> userModel = userService.resetUserPassword(inputUser.getUsername());
       if (!userModel.isPresent()) {
           System.out.println("No User exist with " + inputUser.getUsername());
           return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
       }
       
       String subject = "Reset Password Link For Notes Analyzer";
       String bodyText  = "<p>Please use below link to reset your password for Note Analyzer.This token will expire in next 24 hours.</p><p>"+baseUrl+"/notes/forgetPassword?"+userModel.get().getResetToken()+"</p>";
       if(EmailUtility.sendEmail(inputUser.getUsername(), subject, bodyText)){
    	   return new ResponseEntity<Void>(HttpStatus.OK);   
       }else{
    	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
       }
       
   }
    
}