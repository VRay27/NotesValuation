package com.noteanalyzer.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
	 
	 public static String encodeResetToken(String userName,String resetToken){
	       return Base64.getEncoder().encodeToString((userName+RESET_TOKEN_SEP+resetToken).getBytes());
	 }
	 
	 public static String getUserNameFromResetToken(String resetToken){
	       String resetTokenStr =  new String(Base64.getDecoder().decode(resetToken));
	       return StringUtils.substringBefore(resetTokenStr, RESET_TOKEN_SEP);
	 }
	 
	 public static String decodeResetToken(String resetToken){
	       String resetTokenStr =  new String(Base64.getDecoder().decode(resetToken));
	       return StringUtils.substringAfter(resetTokenStr, RESET_TOKEN_SEP);
	 }
}
