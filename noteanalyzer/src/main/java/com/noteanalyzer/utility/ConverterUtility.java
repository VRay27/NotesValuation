package com.noteanalyzer.utility;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.noteanalyzer.entity.notes.Note;
import com.noteanalyzer.entity.notes.NoteType;
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
		noteEntity.setNoteType(note.getSelNoteType().getNoteTypeCode());
		noteEntity.setPropertyType(note.getSelPropType().getPropertyTypeCode());
		noteEntity.setSalePrice(note.getSalePrice());
		noteEntity.setOriginationDate(df.parse(note.getNoteDate()));
		noteEntity.setPerforming(note.getSelPerforming());
		noteEntity.setNotePosition(note.getNotePosition());
		noteEntity.setTermMonths(note.getOriginalTerm());
		
		/*noteEntity.setTDI(note.getTdiPayment())));
		noteEntity.setUnpaidPrincpalBal(BigDecimal.valueOf(Double.valueOf(note.getUpb())));
		noteEntity.setOriginalTerm(Double.valueOf(note.getOriginalTerm()));
		noteEntity.setPreDeliveryInspectionPay(BigDecimal.valueOf(Double.valueOf(note.getPdiPayment())));
		noteEntity.setOriginalPrincipleBal(BigDecimal.valueOf(Double.valueOf(note.getOriginalPrincipleBalance())));
		*/
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
				//Property property = noteAddress.getProperty();
				 //summaryModel.setAssetImgSrc(IMG_SRC_PATH+noteAddress.getProperty());
				// summaryModel.setCrime(crime);
				// NoteAnalysisService.generateOverAllScore(weightedMap);
				 //summaryModel.setMarketValue(String.valueOf(property.getMarketValue()));
				 summaryModel.setNoteId(String.valueOf(model.getNoteId()));
				// summaryModel.setItv(NoteAnalysisService.getITV(purchasePrice, property.getMarketValue()));
				// summaryModel.setLtv(NoteAnalysisService.getLTV(model.getUnpaidPrincpalBal(),property.getMarketValue()));

				summaryModelList.add(summaryModel);
			}
		}
		return summaryModelList;
	}

}
