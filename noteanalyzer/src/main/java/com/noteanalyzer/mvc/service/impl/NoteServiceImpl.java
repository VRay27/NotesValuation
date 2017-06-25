package com.noteanalyzer.mvc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noteanalyzer.dao.GenericDao;
import com.noteanalyzer.entity.notes.Note;
import com.noteanalyzer.entity.notes.NoteConfiguration;
import com.noteanalyzer.entity.notes.NoteType;
import com.noteanalyzer.entity.notes.PropertyType;
import com.noteanalyzer.mvc.model.NoteInputFormModel;
import com.noteanalyzer.mvc.model.NoteSummaryModel;
import com.noteanalyzer.mvc.model.NoteTypeModel;
import com.noteanalyzer.mvc.model.PropertyTypeModel;
import com.noteanalyzer.mvc.service.NoteService;
import com.noteanalyzer.utility.ConverterUtility;

import io.jsonwebtoken.lang.Collections;
import lombok.NonNull;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Autowired
	GenericDao genericDao;

	public GenericDao getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDao genericDao) {
		this.genericDao = genericDao;
	}
	
	@Override
	public void createNote(@NonNull NoteInputFormModel note) {
		genericDao.create(ConverterUtility.convertNoteModelToEntity(note));

	}

	@Override
	public Optional<List<NoteTypeModel>> getNoteType() {
		List<NoteType> noteTypeList = genericDao.getAll(NoteType.class);
		if(Collections.isEmpty(noteTypeList)){
			Optional.empty();
		}
		return Optional.of(ConverterUtility.convertNoteTypeEntityToModel(noteTypeList));
	}

	@Override
	public Optional<List<PropertyTypeModel>> getPropertyType() {
		List<PropertyType> propTypeList = genericDao.getAll(PropertyType.class);
		if(Collections.isEmpty(propTypeList)){
			Optional.empty();
		}
		return Optional.of(ConverterUtility.convertPropertyTypeEntityToModel(propTypeList));
	}

	@Override
	public Optional<NoteInputFormModel> getNoteDetail(int noteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<NoteSummaryModel>> getAllNotes(String loggedInUserName) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userName", loggedInUserName);
		genericDao.getResultByNamedQuery(Note.class, Note.GET_NOTE_DETAILS_BY_USER, parameters);
		return null;
	}

	@Override
	public List<NoteConfiguration> getConfigValue(@NonNull List<String> configCode) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("configCode", configCode);
		List<NoteConfiguration> noteConfigurationList = genericDao.getResultByNamedQuery(NoteConfiguration.class, NoteConfiguration.GET_CONFIG_VALUE, parameters);
		return noteConfigurationList;
	}

}
