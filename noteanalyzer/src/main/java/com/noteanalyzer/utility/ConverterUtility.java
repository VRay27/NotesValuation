package com.noteanalyzer.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.noteanalyzer.entity.address.Zipcodes;
import com.noteanalyzer.entity.notes.Note;
import com.noteanalyzer.entity.notes.NoteType;
import com.noteanalyzer.entity.notes.Property;
import com.noteanalyzer.entity.notes.PropertyType;
import com.noteanalyzer.entity.user.User;
import com.noteanalyzer.mvc.model.AddressModel;
import com.noteanalyzer.mvc.model.NoteInputFormModel;
import com.noteanalyzer.mvc.model.NoteSummaryModel;
import com.noteanalyzer.mvc.model.NoteTypeModel;
import com.noteanalyzer.mvc.model.PropertyTypeModel;
import com.noteanalyzer.mvc.model.UserModel;

import lombok.NonNull;

public class ConverterUtility {

	private static String IMG_SRC_PATH = "";

	public static User convertUserModelToUserEntity(@NonNull UserModel userModel, BCryptPasswordEncoder encoder) {

		User user = new User();
		user.setDisplayName(userModel.getDisplayName());
		//user.setUserName(userModel.getEmail());
		user.setPassword(encoder.encode(userModel.getPassword()));
		user.setEmailID(userModel.getEmail());
		user.setContactNumber(userModel.getPhoneNumber());
		if (userModel.getAddress() != null) {
			user.setStreet(userModel.getAddress().getStreetAddress());
			user.setCity(userModel.getAddress().getCity());
			user.setState(userModel.getAddress().getState());
		}
		user.setVerificationToken(userModel.getVerificationToken());
		user.setVerificationTokenCreationTime(ZonedDateTime.now());
		user.setIsActive("IA");
		user.setCreateDate(ZonedDateTime.now());
		user.setUpdateDate(ZonedDateTime.now());
		return user;
	}

	public static UserModel convertUserToUserModel(@NonNull User user) {
		UserModel userModel = new UserModel();
		userModel.setDisplayName(user.getDisplayName());
		userModel.setUserId(user.getUserId());
		userModel.setPhoneNumber(user.getContactNumber());
		userModel.setEmail(user.getEmailID());
		userModel.setResetToken(user.getResetToken());
		userModel.setVerificationToken(user.getVerificationToken());
		userModel.setIsActive(user.getIsActive());
		if(StringUtils.isNotEmpty(user.getStreet())){
			AddressModel addressModel = new AddressModel();
			addressModel.setStreetAddress(user.getStreet());
			addressModel.setCity(user.getCity());
			addressModel.setState(user.getState());
			userModel.setAddressModel(addressModel);
		}
	
		return userModel;
	}

	public static Note convertNoteModelToEntity(NoteInputFormModel note) throws ParseException {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Note noteEntity = new Note();
		noteEntity.setUserId(note.getUserId());
		noteEntity.setNoteType(note.getSelNoteType().getNoteTypeCode());
		noteEntity.setFaceValue(note.getOriginalPrincipleBalance());
		noteEntity.setSalePrice(note.getSalePrice());
		noteEntity.setOriginationDate(df.parse(note.getNoteDate()));
		noteEntity.setPerforming(note.getPerforming());
		noteEntity.setNotePosition(note.getNotePosition());
		noteEntity.setTermMonths(note.getOriginalTerm());
		noteEntity.setInterestRateInitial(note.getRate());
		noteEntity.setBorrowerCreditScore(String.valueOf(note.getBorrowerCreditScore()));
		noteEntity.setLatePayments(note.getNoOfLatePayment());
		return noteEntity;
	}

	public static List<NoteTypeModel> convertNoteTypeEntityToModel(List<NoteType> noteTypeList) {
		List<NoteTypeModel> noteTypeModelList = new ArrayList<>();
		if (noteTypeList != null) {
			for (NoteType noteType : noteTypeList) {
				NoteTypeModel model = new NoteTypeModel();
				model.setArmIndexName(noteType.getArmIndexName());
				model.setBaloonAfterMonths(noteType.getBaloonAfterMonths());
				model.setInterestAdjustmentRules(noteType.getInterestAdjustmentRules());
				model.setInterestCap(noteType.getInterestCap());
				model.setInterestOnlyMonths(noteType.getInterestOnlyMonths());
				model.setMargin(noteType.getMargin());
				model.setNoteTypeCode(noteType.getNoteType());
				model.setNoteTypeValue(noteType.getDescription());
				model.setTermMonths(noteType.getTermMonths());
				noteTypeModelList.add(model);
			}
		}
		return noteTypeModelList;
	}

	public static List<PropertyTypeModel> convertPropertyTypeEntityToModel(List<PropertyType> propTypeList) {
		List<PropertyTypeModel> propTypeModelList = new ArrayList<>();
		if (propTypeList != null) {
			for (PropertyType propType : propTypeList) {
				propTypeModelList.add(new PropertyTypeModel(propType.getPropertyType(), propType.getDescription()));
			}
		}
		return propTypeModelList;

	}

	public static List<NoteSummaryModel> convertNoteToNoteSummaryModel(List<Note> noteList) {
		List<NoteSummaryModel> summaryModelList = new ArrayList<>();
		if (noteList != null) {
			NoteSummaryModel summaryModel = new NoteSummaryModel();
			for (Note model : noteList) {
				//NoteAddress noteAddress = model.getNoteAddress();
				Property property = model.getPropertyId();
				summaryModel.setNoteAddress(property.getStreetAddress()+", "+property.getCity()+", "+property.getState()+", "+property.getZip());
				 //summaryModel.setMarketValue(String.valueOf(property.getMarketValue()));
				 summaryModel.setNoteId(String.valueOf(model.getNoteId()));
				// summaryModel.setItv(NoteAnalysisService.getITV(purchasePrice, property.getMarketValue()));
				// summaryModel.setLtv(NoteAnalysisService.getLTV(model.getUnpaidPrincpalBal(),property.getMarketValue()));
				 summaryModel.setYield("test");
				 summaryModel.setCrime("crime000");

				summaryModelList.add(summaryModel);
			}
		}
		return summaryModelList;
	}

	public static AddressModel convertZipCodeWithAddress(List<Zipcodes> zipcodeDetailsList) {
		Set<String> cityList =  new HashSet<>();
		Set<String> stateList =  new HashSet<>();
		AddressModel model  = new AddressModel();
		for(Zipcodes zip : zipcodeDetailsList){
			
			cityList.add(zip.getCity());
			stateList.add(zip.getState());
		}
		 model.setStateList(stateList);
		 model.setCityList(cityList);
		 return model;
	}

}
