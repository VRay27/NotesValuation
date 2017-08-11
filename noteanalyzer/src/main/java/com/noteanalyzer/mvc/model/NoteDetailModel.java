package com.noteanalyzer.mvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NoteDetailModel implements Serializable {

	/**
	 * This class will hold the data for Note input form.
	 * 
	 * @author Arvind Ray
	 */
	private static final long serialVersionUID = 7345264294405815474L;

	private long userId;
	private long noteId;
	
	private List<NoteTypeModel> noteTypeList;
	private NoteTypeModel selNoteType;
	private List<PropertyTypeModel> propTypeList;
	private PropertyTypeModel selPropType;
	private String noteDate;
	private BigDecimal upb;
	private BigDecimal rate;
	private BigDecimal pdiPayment;
	private BigDecimal tdiPayment;
	private BigDecimal originalTerm;
	private BigDecimal originalPrincipleBalance;
	private String paymentHistory;
	private BigDecimal noOfPaymentRemaining;
	private BigDecimal salePrice;
	private String selPerforming;
	private String notePosition;
	private String userScore;
	
	private String selCity;
	private String selState;
	private String zipCode;
	private String streetAddress;
	
	private String noOfLatePayment;
	
	private String borrowerCreditScore;
	private BigDecimal notePrice;
	
	private String performing;
	private String remainingNoOfPayment;
	private BigDecimal originalPropertyValue;
	
	private BigDecimal yieldValue;
	
	private BigDecimal currentEffectiveLTV;
	
	private BigDecimal originalLTV;
	
	private BigDecimal effectiveLTV;
	
	private BigDecimal effectiveInterestRate;
	
	
	private BigDecimal ROI;
	
	private PropertyDetailModel propertyDetailModel;
	
	
	private DemographicDetailModel demoGraphicDetailModel;
	
	

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
	public BigDecimal getROI() {
		return ROI;
	}

	/**
	 * @param rOI the rOI to set
	 */
	public void setROI(BigDecimal rOI) {
		ROI = rOI;
	}

	/**
	 * @return the effectiveLTV
	 */
	public BigDecimal getEffectiveLTV() {
		return effectiveLTV;
	}

	/**
	 * @param effectiveLTV the effectiveLTV to set
	 */
	public void setEffectiveLTV(BigDecimal effectiveLTV) {
		this.effectiveLTV = effectiveLTV;
	}

	/**
	 * @return the originalLTV
	 */
	public BigDecimal getOriginalLTV() {
		return originalLTV;
	}

	/**
	 * @param originalLTV the originalLTV to set
	 */
	public void setOriginalLTV(BigDecimal originalLTV) {
		this.originalLTV = originalLTV;
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
	public List<NoteTypeModel> getNoteTypeList() {
		if (noteTypeList == null) {
			noteTypeList = new ArrayList<NoteTypeModel>();
		}
		return noteTypeList;
	}

	/**
	 * @param noteTypeList
	 *            the noteTypeList to set
	 */
	public void setNoteTypeList(List<NoteTypeModel> noteTypeList) {
		this.noteTypeList = noteTypeList;
	}

	/**
	 * @return the selNoteType
	 */
	public NoteTypeModel getSelNoteType() {
		if (selNoteType == null) {
			selNoteType = new NoteTypeModel();
		}
		return selNoteType;
	}

	/**
	 * @param selNoteType
	 *            the selNoteType to set
	 */
	public void setSelNoteType(NoteTypeModel selNoteType) {
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
	public BigDecimal getUpb() {
		return upb;
	}

	/**
	 * @param upb the upb to set
	 */
	public void setUpb(BigDecimal upb) {
		this.upb = upb;
	}

	/**
	 * @return the rate
	 */
	public BigDecimal getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/**
	 * @return the pdiPayment
	 */
	public BigDecimal getPdiPayment() {
		return pdiPayment;
	}

	/**
	 * @param pdiPayment the pdiPayment to set
	 */
	public void setPdiPayment(BigDecimal pdiPayment) {
		this.pdiPayment = pdiPayment;
	}

	/**
	 * @return the tdiPayment
	 */
	public BigDecimal getTdiPayment() {
		return tdiPayment;
	}

	/**
	 * @param tdiPayment the tdiPayment to set
	 */
	public void setTdiPayment(BigDecimal tdiPayment) {
		this.tdiPayment = tdiPayment;
	}

	/**
	 * @return the originalTerm
	 */
	public BigDecimal getOriginalTerm() {
		return originalTerm;
	}

	/**
	 * @param originalTerm the originalTerm to set
	 */
	public void setOriginalTerm(BigDecimal originalTerm) {
		this.originalTerm = originalTerm;
	}

	/**
	 * @return the originalPrincipleBalance
	 */
	public BigDecimal getOriginalPrincipleBalance() {
		return originalPrincipleBalance;
	}

	/**
	 * @param originalPrincipleBalance the originalPrincipleBalance to set
	 */
	public void setOriginalPrincipleBalance(BigDecimal originalPrincipleBalance) {
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
	public BigDecimal getNoOfPaymentRemaining() {
		return noOfPaymentRemaining;
	}

	/**
	 * @param noOfPaymentRemaining the noOfPaymentRemaining to set
	 */
	public void setNoOfPaymentRemaining(BigDecimal noOfPaymentRemaining) {
		this.noOfPaymentRemaining = noOfPaymentRemaining;
	}

	/**
	 * @return the salePrice
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(BigDecimal salePrice) {
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
	public BigDecimal getNotePrice() {
		return notePrice;
	}

	/**
	 * @param notePrice the notePrice to set
	 */
	public void setNotePrice(BigDecimal notePrice) {
		this.notePrice = notePrice;
	}

	/**
	 * @return the originalPropertyValue
	 */
	public BigDecimal getOriginalPropertyValue() {
		return originalPropertyValue;
	}

	/**
	 * @param originalPropertyValue the originalPropertyValue to set
	 */
	public void setOriginalPropertyValue(BigDecimal originalPropertyValue) {
		this.originalPropertyValue = originalPropertyValue;
	}

	/**
	 * @return the yieldValue
	 */
	public BigDecimal getYieldValue() {
		return yieldValue;
	}

	/**
	 * @param yieldValue the yieldValue to set
	 */
	public void setYieldValue(BigDecimal yieldValue) {
		this.yieldValue = yieldValue;
	}

	/**
	 * @return the currentEffectiveLTV
	 */
	public BigDecimal getCurrentEffectiveLTV() {
		return currentEffectiveLTV;
	}

	/**
	 * @param currentEffectiveLTV the currentEffectiveLTV to set
	 */
	public void setCurrentEffectiveLTV(BigDecimal currentEffectiveLTV) {
		this.currentEffectiveLTV = currentEffectiveLTV;
	}

	/**
	 * @return the effectiveInterestRate
	 */
	public BigDecimal getEffectiveInterestRate() {
		return effectiveInterestRate;
	}

	/**
	 * @param effectiveInterestRate the effectiveInterestRate to set
	 */
	public void setEffectiveInterestRate(BigDecimal effectiveInterestRate) {
		this.effectiveInterestRate = effectiveInterestRate;
	}


}
