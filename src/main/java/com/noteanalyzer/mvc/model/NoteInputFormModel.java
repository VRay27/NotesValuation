package com.noteanalyzer.mvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Digits;

public class NoteInputFormModel  extends RequestStatusModel implements Serializable {

	/**
	 * This class will hold the data for Note input form.
	 * 
	 * @author Arvind Ray
	 */
	private static final long serialVersionUID = 7343742294405817564L;

	private long userId;
	private long noteId;
	
	private AddressModel addressModel;
	private List<LoanTypeModel> loanTypeList;
	private List<NoteTypeModel> noteTypeList;
	private String selLoanType;
	private String selNoteType;
	private List<PropertyTypeModel> propTypeList;
	private String selPropType;
	@NotNull(message = "Note Date cannot be null")
	private String noteDate;
	@NotNull(message = "Unpaid Balance cannot be null")
	//@Digits(message = "Unpaid Balance cannot be greater than 20 digit", fraction = 2, integer = 20)
	private String upb;
	private String rate;
	//@Digits(message = "PDI Payment cannot be greater than 20 digit", fraction = 2, integer = 20)
	private String pdiPayment;
	//@Digits(message = "TDI Payment cannot be greater than 20 digit", fraction = 2, integer = 20)
	private String tdiPayment;
	//@Digits(message = "Original Term cannot be greater than 20 digit", fraction = 2, integer = 20)
	private String originalTerm;
	//@Digits(message = "original Principle Balance cannot be greater than 20 digit", fraction = 2, integer = 20)
	private String originalPrincipleBalance;
	private String notePosition = "1";
	private String noteScoreByUser;
	private String estimatedMarketValue="";
	private String remainingPayment="";
	private String selCity;
	private String selState;
	private String zipCode;
	private String streetAddress;
	private String lastPaymentRecievedDate;
	private String noOfLatePayment;
	private String noOfUnits;
	
	private String borrowerCreditScore;
	private String notePrice;
	private String hoaFees;
	private String borrowerName;
	private String subscribe;
	private String yieldValue;
	
	private String currentLTV;
	private String estimatedITV;
	private String currentITV;
	private String estimatedLTV;
	private String ROI;
	private String noOfPropUnits;
	private PropertyDetailModel propertyDetailModel;
	private DemographicDetailModel demoGraphicDetailModel;

	
	
	
	/**
	 * @return the noOfPropUnits
	 */
	public String getNoOfPropUnits() {
		return noOfPropUnits;
	}

	/**
	 * @param noOfPropUnits the noOfPropUnits to set
	 */
	public void setNoOfPropUnits(String noOfPropUnits) {
		this.noOfPropUnits = noOfPropUnits;
	}

	/**
	 * @return the propertyDetailModel
	 */
	public PropertyDetailModel getPropertyDetailModel() {
		return propertyDetailModel;
	}

	/**
	 * @param propertyDetailModel the propertyDetailModel to set
	 */
	public void setPropertyDetailModel(PropertyDetailModel propertyDetailModel) {
		this.propertyDetailModel = propertyDetailModel;
	}

	/**
	 * @return the demoGraphicDetailModel
	 */
	public DemographicDetailModel getDemoGraphicDetailModel() {
		return demoGraphicDetailModel;
	}

	/**
	 * @param demoGraphicDetailModel the demoGraphicDetailModel to set
	 */
	public void setDemoGraphicDetailModel(DemographicDetailModel demoGraphicDetailModel) {
		this.demoGraphicDetailModel = demoGraphicDetailModel;
	}

	/**
	 * @return the rOI
	 */
	public String getROI() {
		return ROI;
	}

	/**
	 * @param rOI the rOI to set
	 */
	public void setROI(String rOI) {
		ROI = rOI;
	}

	
	/**
	 * @return the subscribe
	 */
	public String getSubscribe() {
		return subscribe;
	}

	/**
	 * @param subscribe the subscribe to set
	 */
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * @return the addressModel
	 */
	public AddressModel getAddressModel() {
		return addressModel;
	}

	/**
	 * @param addressModel the addressModel to set
	 */
	public void setAddressModel(AddressModel addressModel) {
		this.addressModel = addressModel;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the loanTypeList
	 */
	public List<LoanTypeModel> getLoanTypeList() {
		return loanTypeList;
	}

	/**
	 * @param loanTypeList the loanTypeList to set
	 */
	public void setLoanTypeList(List<LoanTypeModel> loanTypeList) {
		this.loanTypeList = loanTypeList;
	}

	/**
	 * @return the noteTypeList
	 */
	public List<NoteTypeModel> getNoteTypeList() {
		return noteTypeList;
	}

	/**
	 * @param noteTypeList the noteTypeList to set
	 */
	public void setNoteTypeList(List<NoteTypeModel> noteTypeList) {
		this.noteTypeList = noteTypeList;
	}

	

	/**
	 * @return the propTypeList
	 */
	public List<PropertyTypeModel> getPropTypeList() {
		return propTypeList;
	}

	/**
	 * @param propTypeList the propTypeList to set
	 */
	public void setPropTypeList(List<PropertyTypeModel> propTypeList) {
		this.propTypeList = propTypeList;
	}

	
	
	
	/**
	 * @return the selLoanType
	 */
	public String getSelLoanType() {
		return selLoanType;
	}

	/**
	 * @param selLoanType the selLoanType to set
	 */
	public void setSelLoanType(String selLoanType) {
		this.selLoanType = selLoanType;
	}

	/**
	 * @return the selNoteType
	 */
	public String getSelNoteType() {
		return selNoteType;
	}

	/**
	 * @param selNoteType the selNoteType to set
	 */
	public void setSelNoteType(String selNoteType) {
		this.selNoteType = selNoteType;
	}

	/**
	 * @return the selPropType
	 */
	public String getSelPropType() {
		return selPropType;
	}

	/**
	 * @param selPropType the selPropType to set
	 */
	public void setSelPropType(String selPropType) {
		this.selPropType = selPropType;
	}

	/**
	 * @return the noteDate
	 */
	public String getNoteDate() {
		return noteDate;
	}

	/**
	 * @param noteDate the noteDate to set
	 */
	public void setNoteDate(String noteDate) {
		this.noteDate = noteDate;
	}

	/**
	 * @return the upb
	 */
	public String getUpb() {
		return upb;
	}

	/**
	 * @param upb the upb to set
	 */
	public void setUpb(String upb) {
		this.upb = upb;
	}

	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

	/**
	 * @return the pdiPayment
	 */
	public String getPdiPayment() {
		return pdiPayment;
	}

	/**
	 * @param pdiPayment the pdiPayment to set
	 */
	public void setPdiPayment(String pdiPayment) {
		this.pdiPayment = pdiPayment;
	}

	/**
	 * @return the tdiPayment
	 */
	public String getTdiPayment() {
		return tdiPayment;
	}

	/**
	 * @param tdiPayment the tdiPayment to set
	 */
	public void setTdiPayment(String tdiPayment) {
		this.tdiPayment = tdiPayment;
	}

	/**
	 * @return the originalTerm
	 */
	public String getOriginalTerm() {
		return originalTerm;
	}

	/**
	 * @param originalTerm the originalTerm to set
	 */
	public void setOriginalTerm(String originalTerm) {
		this.originalTerm = originalTerm;
	}

	/**
	 * @return the originalPrincipleBalance
	 */
	public String getOriginalPrincipleBalance() {
		return originalPrincipleBalance;
	}

	/**
	 * @param originalPrincipleBalance the originalPrincipleBalance to set
	 */
	public void setOriginalPrincipleBalance(String originalPrincipleBalance) {
		this.originalPrincipleBalance = originalPrincipleBalance;
	}

	/**
	 * @return the notePosition
	 */
	public String getNotePosition() {
		return notePosition;
	}

	/**
	 * @param notePosition the notePosition to set
	 */
	public void setNotePosition(String notePosition) {
		this.notePosition = notePosition;
	}

	/**
	 * @return the noteScoreByUser
	 */
	public String getNoteScoreByUser() {
		return noteScoreByUser;
	}

	/**
	 * @param noteScoreByUser the noteScoreByUser to set
	 */
	public void setNoteScoreByUser(String noteScoreByUser) {
		this.noteScoreByUser = noteScoreByUser;
	}

	/**
	 * @return the estimatedMarketValue
	 */
	public String getEstimatedMarketValue() {
		return estimatedMarketValue;
	}

	/**
	 * @param estimatedMarketValue the estimatedMarketValue to set
	 */
	public void setEstimatedMarketValue(String estimatedMarketValue) {
		this.estimatedMarketValue = estimatedMarketValue;
	}

	/**
	 * @return the remainingPayment
	 */
	public String getRemainingPayment() {
		return remainingPayment;
	}

	/**
	 * @param remainingPayment the remainingPayment to set
	 */
	public void setRemainingPayment(String remainingPayment) {
		this.remainingPayment = remainingPayment;
	}

	/**
	 * @return the selCity
	 */
	public String getSelCity() {
		return selCity;
	}

	/**
	 * @param selCity the selCity to set
	 */
	public void setSelCity(String selCity) {
		this.selCity = selCity;
	}

	/**
	 * @return the selState
	 */
	public String getSelState() {
		return selState;
	}

	/**
	 * @param selState the selState to set
	 */
	public void setSelState(String selState) {
		this.selState = selState;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * @param streetAddress the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * @return the lastPaymentRecievedDate
	 */
	public String getLastPaymentRecievedDate() {
		return lastPaymentRecievedDate;
	}

	/**
	 * @param lastPaymentRecievedDate the lastPaymentRecievedDate to set
	 */
	public void setLastPaymentRecievedDate(String lastPaymentRecievedDate) {
		this.lastPaymentRecievedDate = lastPaymentRecievedDate;
	}

	/**
	 * @return the noOfLatePayment
	 */
	public String getNoOfLatePayment() {
		return noOfLatePayment;
	}

	/**
	 * @param noOfLatePayment the noOfLatePayment to set
	 */
	public void setNoOfLatePayment(String noOfLatePayment) {
		this.noOfLatePayment = noOfLatePayment;
	}

	/**
	 * @return the noOfUnits
	 */
	public String getNoOfUnits() {
		return noOfUnits;
	}

	/**
	 * @param noOfUnits the noOfUnits to set
	 */
	public void setNoOfUnits(String noOfUnits) {
		this.noOfUnits = noOfUnits;
	}

	/**
	 * @return the borrowerCreditScore
	 */
	public String getBorrowerCreditScore() {
		return borrowerCreditScore;
	}

	/**
	 * @param borrowerCreditScore the borrowerCreditScore to set
	 */
	public void setBorrowerCreditScore(String borrowerCreditScore) {
		this.borrowerCreditScore = borrowerCreditScore;
	}

	/**
	 * @return the notePrice
	 */
	public String getNotePrice() {
		return notePrice;
	}

	/**
	 * @param notePrice the notePrice to set
	 */
	public void setNotePrice(String notePrice) {
		this.notePrice = notePrice;
	}

	/**
	 * @return the hoaFees
	 */
	public String getHoaFees() {
		return hoaFees;
	}

	/**
	 * @param hoaFees the hoaFees to set
	 */
	public void setHoaFees(String hoaFees) {
		this.hoaFees = hoaFees;
	}

	/**
	 * @return the borrowerName
	 */
	public String getBorrowerName() {
		return borrowerName;
	}

	/**
	 * @param borrowerName the borrowerName to set
	 */
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	/**
	 * @return the yieldValue
	 */
	public String getYieldValue() {
		return yieldValue;
	}

	/**
	 * @param yieldValue the yieldValue to set
	 */
	public void setYieldValue(String yieldValue) {
		this.yieldValue = yieldValue;
	}

	

	/**
	 * @return the currentLTV
	 */
	public String getCurrentLTV() {
		return currentLTV;
	}

	/**
	 * @param currentLTV the currentLTV to set
	 */
	public void setCurrentLTV(String currentLTV) {
		this.currentLTV = currentLTV;
	}

	/**
	 * @return the estimatedITV
	 */
	public String getEstimatedITV() {
		return estimatedITV;
	}

	/**
	 * @param estimatedITV the estimatedITV to set
	 */
	public void setEstimatedITV(String estimatedITV) {
		this.estimatedITV = estimatedITV;
	}

	/**
	 * @return the currentITV
	 */
	public String getCurrentITV() {
		return currentITV;
	}

	/**
	 * @param currentITV the currentITV to set
	 */
	public void setCurrentITV(String currentITV) {
		this.currentITV = currentITV;
	}

	/**
	 * @return the estimatedLTV
	 */
	public String getEstimatedLTV() {
		return estimatedLTV;
	}

	/**
	 * @param estimatedLTV the estimatedLTV to set
	 */
	public void setEstimatedLTV(String estimatedLTV) {
		this.estimatedLTV = estimatedLTV;
	}

	/**
	 * @return the noteId
	 */
	public long getNoteId() {
		return noteId;
	}

	/**
	 * @param noteId the noteId to set
	 */
	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}
	
	
	
}
