package com.noteanalyzer.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.noteanalyzer.entity.AbstractEntity;
import com.noteanalyzer.entity.notes.Note;
import com.noteanalyzer.entity.notes.NoteType;
import com.noteanalyzer.entity.notes.Property;
import com.noteanalyzer.entity.notes.PropertyType;
import com.noteanalyzer.entity.user.User;
import com.noteanalyzer.mvc.model.NoteInputFormModel;
import com.noteanalyzer.mvc.model.NoteTypeModel;
import com.noteanalyzer.mvc.model.PropertyTypeModel;
import com.noteanalyzer.mvc.model.UserModel;

import lombok.NonNull;

public class ConverterUtility {

	public static User convertUserModelToUserEntity(@NonNull UserModel userModel,BCryptPasswordEncoder encoder) {
		
		User user = new User();
		user.setDisplayName(userModel.getDisplayName());
		user.setUserName(userModel.getEmail());
		user.setPassword(encoder.encode(userModel.getPassword()));
		user.setEmailID(userModel.getEmail());
		user.setContactNumber(userModel.getPhoneNumber());
		if (userModel.getAddress() != null) {
			user.setAddress(userModel.getAddress().getStreetAddress());
			user.setCity(userModel.getAddress().getCity());
			user.setState(userModel.getAddress().getState());
		}

		return user;
	}

	public static UserModel convertUserToUserModel(@NonNull User user) {
		UserModel userModel = new UserModel();
		userModel.setDisplayName(user.getDisplayName());
		userModel.setUserId(user.getUserId());
		userModel.setPhoneNumber(user.getContactNumber());
		userModel.setEmail(user.getEmailID());
		userModel.setResetToken(user.getResetToken());
		return userModel;
	}
	
	
	public static AbstractEntity convertNoteModelToEntity(NoteInputFormModel note) {
		Note noteEntity = new Note();
		noteEntity.setNoteType(note.getSelNoteType().getNoteTypeCode());
		noteEntity.setPropertyType(note.getSelPropType().getPropertyTypeCode());
		
		//noteEntity.setDateOfNote(LocalDate.note.getNoteDate());
		/*Property property = new Property();
		property.setCity(note.getAddress().getCity());
		property.setState(note.getAddress().getState());
		property.setStreet(note.getAddress().getStreetAddress());
		property.setZip(Integer.valueOf(note.getAddress().getZipCode()));*/
		//noteEntity.setpr(note.getSelNoteType().getNoteTypeCode());
		//property.setPropertyType(note.getSelPropType().getPropertyTypeCode());
	//	noteEntity.
				
		return noteEntity;
	}

	public static List<NoteTypeModel> convertNoteTypeEntityToModel(List<NoteType> noteTypeList) {
		List<NoteTypeModel>  noteTypeModelList = new ArrayList<>(); 
		for(NoteType noteType : noteTypeList){
			noteTypeModelList.add(new NoteTypeModel(noteType.getNoteType(),noteType.getDescription()));
		}
		return noteTypeModelList;
	}

	public static List<PropertyTypeModel> convertPropertyTypeEntityToModel(List<PropertyType> propTypeList) {
		List<PropertyTypeModel>  propTypeModelList = new ArrayList<>();
		for(PropertyType propType : propTypeList){
			propTypeModelList.add(new PropertyTypeModel(propType.getPropertyType(),propType.getDescription()));
		}
		return propTypeModelList;
		
	}
	
}
