package com.noteanalyzer.utility;

import static com.noteanalyzer.mvc.constant.NoteConstant.DEFAULT_DATE_FORMAT;
import static com.noteanalyzer.mvc.constant.NoteConstant.IN_ACTIVE_USER_FLAG;

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
import com.noteanalyzer.entity.notes.LoanType;
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
import com.noteanalyzer.mvc.model.LoanTypeModel;
import com.noteanalyzer.mvc.model.NoteDashboardModel;
import com.noteanalyzer.mvc.model.NoteDetailModel;
import com.noteanalyzer.mvc.model.NoteInputFormModel;
import com.noteanalyzer.mvc.model.NoteTypeModel;
import com.noteanalyzer.mvc.model.PropertyDetailModel;
import com.noteanalyzer.mvc.model.PropertyTypeModel;
import com.noteanalyzer.mvc.model.UserModel;
import com.noteanalyzer.mvc.service.NoteAnalysisService;
import com.noteanalyzer.webservice.appraisal.AppraisalPropertyBean;

import io.jsonwebtoken.lang.Collections;
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

	public static Note convertNoteModelToEntity(NoteInputFormModel note, Property property, Note noteEntity)
			throws ParseException {
		DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		noteEntity = noteEntity == null ? new Note() : noteEntity;
		noteEntity.setUserId(note.getUserId());
		noteEntity.setNoteType(note.getSelNoteType());
		noteEntity.setLoanType(note.getSelLoanType());
		noteEntity.setOriginationDate(df.parse(note.getNoteDate()));
		noteEntity.setOriginalLoanBal(Double.valueOf(note.getOriginalPrincipleBalance()));
		if (StringUtils.isNotBlank(note.getLastPaymentRecievedDate())) {
			noteEntity.setLastPaymentRecievedDate(df.parse(note.getLastPaymentRecievedDate()));
		}
		if (StringUtils.isNotBlank(note.getNotePosition())) {
			noteEntity.setNotePosition(Long.valueOf(note.getNotePosition()));
		}
		noteEntity.setTermMonths(Double.valueOf(note.getOriginalTerm()));
		noteEntity.setInterestRateInitial(Double.valueOf(note.getRate()));
		if (StringUtils.isNotBlank(note.getBorrowerCreditScore())) {
			noteEntity.setBorrowerCreditScore(Objects.toString(note.getBorrowerCreditScore(), null));
		}
		if (StringUtils.isNotBlank(note.getNoOfLatePayment())) {
			noteEntity.setLatePayments(Long.valueOf(note.getNoOfLatePayment()));
		}
		noteEntity.setBorrowerName(note.getBorrowerName());
		noteEntity.setNotePrice(Double.valueOf(note.getNotePrice()));
		noteEntity.setEstimatedMarketValue(note.getEstimatedMarketValue());
		if (StringUtils.isNotBlank(note.getNoteScoreByUser())) {
			noteEntity.setUserScore(Double.valueOf(note.getNoteScoreByUser()));
		}
		noteEntity.setRemainingNoOfPayment(Integer.valueOf(note.getRemainingPayment()));
		noteEntity.setHoaFee(note.getHoaFees());
		noteEntity.setNumberOfPropUnit(note.getNoOfPropUnits());
		noteEntity.setSystemScore(null);
		noteEntity.setEstimatedLTV(NoteAnalysisService.getEstimatedLTV(note.getUpb(), note.getEstimatedMarketValue()));

		noteEntity.setEstimatedITV(
				NoteAnalysisService.getEstimatedITV(note.getNotePrice(), note.getEstimatedMarketValue()));
		noteEntity.setRoi(NoteAnalysisService.getROI(Double.valueOf(note.getPdiPayment()),
				Double.valueOf(note.getNotePrice()), note.getSelLoanType()));

		if (property != null) {
			Set<PropertyAppraisals> propertyApprisalSet = property.getPropertyAppraisalSet();
			if (propertyApprisalSet != null) {
				Iterator<PropertyAppraisals> itr = propertyApprisalSet.iterator();
				if (itr.hasNext()) {
					PropertyAppraisals propertyAppraisals = itr.next();
					noteEntity.setAppraisedITV(NoteAnalysisService.getCurrentITV(note.getNotePrice(),
							propertyAppraisals.getMarketValue()));
					noteEntity.setAppraisedLTV(
							NoteAnalysisService.getCurrentLTV(note.getUpb(), propertyAppraisals.getMarketValue()));
				}
			}
		}

		if (StringUtils.isNotBlank(note.getYieldValue())) {
			noteEntity.setYield(Double.valueOf(note.getYieldValue()));
		}
		noteEntity.setPdiPayment(Double.valueOf(note.getPdiPayment()));
		if (StringUtils.isNotBlank(note.getTdiPayment())) {
			noteEntity.setTdiPayment(Double.valueOf(note.getTdiPayment()));
		}
		noteEntity.setUnpaidBalance(Double.valueOf(note.getUpb()));
		noteEntity.setUpdatedDate(ZonedDateTime.now());
		return noteEntity;
	}

	public static List<LoanTypeModel> convertLoanTypeEntityToModel(List<LoanType> loanTypeList) {
		List<LoanTypeModel> loanTypeModelList = new ArrayList<>();
		if (loanTypeList != null) {
			for (LoanType loanType : loanTypeList) {
				LoanTypeModel model = new LoanTypeModel();
				model.setArmIndexName(loanType.getArmIndexName());
				model.setBaloonAfterMonths(loanType.getBaloonAfterMonths());
				model.setInterestAdjustmentRules(loanType.getInterestAdjustmentRules());
				model.setInterestCap(loanType.getInterestCap());
				model.setInterestOnlyMonths(loanType.getInterestOnlyMonths());
				model.setMargin(loanType.getMargin());
				model.setLoanTypeCode(loanType.getLoanType());
				model.setLoanTypeValue(loanType.getDescription());
				model.setTermMonths(loanType.getTermMonths());
				loanTypeModelList.add(model);
			}
		}
		return loanTypeModelList;
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
			List<Statistics> statisticsList, String subscription) {
		List<NoteDashboardModel> summaryModelList = new ArrayList<>();
		if (noteList != null) {
			for (Note model : noteList) {
				NoteDashboardModel dashBoardModel = new NoteDashboardModel();
				Property property = model.getPropertyId();
				dashBoardModel.setNoteId(model.getNoteId());
				dashBoardModel.setPropertyType(property.getPropertyType());
				dashBoardModel.setStreetAddress(property.getStreetAddress());
				dashBoardModel.setZipCode(Objects.toString(property.getZip(), ""));
				dashBoardModel.setState(property.getState());
				dashBoardModel.setCity(property.getCity());

				dashBoardModel.setNoteAddress(property.getStreetAddress() + ", " + property.getCity() + ", "
						+ property.getState() + ", " + property.getZip());

				Set<PropertyAppraisals> propertyApprisalSet = property.getPropertyAppraisalSet();
				if (propertyApprisalSet != null) {
					Iterator<PropertyAppraisals> itr = propertyApprisalSet.iterator();
					if (itr.hasNext()) {
						PropertyAppraisals propertyAppraisals = itr.next();
						dashBoardModel.setMarketValue(propertyAppraisals.getMarketValue());
						dashBoardModel.setMarketUpdateDate(propertyAppraisals.getMarketValueUpdatedDate());
						if ("P1".equalsIgnoreCase(subscription)) {
							dashBoardModel.setMarketValueAvailable(true);
							if (StringUtils.isBlank(propertyAppraisals.getMarketValue())) {
								dashBoardModel.setRowText("No Data Available");
								dashBoardModel.setMarketValueAvailable(false);
							}
						} else {
							dashBoardModel.setRowText("Subscribe");
							dashBoardModel.setMarketValueAvailable(false);
						}
					}
				}
				if (statisticsList != null) {
					String areaId = "";
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
				dashBoardModel.setYield(Objects.toString(model.getYield(), ""));
				dashBoardModel.setEstimatedITV(Objects.toString(model.getEstimatedITV(), ""));
				dashBoardModel.setCurrentITV(Objects.toString(model.getAppraisedITV(), ""));
				dashBoardModel.setCurrentLTV(Objects.toString(model.getAppraisedLTV(), ""));
				dashBoardModel.setEstimatedLTV(Objects.toString(model.getEstimatedLTV(), ""));

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
		Iterator<PropertyAppraisals> itr = property.getPropertyAppraisalSet().iterator();
		while (itr.hasNext()) {
			PropertyAppraisals appraisalFromDB = itr.next();
			if (propertyFromDB != null && appraisalPropertyBean.getAppraisalSource()
					.equalsIgnoreCase(appraisalFromDB.getAppraisalsSource())) {
				propertyAppraisals = appraisalFromDB;
				break;
			}
		}

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
		if (propertyFromDB == null && zipCodeDetails != null) {
			PropertyArea propertyArea = new PropertyArea();
			propertyArea.setAreaId(zipCodeDetails.getAreaId());
			propertyArea.setAreaType(zipCodeDetails.getAreaType());
			property.getPropertyAreaSet().add(propertyArea);
			propertyArea.setPropertyId(property);
		}
		return property;
	}

	public static NoteDetailModel convertNoteEntityToNoteDetailModel(Note note, Property property,
			List<PropertyType> propertyTypeList, List<LoanType> noteTypeList) {
		NoteDetailModel noteDetailModel = new NoteDetailModel();
		if (note != null) {
			DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			noteDetailModel.setNoteId(note.getNoteId());
			noteDetailModel.setUserId(note.getUserId());
			if (!CollectionUtils.isEmpty(noteTypeList)) {
				noteDetailModel.setSelNoteType(convertLoanTypeEntityToModel(noteTypeList).get(0));
			}
			if (!CollectionUtils.isEmpty(propertyTypeList)) {
				noteDetailModel.setSelPropType(convertPropertyTypeEntityToModel(propertyTypeList).get(0));
			}
			noteDetailModel.setOriginalPrincipleBalance(Objects.toString(note.getFaceValue(), ""));
			noteDetailModel.setSalePrice(Objects.toString(note.getSalePrice(), ""));
			noteDetailModel.setNoteDate(df.format(note.getOriginationDate()));

			noteDetailModel.setUpb(Objects.toString(note.getUnpaidBalance(), ""));
			noteDetailModel.setPdiPayment(Objects.toString(note.getPdiPayment(), ""));
			noteDetailModel.setTdiPayment(Objects.toString(note.getTdiPayment(), ""));
			noteDetailModel.setNotePosition(Objects.toString(note.getNotePosition(), ""));
			noteDetailModel.setOriginalTerm(Objects.toString(note.getTermMonths()));
			noteDetailModel.setRate(Objects.toString(note.getInterestRateInitial(), ""));
			noteDetailModel.setBorrowerCreditScore(Objects.toString(note.getBorrowerCreditScore(), ""));
			noteDetailModel.setNoOfLatePayment(Objects.toString(note.getLatePayments(), ""));
			noteDetailModel.setNotePrice(Objects.toString(note.getNotePrice(), ""));
			noteDetailModel.setOriginalPropertyValue(Objects.toString(note.getOriginalPropertyValue(), ""));
			noteDetailModel.setRemainingNoOfPayment(Objects.toString(note.getRemainingNoOfPayment(), ""));
			noteDetailModel.setEstimatedLTV(Objects.toString(note.getEstimatedLTV(), ""));
			noteDetailModel.setCurrentLTV(Objects.toString(note.getAppraisedLTV(), ""));
			noteDetailModel.setCurrentITV(Objects.toString(note.getAppraisedITV(), ""));
			noteDetailModel.setEstimatedITV(Objects.toString(note.getEstimatedITV(), ""));
			noteDetailModel.setROI(Objects.toString(
					NoteAnalysisService.getROI(note.getPdiPayment(), note.getNotePrice(), note.getNoteType()), ""));
			noteDetailModel.setYieldValue(Objects.toString(note.getYield(), ""));
			PropertyDetailModel propertyDetailModel = new PropertyDetailModel();

			if (property != null) {
				Set<PropertyAppraisals> propertyApprisalSet = property.getPropertyAppraisalSet();
				if (propertyApprisalSet != null) {
					DateFormat zillowDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Iterator<PropertyAppraisals> itr = propertyApprisalSet.iterator();
					if (itr.hasNext()) {
						PropertyAppraisals propertyAppraisals = itr.next();
						if (propertyAppraisals.getLastSoldDate() != null) {
							propertyDetailModel
									.setLastSoldDate(zillowDateFormat.format(propertyAppraisals.getLastSoldDate()));
						}
						propertyDetailModel.setLastSoldPrice(propertyAppraisals.getLastSoldPrice());
						propertyDetailModel.setRentValue(propertyAppraisals.getRentValue());
						propertyDetailModel.setTaxAssessmentValue(propertyAppraisals.getTaxAssessmentValue());
						propertyDetailModel.setTaxAssessmentYear(propertyAppraisals.getTaxAssessmentYear());
						propertyDetailModel.setPropertyDetailUrl(propertyAppraisals.getPropertyDetailUrl());
						propertyDetailModel.setPropertyMapUrl(propertyAppraisals.getPropertyMapUrl());
						propertyDetailModel.setPropertyGraphAndDataUrl(propertyAppraisals.getPropertyGraphAndDataUrl());
						propertyDetailModel.setMarketValue(propertyAppraisals.getMarketValue());
						propertyDetailModel.setMarketUpdateDate(
								zillowDateFormat.format(propertyAppraisals.getMarketValueUpdatedDate()));

					}
				}
				propertyDetailModel.setZip(Objects.toString(property.getZip(), ""));
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
			DemographicDetailModel demoGraphicDetailModel = new DemographicDetailModel();
			noteDetailModel.setDemoGraphicDetailModel(demoGraphicDetailModel);

			noteDetailModel.setPropertyDetailModel(propertyDetailModel);

		}

		return noteDetailModel;
	}

	public static void convertUpdatedNoteToEnityNote(Note noteEntity, NoteDetailModel noteDetailModel) {
		noteEntity.setFaceValue(Double.valueOf(noteDetailModel.getOriginalPrincipleBalance()));
		noteEntity.setInterestRateInitial(Double.valueOf(noteDetailModel.getRate()));
		noteEntity.setPdiPayment(Double.valueOf(noteDetailModel.getPdiPayment()));
		noteEntity.setUnpaidBalance(Double.valueOf(noteDetailModel.getUpb()));
		noteEntity.setNotePrice(Double.valueOf(noteDetailModel.getNotePrice()));
		noteEntity.setOriginalPropertyValue(Double.valueOf(noteDetailModel.getOriginalPropertyValue()));
		/*
		 * noteEntity.setCurrentEffectiveLTV(
		 * NoteAnalysisService.getCurrentEffectiveLTV(Objects.toString(
		 * noteDetailModel.getNotePrice(), null),
		 * noteDetailModel.getPropertyDetailModel().getMarketValue()));
		 * noteEntity.setOriginalLTV(
		 * NoteAnalysisService.getOriginalLTV(noteDetailModel.
		 * getOriginalPrincipleBalance().toString(),
		 * noteDetailModel.getOriginalPropertyValue().toString()));
		 * noteEntity.setEffectiveLTV(NoteAnalysisService.getEffectiveLTV(
		 * noteDetailModel.getNotePrice().toString(),
		 * noteDetailModel.getOriginalPropertyValue().toString()));
		 */

	}

	public static List<NoteTypeModel> convertNoteTypeEntityToNoteTypeModel(List<NoteType> noteTypeList) {
		List<NoteTypeModel> noteTypeModelList = new ArrayList<>();
		if (Collections.isEmpty(noteTypeList)) {
			return noteTypeModelList;
		}

		for (NoteType noteType : noteTypeList) {
			NoteTypeModel model = new NoteTypeModel();
			model.setNoteType(noteType.getNoteType());
			model.setDescription(noteType.getDescription());
			noteTypeModelList.add(model);
		}
		return noteTypeModelList;
	}

	public static NoteInputFormModel convertNoteEntityToNoteInputFormModel(Note note, NoteInputFormModel model) {
		model = model == null ? new NoteInputFormModel() : model;

		DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		model.setNoteId(note.getNoteId());
		model.setUserId(note.getUserId());
		model.setOriginalPrincipleBalance(Objects.toString(note.getOriginalLoanBal(), ""));
		model.setNoteDate(df.format(note.getOriginationDate()));
		if (note.getLastPaymentRecievedDate() != null) {
			model.setLastPaymentRecievedDate(df.format(note.getLastPaymentRecievedDate()));
		}
		model.setUpb(Objects.toString(note.getUnpaidBalance(), ""));
		model.setOriginalTerm(Objects.toString(note.getTermMonths(), ""));
		model.setNoteScoreByUser(Objects.toString(note.getUserScore(), ""));
		model.setPdiPayment(Objects.toString(note.getPdiPayment(), ""));
		model.setTdiPayment(Objects.toString(note.getTdiPayment(), ""));
		model.setNotePosition(Objects.toString(note.getNotePosition(), ""));
		model.setRate(Objects.toString(note.getInterestRateInitial(), ""));
		model.setBorrowerCreditScore(Objects.toString(note.getBorrowerCreditScore(), ""));
		model.setNoOfLatePayment(Objects.toString(note.getLatePayments(), ""));
		model.setNotePrice(Objects.toString(note.getNotePrice(), ""));
		model.setRemainingPayment(Objects.toString(note.getRemainingNoOfPayment(), ""));
		model.setEstimatedLTV(Objects.toString(note.getEstimatedLTV(), ""));
		model.setCurrentLTV(Objects.toString(note.getAppraisedLTV(), ""));
		model.setCurrentITV(Objects.toString(note.getAppraisedITV(), ""));
		model.setEstimatedITV(Objects.toString(note.getEstimatedITV(), ""));
		model.setROI(Objects.toString(note.getRoi(), ""));
		model.setYieldValue(Objects.toString(note.getYield(), ""));
		model.setEstimatedMarketValue(Objects.toString(note.getEstimatedMarketValue(), ""));
		model.setBorrowerName(Objects.toString(note.getBorrowerName(), ""));
		model.setNoOfLatePayment(Objects.toString(note.getLatePayments(), ""));
		model.setHoaFees(Objects.toString(note.getHoaFee(), ""));
		model.setNoOfLatePayment(Objects.toString(note.getLatePayments(), ""));
		PropertyDetailModel propertyDetailModel = new PropertyDetailModel();
		Property property = note.getPropertyId();
		if (property != null) {
			Set<PropertyAppraisals> propertyApprisalSet = property.getPropertyAppraisalSet();
			if (propertyApprisalSet != null) {
				DateFormat zillowDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Iterator<PropertyAppraisals> itr = propertyApprisalSet.iterator();
				if (itr.hasNext()) {
					PropertyAppraisals propertyAppraisals = itr.next();
					if (propertyAppraisals.getLastSoldDate() != null) {
						propertyDetailModel
								.setLastSoldDate(zillowDateFormat.format(propertyAppraisals.getLastSoldDate()));
					}
					propertyDetailModel.setLastSoldPrice(propertyAppraisals.getLastSoldPrice());
					propertyDetailModel.setRentValue(propertyAppraisals.getRentValue());
					propertyDetailModel.setTaxAssessmentValue(propertyAppraisals.getTaxAssessmentValue());
					propertyDetailModel.setTaxAssessmentYear(propertyAppraisals.getTaxAssessmentYear());
					propertyDetailModel.setPropertyDetailUrl(propertyAppraisals.getPropertyDetailUrl());
					propertyDetailModel.setPropertyMapUrl(propertyAppraisals.getPropertyMapUrl());
					propertyDetailModel.setPropertyGraphAndDataUrl(propertyAppraisals.getPropertyGraphAndDataUrl());
					propertyDetailModel.setMarketValue(propertyAppraisals.getMarketValue());
					propertyDetailModel.setMarketUpdateDate(
							zillowDateFormat.format(propertyAppraisals.getMarketValueUpdatedDate()));

				}
			}
			propertyDetailModel.setZip(Objects.toString(property.getZip(), ""));
			propertyDetailModel.setAge(property.getAge());
			propertyDetailModel.setCity(property.getCity());
			propertyDetailModel.setStreetAddress(property.getStreetAddress());
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
			model.setNoOfPropUnits(note.getNumberOfPropUnit());
			propertyDetailModel.setSizeSF(property.getSizeSF());
			propertyDetailModel.setState(property.getState());
			propertyDetailModel.setStreetAddress(property.getStreetAddress());
			propertyDetailModel.setSubdividable(property.getSubdividable());
		}
		DemographicDetailModel demoGraphicDetailModel = new DemographicDetailModel();
		model.setDemoGraphicDetailModel(demoGraphicDetailModel);

		model.setPropertyDetailModel(propertyDetailModel);

		return model;
	}

}
