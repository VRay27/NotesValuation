package com.noteanalyzer.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.noteanalyzer.entity.user.User;
import com.noteanalyzer.mvc.model.UserModel;

import lombok.NonNull;

public class ConverterUtility {

	public static User convertUserModelToUserEntity(@NonNull UserModel userModel,BCryptPasswordEncoder encoder) {
		
		User user = new User();
		user.setFirstName(userModel.getDisplayName());
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
		userModel.setDisplayName(user.getFirstName());
		userModel.setUserId(user.getUserId());
		userModel.setPhoneNumber(user.getContactNumber());
		userModel.setEmail(user.getEmailID());
		userModel.setResetToken(user.getResetToken());
		return userModel;
	}
	
}
