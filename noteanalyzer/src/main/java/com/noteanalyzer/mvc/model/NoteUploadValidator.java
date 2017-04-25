package com.noteanalyzer.mvc.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NoteUploadValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

/*	 public boolean supports(Class<?> clazz) {
	        return MultiNoteBucket.class.isAssignableFrom(clazz);
	    }
	 
	    public void validate(Object obj, Errors errors) {
	        MultiNoteBucket multiBucket = (MultiNoteBucket) obj;
	         
	        int index=0;
	         
	        for(NoteBucket file : multiBucket.getFiles()){
	            if(file.getFile()!=null){
	                if (file.getFile().getSize() == 0) {
	                    errors.rejectValue("files["+index+"].file", "missing.file");
	                }
	            }
	            index++;
	        }
	         
	    }*/
}
