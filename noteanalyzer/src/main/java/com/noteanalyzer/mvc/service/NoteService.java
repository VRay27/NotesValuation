package com.noteanalyzer.mvc.service;

import java.util.Optional;

import com.noteanalyzer.mvc.model.NoteInputFormModel;
import com.noteanalyzer.mvc.model.UserModel;



public interface NoteService {
	public void createNote(NoteInputFormModel note);
	
	/*User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);*/
	
}
