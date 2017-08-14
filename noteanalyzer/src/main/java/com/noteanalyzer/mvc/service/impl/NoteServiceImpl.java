package com.noteanalyzer.mvc.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.noteanalyzer.dao.GenericDao;
import com.noteanalyzer.entity.address.Zipcodes;
import com.noteanalyzer.entity.appraisal.AppriasalSources;
import com.noteanalyzer.entity.notes.Note;
import com.noteanalyzer.entity.notes.NoteConfiguration;
import com.noteanalyzer.entity.notes.NoteType;
import com.noteanalyzer.entity.notes.Parameters;
import com.noteanalyzer.entity.notes.Property;
import com.noteanalyzer.entity.notes.PropertyType;
import com.noteanalyzer.mvc.model.AddressModel;
import com.noteanalyzer.mvc.model.NoteDashboardModel;
import com.noteanalyzer.mvc.model.NoteDetailModel;
import com.noteanalyzer.mvc.model.NoteInputFormModel;
import com.noteanalyzer.mvc.model.NoteTypeModel;
import com.noteanalyzer.mvc.model.PropertyTypeModel;
import com.noteanalyzer.mvc.service.NoteService;
import com.noteanalyzer.utility.ConverterUtility;
import com.noteanalyzer.webservice.appraisal.AppraisalPropertyBean;
import com.noteanalyzer.webservice.appraisal.IAppraisalSource;

import io.jsonwebtoken.lang.Collections;
import lombok.NonNull;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Autowired
	GenericDao genericDao;

	@Resource(name = "zillowWebService")
	IAppraisalSource zillowWebService;

	private final static Logger LOG = Logger.getLogger(NoteServiceImpl.class.getName());

	/**
	 * @return the zillowWebService
	 */
	public IAppraisalSource getZillowWebService() {
		return zillowWebService;
	}

	/**
	 * @param zillowWebService
	 *            the zillowWebService to set
	 */
	public void setZillowWebService(IAppraisalSource zillowWebService) {
		this.zillowWebService = zillowWebService;
	}

	public GenericDao getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDao genericDao) {
		this.genericDao = genericDao;
	}

	@Override
	@Transactional
	public void createNote(@NonNull NoteInputFormModel noteModel) throws ParseException {

		Optional<List<Property>> propertyList = getPropertyByAddress(noteModel);
		Property property = null;
		if (propertyList.isPresent()) {
			property = propertyList.get().get(0);
		} else {
			property = getPropertyFromZillow(noteModel.getStreetAddress(), noteModel.getSelCity(),
					noteModel.getSelState(), noteModel.getZipCode(), noteModel.getSelPropType().getPropertyTypeCode());
			property = genericDao.create(property);
		}
		Note note = ConverterUtility.convertNoteModelToEntity(noteModel, property);
		note.setPropertyId(property);
		genericDao.create(note);

	}
	
	@Override
	public Optional<List<AppriasalSources>> getApprisalSourceDetail(String apprisalSourceCode) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("appraisalSource", apprisalSourceCode);
		List<AppriasalSources> appriasalSourcesList = genericDao.getResultByNamedQuery(AppriasalSources.class,
				AppriasalSources.GET_APPRAISAL_SOURCE_DETAIL, parameters);
		if (Collections.isEmpty(appriasalSourcesList)) {
			return Optional.empty();
		}
		return Optional.of(appriasalSourcesList);
	}
	

	private Property getPropertyFromZillow(String streetAddress, String city, String state, String zipcode,
			String propertyTypeCode) {
		AppraisalPropertyBean appraisalPropertyBean = zillowWebService.getPropertyDetailsWithAddress(streetAddress,
				city, state, zipcode);
		LOG.info(appraisalPropertyBean.toString());
		Optional<List<AppriasalSources>> apprsialSourceList = getApprisalSourceDetail("Zillow");
		return ConverterUtility.createPropertyObject(appraisalPropertyBean, propertyTypeCode,apprsialSourceList.get().get(0));
	}

	@Override
	public Optional<List<Property>> getPropertyByAddress(NoteInputFormModel noteModel) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("zipCode", Integer.valueOf(noteModel.getZipCode()));
		parameters.put("streetAddress", noteModel.getStreetAddress());
		parameters.put("city", noteModel.getSelCity());
		parameters.put("state", noteModel.getSelState());
		List<Property> propertyList = genericDao.getResultByNamedQuery(Property.class,
				Property.GET_PROPERTY_DETAILS_BY_ADDRESS, parameters);
		if (Collections.isEmpty(propertyList)) {
			return Optional.empty();
		}
		return Optional.of(propertyList);
	}

	@Override
	public Optional<List<NoteTypeModel>> getNoteType() {
		List<NoteType> noteTypeList = genericDao.getAll(NoteType.class);
		if (Collections.isEmpty(noteTypeList)) {
			return Optional.empty();
		}
		return Optional.of(ConverterUtility.convertNoteTypeEntityToModel(noteTypeList));
	}

	@Override
	public Optional<List<PropertyTypeModel>> getPropertyType() {
		List<PropertyType> propTypeList = genericDao.getAll(PropertyType.class);
		if (Collections.isEmpty(propTypeList)) {
			return Optional.empty();
		}
		return Optional.of(ConverterUtility.convertPropertyTypeEntityToModel(propTypeList));
	}

	@Override
	public Optional<NoteDetailModel> getNoteDetail(Long noteId) {
		Note note = genericDao.getById(Note.class, noteId);
		if (note == null) {
			return Optional.empty();
		}
		List<NoteType> noteTypeList = getNoteTypeByCode(note.getNoteType());
		Property property = note.getPropertyId();
		List<PropertyType> propertyTypeList = null;
		if (property != null) {
			propertyTypeList = getPropertyTypeByCode(property.getPropertyType());
		}
		Property appraisalProperty = getPropertyFromZillow(property.getStreetAddress(), property.getCity(),
				property.getState(), property.getZip().toString(), property.getPropertyType());
		if (appraisalProperty != null) {
			property = appraisalProperty;
			note.setPropertyId(appraisalProperty);
			genericDao.update(note);
		}
		return Optional.of(
				ConverterUtility.convertNoteEntityToNoteDetailModel(note, property, propertyTypeList, noteTypeList));
	}

	@Override
	public List<NoteType> getNoteTypeByCode(@NonNull String noteTypeCode) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("noteTypeCode", noteTypeCode);
		return genericDao.getResultByNamedQuery(NoteType.class, NoteType.GET_NOTE_TYPE_BY_TYPE, parameters);
	}

	@Override
	public List<PropertyType> getPropertyTypeByCode(@NonNull String propertyTypeCode) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("propertyTypeCode", propertyTypeCode);
		return genericDao.getResultByNamedQuery(PropertyType.class, PropertyType.GET_PROPERTY_TYPE_BY_TYPE, parameters);
	}

	@Override
	@Transactional
	public Optional<List<NoteDashboardModel>> getAllNotes(long userId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userId", userId);
		List<Note> noteList = genericDao.getResultByNamedQuery(Note.class, Note.GET_NOTE_DETAILS_BY_USER, parameters);
		if (CollectionUtils.isEmpty(noteList)) {
			return Optional.empty();
		}
		// NoteForZillow
		return Optional.of(ConverterUtility.convertNoteToNoteSummaryModel(noteList));
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
	public Parameters getParameterValue(String parameterName, String userEmailId) {
		Map<String, Object> parameters = new HashMap<>();
		List<Parameters> param = null;
		parameters.put("parameterName", parameterName);
		if (StringUtils.isNotBlank(userEmailId)) {
			parameters.put("emailID", userEmailId);
			param = genericDao.getResultByNamedQuery(Parameters.class, Parameters.GET_PARAMETERS_VALUE_USER_ID,
					parameters);
		} else {
			param = genericDao.getResultByNamedQuery(Parameters.class, Parameters.GET_PARAMETERS_VALUE, parameters);
		}
		if (!CollectionUtils.isEmpty(param)) {
			return param.get(0);
		}
		return new Parameters();
	}

	@Override
	public Optional<AddressModel> getZipCodeDetails(String zipCode) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("zipCode", StringUtils.lowerCase(zipCode));

		List<Zipcodes> zipcodeDetails = genericDao.getResultByNamedQuery(Zipcodes.class,
				Zipcodes.GET_ZIPCODE_DETAILS_BY_ZIPCODE, parameters);
		if (!CollectionUtils.isEmpty(zipcodeDetails)) {
			AddressModel addressModel = ConverterUtility.convertZipCodeWithAddress(zipcodeDetails);
			return Optional.of(addressModel);
		}
		return Optional.empty();
	}

	@Override
	public Optional<AddressModel> getAllLocationDetails() {

		List<Zipcodes> zipcodeDetails = genericDao.getAll(Zipcodes.class);
		if (!CollectionUtils.isEmpty(zipcodeDetails)) {
			AddressModel addressModel = ConverterUtility.convertZipCodeWithAddress(zipcodeDetails);
			return Optional.of(addressModel);
		}
		return Optional.empty();
	}

	@Override
	@Transactional
	public Optional<NoteDetailModel> updateNote(@NonNull NoteDetailModel noteDetailModel) {
		Note noteEntity = genericDao.getById(Note.class, noteDetailModel.getNoteId());
		if (noteEntity == null) {
			return Optional.empty();
		}
		ConverterUtility.convertUpdatedNoteToEnityNote(noteEntity, noteDetailModel);
		genericDao.update(noteEntity);
		return Optional.of(noteDetailModel);

	}

	@Override
	public boolean deleteNote(@NonNull NoteDetailModel noteDetailModel, String userName) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("emailID", userName);
		parameters.put("noteId", noteDetailModel.getNoteId());
		List<Note> notes = genericDao.getResultByNamedQuery(Note.class, Note.GET_NOTE_DETAILS_BY_USER_NOTE_ID,
				parameters);
		if (notes != null) {
			genericDao.deleteById(Note.class, notes.get(0).getNoteId());
			return true;
		}
		System.out.println("There is No record found with logging user " + userName + " and noteDetailModel ID"
				+ noteDetailModel.getNoteId());
		return false;
	}

}
