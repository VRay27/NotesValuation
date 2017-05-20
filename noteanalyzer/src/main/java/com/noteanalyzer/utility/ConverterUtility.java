package com.noteanalyzer.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.noteanalyzer.entity.user.User;
import com.noteanalyzer.mvc.model.UserModel;

import lombok.NonNull;

public class ConverterUtility {

	public static User convertUserModelToUserEntity(@NonNull UserModel userModel) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user = new User();
		user.setFirstName(userModel.getUserName());
		user.setUserName(userModel.getEmail());
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
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
		userModel.setUserName(user.getUserName());
		userModel.setUserId(user.getUserId());
		userModel.setPassword(user.getPassword());
		userModel.setEmail(user.getEmailID());
		userModel.setResetToken(user.getResetToken());
		return userModel;
	}

}
