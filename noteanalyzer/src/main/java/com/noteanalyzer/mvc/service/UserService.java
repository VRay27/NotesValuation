package com.noteanalyzer.mvc.service;

import java.util.Optional;

import com.noteanalyzer.mvc.model.UserModel;



public interface UserService {
	public void createUser(UserModel user);
	public Optional<UserModel> getByUsername(String userName);
	public Optional<UserModel> resetUserPassword(String userName);
	public Optional<UserModel> updateUser(UserModel user);
	Optional<UserModel> changePassword(UserModel inputUser, boolean isResetTokenRequired);
	public Optional<UserModel> verifyUser(UserModel inputUser);
	
	/*User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);*/
	
}
