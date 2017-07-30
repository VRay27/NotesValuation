package com.noteanalyzer.mvc.service;

import java.util.List;
import java.util.Optional;

import com.noteanalyzer.mvc.model.AddressModel;
import com.noteanalyzer.mvc.model.UserModel;



public interface UserService {
	public void createUser(UserModel user);
	public Optional<UserModel> getByUsername(String userName);
	public Optional<UserModel> resetUserPassword(String userName);
	public Optional<UserModel> updateUser(UserModel user);
	Optional<UserModel> changePassword(UserModel inputUser, boolean isResetTokenRequired);
	public Optional<UserModel> verifyUser(UserModel inputUser);
	public String getUserStatus(String userName);
	void updateUnsuccessfullAttempt(String loginStatus, long userId, String userEmailId);
	
	
}
