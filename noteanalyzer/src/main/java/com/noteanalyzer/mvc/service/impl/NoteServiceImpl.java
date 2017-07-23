package com.noteanalyzer.mvc.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.noteanalyzer.dao.GenericDao;
import com.noteanalyzer.entity.notes.Note;
import com.noteanalyzer.entity.notes.NoteConfiguration;
import com.noteanalyzer.entity.notes.NoteType;
import com.noteanalyzer.entity.notes.Parameters;
import com.noteanalyzer.entity.notes.Property;
import com.noteanalyzer.entity.notes.PropertyType;
import com.noteanalyzer.mvc.model.NoteInputFormModel;
import com.noteanalyzer.mvc.model.NoteSummaryModel;
import com.noteanalyzer.mvc.model.NoteTypeModel;
import com.noteanalyzer.mvc.model.PropertyTypeModel;
import com.noteanalyzer.mvc.service.NoteService;
import com.noteanalyzer.utility.ConverterUtility;
import com.noteanalyzer.utility.NoteUtility;

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
	@Transactional
	public void createNote(@NonNull NoteInputFormModel noteModel) throws ParseException {
	/*	NoteAddress noteAddress = new NoteAddress();
		noteAddress.setCity(noteModel.getAddress().getCity());
		noteAddress.setCountry("US");
		noteAddress.setState(noteModel.getAddress().getState());
		noteAddress.setStreetAddress(noteModel.getAddress().getStreetAddress());
		noteAddress.setZipCode(noteModel.getAddress().getZipCode());*/

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("zipCode", Integer.valueOf(noteModel.getAddress().getZipCode()));
		parameters.put("streetAddress", noteModel.getAddress().getStreetAddress());
		parameters.put("city", noteModel.getAddress().getCity());
		parameters.put("state", noteModel.getAddress().getState());
		List<Property> propertyList = genericDao.getResultByNamedQuery(Property.class,
				Property.GET_PROPERTY_DETAILS_BY_ADDRESS, parameters);
		Property property = null;
		if (!CollectionUtils.isEmpty(propertyList)) {
			property = propertyList.get(0);
		//	noteAddress.setProperty(property);
		}
		Note note = ConverterUtility.convertNoteModelToEntity(noteModel);
		//note.setNoteAddress(noteAddress);
		genericDao.create(note);

	}

	@Override
	public Optional<List<NoteTypeModel>> getNoteType() {
		List<NoteType> noteTypeList = genericDao.getAll(NoteType.class);
		if (Collections.isEmpty(noteTypeList)) {
			Optional.empty();
		}
		return Optional.of(ConverterUtility.convertNoteTypeEntityToModel(noteTypeList));
	}

	@Override
	public Optional<List<PropertyTypeModel>> getPropertyType() {
		List<PropertyType> propTypeList = genericDao.getAll(PropertyType.class);
		if (Collections.isEmpty(propTypeList)) {
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
	@Transactional
	public Optional<List<NoteSummaryModel>> getAllNotes(String loggedInUserName) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userName", loggedInUserName);
		List<Note> noteList = genericDao.getResultByNamedQuery(Note.class, Note.GET_NOTE_DETAILS_BY_USER, parameters);
		ConverterUtility.convertNoteToNoteSummaryModel(noteList);
		return null;
	}

	@Override
	public List<NoteConfiguration> getConfigValue(@NonNull List<String> configCode) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("configCode", configCode);
		List<NoteConfiguration> noteConfigurationList = genericDao.getResultByNamedQuery(NoteConfiguration.class,
				NoteConfiguration.GET_CONFIG_VALUE, parameters);
		return noteConfigurationList;
	}

	@Override
	public Parameters getParameterValue(String parameterName, String userEmailId){
		Map<String, Object> parameters = new HashMap<>();
		if(userEmailId == null){
			userEmailId = NoteUtility.getLoggedInUserName();
		}
		parameters.put("parameterName", parameterName);
		parameters.put("emailId", userEmailId);
		List<Parameters> param = genericDao.getResultByNamedQuery(Parameters.class,
				Parameters.GET_PARAMETERS_VALUE, parameters);
		if(param != null){
			return param.get(0);
		}
		return null;
	}
}
