package com.noteanalyzer.utility;

import static com.noteanalyzer.mvc.constant.NoteConstant.DEFAULT_DATE_FORMAT;
import static com.noteanalyzer.mvc.constant.NoteConstant.IN_ACTIVE_USER_FLAG;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;

import com.noteanalyzer.entity.address.Zipcodes;
import com.noteanalyzer.entity.appraisal.AppriasalSources;
import com.noteanalyzer.entity.notes.Note;
import com.noteanalyzer.entity.notes.NoteType;
import com.noteanalyzer.entity.notes.Property;
import com.noteanalyzer.entity.notes.PropertyAppraisals;
import com.noteanalyzer.entity.notes.PropertyArea;
import com.noteanalyzer.entity.notes.PropertyType;
import com.noteanalyzer.entity.user.User;
import com.noteanalyzer.entity.valuation.Statistics;
import com.noteanalyzer.mvc.model.AddressModel;
import com.noteanalyzer.mvc.model.DemographicDetailModel;
import com.noteanalyzer.mvc.model.NoteDashboardModel;
import com.noteanalyzer.mvc.model.NoteDetailModel;
import com.noteanalyzer.mvc.model.NoteInputFormModel;
import com.noteanalyzer.mvc.model.NoteTypeModel;
import com.noteanalyzer.mvc.model.PropertyDetailModel;
import com.noteanalyzer.mvc.model.PropertyTypeModel;
import com.noteanalyzer.mvc.model.UserModel;
import com.noteanalyzer.mvc.service.NoteAnalysisService;
import com.noteanalyzer.webservice.appraisal.AppraisalPropertyBean;

import lombok.NonNull;

public class ConverterUtility {

	public static User convertUserModelToUserEntity(@NonNull UserModel userModel, BCryptPasswordEncoder encoder) {

		User user = new User();
		user.setDisplayName(userModel.getDisplayName());
		user.setPassword(encoder.encode(userModel.getPassword()));
		user.setEmailID(userModel.getEmail());
		user.setContactNumber(userModel.getPhoneNumber());
		user.setStreet(userModel.getStreetAddress());
		user.setCity(userModel.getSelCity());
		user.setState(userModel.getSelState());
		user.setZipcode(userModel.getZipCode());
		user.setVerificationToken(userModel.getVerificationToken());
		user.setVerificationTokenCreationTime(ZonedDateTime.now());
		user.setIsActive(IN_ACTIVE_USER_FLAG);
		user.setCreateDate(ZonedDateTime.now());
		user.setUpdateDate(ZonedDateTime.now());
		user.setUnsuccessfulLoginAttempts(new Long(0));
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
		userModel.setStreetAddress(user.getStreet());
		userModel.setSelCity(user.getCity());
		userModel.setSelState(user.getState());
		userModel.setZipCode(user.getZipcode());
		return userModel;
	}

	public static Note convertNoteModelToEntity(NoteInputFormModel note, Property property) throws ParseException {
		DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Note noteEntity = new Note();
		noteEntity.setUserId(note.getUserId());
		noteEntity.setNoteType(note.getSelNoteType().getNoteTypeCode());
		noteEntity.setFaceValue(note.getOriginalPrincipleBalance());
		noteEntity.setSalePrice(note.getSalePrice());
		noteEntity.setOriginationDate(df.parse(note.getNoteDate()));
		noteEntity.setPerforming(note.getPerforming());
		noteEntity.setNotePosition(Long.valueOf(note.getNotePosition()));
		noteEntity.setTermMonths(note.getOriginalTerm());
		noteEntity.setInterestRateInitial(note.getRate());
		noteEntity.setBorrowerCreditScore(Objects.toString(note.getBorrowerCreditScore(), null));
		noteEntity.setLatePayments(Long.valueOf(note.getNoOfLatePayment()));
		noteEntity.setNotePrice(BigDecimal.valueOf(Double.valueOf(note.getNotePrice())));
		noteEntity.setOriginalPropertyValue(BigDecimal.valueOf(Double.valueOf(note.getOriginalPropertyValue())));
		noteEntity.setRemainingNoOfPayment(Integer.valueOf(note.getRemainingNoOfPayment()));
		// noteEntity.setStoreName(DEFAULT_STORE_NAME);
		// noteEntity.setSearchName(DEFAULT_STORE_NAME);
		noteEntity.setUserScore(BigDecimal.valueOf(Double.valueOf(note.getUserScore())));
		noteEntity.setSystemScore(null);
		noteEntity.setOriginalLTV(NoteAnalysisService.getOriginalLTV(note.getOriginalPrincipleBalance().toString(),
				note.getOriginalPropertyValue()));
		noteEntity.setEffectiveLTV(
				NoteAnalysisService.getEffectiveLTV(note.getNotePrice(), note.getOriginalPropertyValue()));
		if (property != null) {
			Set<PropertyAppraisals> propertyApprisalSet = property.getPropertyAppraisalSet();
			if (propertyApprisalSet != null) {
				Iterator<PropertyAppraisals> itr = propertyApprisalSet.iterator();
				if (itr.hasNext()) {
					PropertyAppraisals propertyAppraisals = itr.next();
					noteEntity.setCurrentEffectiveLTV(NoteAnalysisService.getCurrentEffectiveLTV(
							Objects.toString(note.getNotePrice(), null), propertyAppraisals.getMarketValue()));
				}
			}
		}
		noteEntity.setRoi(NoteAnalysisService.getROI());
		noteEntity.setYield(note.getYieldValue());
		noteEntity.setPdiPayment(note.getPdiPayment());
		noteEntity.setTdiPayment(note.getTdiPayment());
		noteEntity.setUnpaidBalance(note.getUpb());
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

	public static List<NoteDashboardModel> convertNoteToNoteSummaryModel(List<Note> noteList,
			List<Statistics> statisticsList) {
		List<NoteDashboardModel> summaryModelList = new ArrayList<>();
		if (noteList != null) {
			for (Note model : noteList) {
				NoteDashboardModel dashBoardModel = new NoteDashboardModel();
				Property property = model.getPropertyId();
				dashBoardModel.setNoteId(model.getNoteId());
				dashBoardModel.setNoteAddress(property.getStreetAddress() + ", " + property.getCity() + ", "
						+ property.getState() + ", " + property.getZip());

				Set<PropertyAppraisals> propertyApprisalSet = property.getPropertyAppraisalSet();
				if (propertyApprisalSet != null) {
					Iterator<PropertyAppraisals> itr = propertyApprisalSet.iterator();
					if (itr.hasNext()) {
						PropertyAppraisals propertyAppraisals = propertyApprisalSet.iterator().next();
						dashBoardModel.setMarketValue(propertyAppraisals.getMarketValue());
						dashBoardModel.setMarketUpdateDate(propertyAppraisals.getMarketValueUpdatedDate());

					}
				}
				if (statisticsList != null) {
					String areaId = null;
					Iterator<PropertyArea> itr = property.getPropertyAreaSet().iterator();
					if (itr.hasNext()) {
						areaId = itr.next().getAreaId();
					}
					for (Statistics stat : statisticsList) {
						if (areaId.equalsIgnoreCase(stat.getBaseId())) {
							if ("CRIME".equalsIgnoreCase(stat.getStatType())) {
								dashBoardModel.setCrimeScore(stat.getStatValue());
							} else if ("SCHOOL".equalsIgnoreCase(stat.getStatType())) {
								dashBoardModel.setSchoolScore(stat.getStatValue());
							}
						}
					}
				}
				dashBoardModel.setYield(model.getYield());
				dashBoardModel.setEffectiveLTV(model.getEffectiveLTV());
				dashBoardModel.setCurrentEffectiveLTV(model.getCurrentEffectiveLTV());
				dashBoardModel.setOriginalLTV(model.getOriginalLTV());
				dashBoardModel.setMarketValueAvailable(StringUtils.isNotBlank(dashBoardModel.getMarketValue()));

				summaryModelList.add(dashBoardModel);
			}
		}
		return summaryModelList;
	}

	public static AddressModel convertZipCodeWithAddress(List<Zipcodes> zipcodeDetailsList) {
		Set<String> cityList = new HashSet<>();
		Set<String> stateList = new HashSet<>();
		AddressModel model = new AddressModel();
		for (Zipcodes zip : zipcodeDetailsList) {
			cityList.add(zip.getCity());
			stateList.add(zip.getState());
		}
		model.setStateList(stateList);
		model.setCityList(cityList);
		return model;
	}

	public static Property createPropertyObject(AppraisalPropertyBean appraisalPropertyBean, String propertyTypeCode,
			AppriasalSources appraisalsSource, Property propertyFromDB, Zipcodes zipCodeDetails) {
		Property property = propertyFromDB == null ? new Property() : propertyFromDB;
		property.setZip(Integer.valueOf(appraisalPropertyBean.getZipCode()));
		property.setStreetAddress(appraisalPropertyBean.getStreetAddress());
		property.setCity(appraisalPropertyBean.getCity());
		property.setState(appraisalPropertyBean.getState());
		property.setPropertyType(propertyTypeCode);

		PropertyAppraisals propertyAppraisals = new PropertyAppraisals();
		propertyAppraisals.setLastSoldDate(appraisalPropertyBean.getLastSoldDate());
		propertyAppraisals.setLastSoldPrice(appraisalPropertyBean.getLastSoldPrice());
		property.setNumberOfBathrooms(appraisalPropertyBean.getNumberOfBathrooms());
		property.setNumberOfBedrooms(appraisalPropertyBean.getNumberOfBedrooms());
		property.setPropertyBuiltUpSize(appraisalPropertyBean.getPropertyBuiltUpSize());
		property.setPropertyLotSize(appraisalPropertyBean.getPropertyLotSize());
		propertyAppraisals.setRentValue(appraisalPropertyBean.getRentValue());
		propertyAppraisals.setTaxAssessmentValue(appraisalPropertyBean.getTaxAssessmentValue());
		propertyAppraisals.setTaxAssessmentYear(appraisalPropertyBean.getTaxAssessmentYear());
		propertyAppraisals.setMarketValue(appraisalPropertyBean.getMarketValue());
		Date lastMarketValueUpdatedDate = appraisalPropertyBean.getLastMarketValueUpdated() == null ? new Date()
				: appraisalPropertyBean.getLastMarketValueUpdated();
		propertyAppraisals.setMarketValueUpdatedDate(lastMarketValueUpdatedDate);
		propertyAppraisals.setPropertyMapUrl(appraisalPropertyBean.getPropertyMapUrl());
		propertyAppraisals.setPropertyGraphAndDataUrl(appraisalPropertyBean.getPropertyGraphAndDataUrl());
		propertyAppraisals.setPropertyDetailUrl(appraisalPropertyBean.getPropertyDetailUrl());
		propertyAppraisals.setAppraisalsSource(appraisalPropertyBean.getAppraisalSource());
		propertyAppraisals.setPropertyId(property);
		property.getPropertyAppraisalSet().add(propertyAppraisals);
		property.setUpdatedTime(ZonedDateTime.now());
		PropertyArea propertyArea = new PropertyArea();
		Iterator<PropertyArea> itr = property.getPropertyAreaSet().iterator();
		if (itr.hasNext()) {
			propertyArea = itr.next();
			if (zipCodeDetails != null) {
				propertyArea.setAreaId(zipCodeDetails.getAreaId());
				propertyArea.setAreaType(zipCodeDetails.getAreaType());
			}
			property.getPropertyAreaSet().add(propertyArea);
			propertyArea.setPropertyId(property);
		}
		return property;
	}

	public static NoteDetailModel convertNoteEntityToNoteDetailModel(Note note, Property property,
			List<PropertyType> propertyTypeList, List<NoteType> noteTypeList) {
		NoteDetailModel noteDetailModel = new NoteDetailModel();
		if (note != null) {
			DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			noteDetailModel.setNoteId(note.getNoteId());
			noteDetailModel.setUserId(note.getUserId());
			if (!CollectionUtils.isEmpty(noteTypeList)) {
				noteDetailModel.setSelNoteType(convertNoteTypeEntityToModel(noteTypeList).get(0));
			}
			if (!CollectionUtils.isEmpty(propertyTypeList)) {
				noteDetailModel.setSelPropType(convertPropertyTypeEntityToModel(propertyTypeList).get(0));
			}
			noteDetailModel.setOriginalPrincipleBalance(note.getFaceValue());
			noteDetailModel.setSalePrice(note.getSalePrice());
			noteDetailModel.setNoteDate(df.format(note.getOriginationDate()));
			if ("Y".equalsIgnoreCase(note.getPerforming())) {
				noteDetailModel.setPerforming("Performing");
			} else if ("N".equalsIgnoreCase(note.getPerforming())) {
				noteDetailModel.setPerforming("Non-Performing");
			} else {
				noteDetailModel.setPerforming("Unknown");
			}
			noteDetailModel.setUpb(note.getUnpaidBalance());
			noteDetailModel.setPdiPayment(note.getPdiPayment());
			noteDetailModel.setTdiPayment(note.getTdiPayment());
			noteDetailModel.setNotePosition(Objects.toString(note.getNotePosition(), ""));
			noteDetailModel.setOriginalTerm(note.getTermMonths());
			noteDetailModel.setRate(note.getInterestRateInitial());
			noteDetailModel.setBorrowerCreditScore(Objects.toString(note.getBorrowerCreditScore(), ""));
			noteDetailModel.setNoOfLatePayment(Objects.toString(note.getLatePayments(), ""));
			noteDetailModel.setNotePrice(note.getNotePrice());
			noteDetailModel.setOriginalPropertyValue(note.getOriginalPropertyValue());
			noteDetailModel.setRemainingNoOfPayment(Objects.toString(note.getRemainingNoOfPayment(), ""));
			noteDetailModel.setOriginalLTV(note.getOriginalLTV());
			noteDetailModel.setEffectiveLTV(note.getEffectiveLTV());
			noteDetailModel.setCurrentEffectiveLTV(note.getCurrentEffectiveLTV());
			noteDetailModel.setROI(NoteAnalysisService.getROI());
			noteDetailModel.setYieldValue(note.getYield());
			PropertyDetailModel propertyDetailModel = new PropertyDetailModel();

			if (property != null) {
				Set<PropertyAppraisals> propertyApprisalSet = property.getPropertyAppraisalSet();
				if (propertyApprisalSet != null) {
					Iterator<PropertyAppraisals> itr = propertyApprisalSet.iterator();
					if (itr.hasNext()) {
						PropertyAppraisals propertyAppraisals = itr.next();
						propertyDetailModel.setLastSoldDate(propertyAppraisals.getLastSoldDate());
						propertyDetailModel.setLastSoldPrice(propertyAppraisals.getLastSoldPrice());
						propertyDetailModel.setRentValue(propertyAppraisals.getRentValue());
						propertyDetailModel.setTaxAssessmentValue(propertyAppraisals.getTaxAssessmentValue());
						propertyDetailModel.setTaxAssessmentYear(propertyAppraisals.getTaxAssessmentYear());
						propertyDetailModel.setPropertyDetailUrl(propertyAppraisals.getPropertyDetailUrl());
						propertyDetailModel.setPropertyMapUrl(propertyAppraisals.getPropertyMapUrl());
						propertyDetailModel.setPropertyGraphAndDataUrl(propertyAppraisals.getPropertyGraphAndDataUrl());

						propertyDetailModel.setMarketValue(propertyAppraisals.getMarketValue());
						noteDetailModel.setCurrentEffectiveLTV(NoteAnalysisService.getCurrentEffectiveLTV(
								Objects.toString(note.getNotePrice(), null), propertyAppraisals.getMarketValue()));

					}
				}
				propertyDetailModel.setZip(property.getZip());
				propertyDetailModel.setAge(property.getAge());
				propertyDetailModel.setCity(property.getCity());
				propertyDetailModel.setNumberOfBathrooms(property.getNumberOfBathrooms());
				propertyDetailModel.setNumberOfBedrooms(property.getNumberOfBedrooms());
				propertyDetailModel.setOtherHigherPriorityDebt(property.getOtherHigherPriorityDebt());
				propertyDetailModel.setOtherLowerPriorityDebt(property.getOtherLowerPriorityDebt());
				propertyDetailModel.setOtherMonthlyExpenses(property.getOtherMonthlyExpenses());

				propertyDetailModel.setPropertyBuiltUpSize(property.getPropertyBuiltUpSize());
				propertyDetailModel.setPropertyBuiltYear(property.getPropertyBuiltYear());
				propertyDetailModel.setPropertyId(property.getPropertyId());

				propertyDetailModel.setPropertyLotSize(property.getPropertyLotSize());

				propertyDetailModel.setPropertyType(property.getPropertyType());

				propertyDetailModel.setSizeSF(property.getSizeSF());
				propertyDetailModel.setState(property.getState());
				propertyDetailModel.setStreetAddress(property.getStreetAddress());

				propertyDetailModel.setSubdividable(property.getSubdividable());
			}

			noteDetailModel.setPropertyDetailModel(propertyDetailModel);

			DemographicDetailModel demoGraphicDetailModel = new DemographicDetailModel();
			demoGraphicDetailModel.setCrime("1000");
			demoGraphicDetailModel.setForeClosure("Test forecoluser");
			demoGraphicDetailModel.setSchoolScore("School");

			noteDetailModel.setDemoGraphicDetailModel(demoGraphicDetailModel);
		}

		return noteDetailModel;
	}

	public static void convertUpdatedNoteToEnityNote(Note noteEntity, NoteDetailModel noteDetailModel) {
		noteEntity.setFaceValue(noteDetailModel.getOriginalPrincipleBalance());
		noteEntity.setInterestRateInitial(noteDetailModel.getRate());
		noteEntity.setPdiPayment(noteDetailModel.getPdiPayment());
		noteEntity.setUnpaidBalance(noteDetailModel.getUpb());
		noteEntity.setNotePrice(noteDetailModel.getNotePrice());
		noteEntity.setOriginalPropertyValue(noteDetailModel.getOriginalPropertyValue());
		noteEntity.setCurrentEffectiveLTV(
				NoteAnalysisService.getCurrentEffectiveLTV(Objects.toString(noteDetailModel.getNotePrice(), null),
						noteDetailModel.getPropertyDetailModel().getMarketValue()));
		noteEntity.setOriginalLTV(
				NoteAnalysisService.getOriginalLTV(noteDetailModel.getOriginalPrincipleBalance().toString(),
						noteDetailModel.getOriginalPropertyValue().toString()));
		noteEntity.setEffectiveLTV(NoteAnalysisService.getEffectiveLTV(noteDetailModel.getNotePrice().toString(),
				noteDetailModel.getOriginalPropertyValue().toString()));

	}

}
