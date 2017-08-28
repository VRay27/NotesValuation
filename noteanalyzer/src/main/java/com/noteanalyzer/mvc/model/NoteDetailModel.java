package com.noteanalyzer.mvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NoteDetailModel  extends RequestStatusModel implements Serializable {

	/**
	 * This class will hold the data for Note input form.
	 * 
	 * @author Arvind Ray
	 */
	private static final long serialVersionUID = 7345264294405815474L;

	private long userId;
	private long noteId;
	
	private List<LoanTypeModel> noteTypeList;
	private LoanTypeModel selNoteType;
	private List<PropertyTypeModel> propTypeList;
	private PropertyTypeModel selPropType;
	private String noteDate;
	private String upb;
	private String rate;
	private String pdiPayment;
	private String tdiPayment;
	private String originalTerm;
	private String originalPrincipleBalance;
	private String paymentHistory;
	private String noOfPaymentRemaining;
	private String salePrice;
	private String selPerforming;
	private String notePosition;
	private String userScore;
	
	private String selCity;
	private String selState;
	private String zipCode;
	private String streetAddress;
	
	private String noOfLatePayment;
	
	private String borrowerCreditScore;
	private String notePrice;
	
	private String performing;
	private String remainingNoOfPayment;
	private String originalPropertyValue;
	
	private String yieldValue;
	
	private String currentLTV;
	private String estimatedITV;
	private String currentITV;
	private String estimatedLTV;
	private String borrowerName;
	
	private boolean isSubscribe;
	
	private String ROI;
	
	private PropertyDetailModel propertyDetailModel;
	
	
	private DemographicDetailModel demoGraphicDetailModel;
	
	

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
	 * @return the isSubscribe
	 */
	public boolean isSubscribe() {
		return isSubscribe;
	}

	/**
	 * @param isSubscribe the isSubscribe to set
	 */
	public void setSubscribe(boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
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

	/**
	 * @return the performing
	 */
	public String getPerforming() {
		return performing;
	}

	/**
	 * @param performing the performing to set
	 */
	public void setPerforming(String performing) {
		this.performing = performing;
	}

	
	/**
	 * @return the noteTypeList
	 */
	public List<LoanTypeModel> getNoteTypeList() {
		if (noteTypeList == null) {
			noteTypeList = new ArrayList<LoanTypeModel>();
		}
		return noteTypeList;
	}

	/**
	 * @param noteTypeList
	 *            the noteTypeList to set
	 */
	public void setNoteTypeList(List<LoanTypeModel> noteTypeList) {
		this.noteTypeList = noteTypeList;
	}

	/**
	 * @return the selNoteType
	 */
	public LoanTypeModel getSelNoteType() {
		if (selNoteType == null) {
			selNoteType = new LoanTypeModel();
		}
		return selNoteType;
	}

	/**
	 * @param selNoteType
	 *            the selNoteType to set
	 */
	public void setSelNoteType(LoanTypeModel selNoteType) {
		this.selNoteType = selNoteType;
	}

	/**
	 * @return the propTypeList
	 */
	public List<PropertyTypeModel> getPropTypeList() {
		if (propTypeList == null) {
			propTypeList = new ArrayList<PropertyTypeModel>();
		}
		return propTypeList;
	}

	/**
	 * @param propTypeList
	 *            the propTypeList to set
	 */
	public void setPropTypeList(List<PropertyTypeModel> propTypeList) {
		this.propTypeList = propTypeList;
	}

	/**
	 * @return the selPropType
	 */
	public PropertyTypeModel getSelPropType() {
		return selPropType;
	}

	/**
	 * @param selPropType
	 *            the selPropType to set
	 */
	public void setSelPropType(PropertyTypeModel selPropType) {
		this.selPropType = selPropType;
	}

	
	/**
	 * @return the noteDate
	 */
	public String getNoteDate() {
		return noteDate;
	}

	/**
	 * @param noteDate
	 *            the noteDate to set
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
	 * @return the paymentHistory
	 */
	public String getPaymentHistory() {
		return paymentHistory;
	}

	/**
	 * @param paymentHistory the paymentHistory to set
	 */
	public void setPaymentHistory(String paymentHistory) {
		this.paymentHistory = paymentHistory;
	}

	/**
	 * @return the noOfPaymentRemaining
	 */
	public String getNoOfPaymentRemaining() {
		return noOfPaymentRemaining;
	}

	/**
	 * @param noOfPaymentRemaining the noOfPaymentRemaining to set
	 */
	public void setNoOfPaymentRemaining(String noOfPaymentRemaining) {
		this.noOfPaymentRemaining = noOfPaymentRemaining;
	}

	/**
	 * @return the salePrice
	 */
	public String getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	
	

	/**
	 * @return the selPerforming
	 */
	public String getSelPerforming() {
		return selPerforming;
	}

	/**
	 * @param selPerforming the selPerforming to set
	 */
	public void setSelPerforming(String selPerforming) {
		this.selPerforming = selPerforming;
	}

	

	/**
	 * @return the userScore
	 */
	public String getUserScore() {
		return userScore;
	}

	/**
	 * @param userScore the userScore to set
	 */
	public void setUserScore(String userScore) {
		this.userScore = userScore;
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
	 * @return the remainingNoOfPayment
	 */
	public String getRemainingNoOfPayment() {
		return remainingNoOfPayment;
	}

	/**
	 * @param remainingNoOfPayment the remainingNoOfPayment to set
	 */
	public void setRemainingNoOfPayment(String remainingNoOfPayment) {
		this.remainingNoOfPayment = remainingNoOfPayment;
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
	 * @return the originalPropertyValue
	 */
	public String getOriginalPropertyValue() {
		return originalPropertyValue;
	}

	/**
	 * @param originalPropertyValue the originalPropertyValue to set
	 */
	public void setOriginalPropertyValue(String originalPropertyValue) {
		this.originalPropertyValue = originalPropertyValue;
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

	

}
