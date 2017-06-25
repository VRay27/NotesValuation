package com.noteanalyzer.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.noteanalyzer.entity.AbstractEntity;
import com.noteanalyzer.entity.notes.Note;
import com.noteanalyzer.entity.notes.Property;
import com.noteanalyzer.mvc.model.NoteInputFormModel;
import com.noteanalyzer.security.security.model.UserContext;

public class NoteUtility {

	protected static final String RESET_TOKEN_SEP = "!#$^";
	
	 public static File convert(MultipartFile file) throws IOException
	  {    
	      File convFile = new File(file.getOriginalFilename());
	      convFile.createNewFile(); 
	      FileOutputStream fos = new FileOutputStream(convFile); 
	      fos.write(file.getBytes());
	      fos.close(); 
	      return convFile;
	  }
	 
	 public static String encodeResetToken(String userName,String token){
	       return Base64.getEncoder().encodeToString((userName+RESET_TOKEN_SEP+token).getBytes());
	 }
	 
	 public static String getUserNameFromResetToken(String token){
	       String tokenStr =  new String(Base64.getDecoder().decode(token));
	       return StringUtils.substringBefore(tokenStr, RESET_TOKEN_SEP);
	 }
	 
	 public static String decodeResetToken(String token){
	       String tokenStr =  new String(Base64.getDecoder().decode(token));
	       return StringUtils.substringAfter(tokenStr, RESET_TOKEN_SEP);
	 }

	
	public static String getLoggedInUserName(){
	     Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	     String userName;
	        if (principal instanceof UserContext) {
	        	userName = ((UserContext)principal).getUsername();
	        } else {
	        	userName = principal.toString();
	        }
	      return userName;  
	}
	
	
}
