package com.noteanalyzer.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.noteanalyzer.dao.GenericDao;
import com.noteanalyzer.mvc.model.NoteInputFormModel;
import com.noteanalyzer.mvc.service.NoteService;
import com.noteanalyzer.utility.NoteUtility;

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
	public void createNote(NoteInputFormModel note) {
		genericDao.create(NoteUtility.convertModelToEntity(note));

	}

}
