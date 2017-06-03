package com.noteanalyzer.mvc.service;

import java.util.List;
import java.util.Optional;

import com.noteanalyzer.mvc.model.NoteInputFormModel;
import com.noteanalyzer.mvc.model.NoteSummaryModel;
import com.noteanalyzer.mvc.model.NoteTypeModel;
import com.noteanalyzer.mvc.model.PropertyTypeModel;

import lombok.NonNull;



public interface NoteService {
	public void createNote(@NonNull NoteInputFormModel note);
	
	public Optional<List<NoteTypeModel>> getNoteType();
	
	public Optional<List<PropertyTypeModel>> getPropertyType();
	
	public Optional<NoteInputFormModel> getNoteDetail(@NonNull int noteId);

	public Optional<List<NoteSummaryModel>> getAllNotes(@NonNull String loggedInUserName);
	
	/*User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);*/
	
}
