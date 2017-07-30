package com.noteanalyzer.mvc.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.noteanalyzer.entity.notes.NoteConfiguration;
import com.noteanalyzer.entity.notes.Parameters;
import com.noteanalyzer.mvc.model.AddressModel;
import com.noteanalyzer.mvc.model.NoteInputFormModel;
import com.noteanalyzer.mvc.model.NoteSummaryModel;
import com.noteanalyzer.mvc.model.NoteTypeModel;
import com.noteanalyzer.mvc.model.PropertyTypeModel;

import lombok.NonNull;



public interface NoteService {
	public void createNote(@NonNull NoteInputFormModel note) throws ParseException;
	
	public Optional<List<NoteTypeModel>> getNoteType();
	
	public Optional<List<PropertyTypeModel>> getPropertyType();
	
	public Optional<NoteInputFormModel> getNoteDetail(@NonNull int noteId);

	public Optional<List<NoteSummaryModel>> getAllNotes(@NonNull String loggedInUserName);
	
	public List<NoteConfiguration> getConfigValue(@NonNull List<String> configCode);

	Parameters getParameterValue(String parameterName, String userEmailId);
	public Optional<AddressModel> getZipCodeDetails(String zipCode);
	
	/*User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);*/
	
}
