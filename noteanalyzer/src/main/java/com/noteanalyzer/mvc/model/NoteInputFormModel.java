package com.noteanalyzer.mvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NoteInputFormModel implements Serializable {

	/**
	 * This class will hold the data for Note input form.
	 * 
	 * @author Arvind Ray
	 */
	private static final long serialVersionUID = 7343742294405817564L;

	private List<NoteTypeModel> noteTypeList;
	private NoteTypeModel selNoteType;
	private List<PropertyTypeModel> propTypeList;
	private PropertyTypeModel selPropType;
	private AddressModel addressModel;
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
	private int notePosition;
	private BigDecimal userScore;
	
	private String selCity;
	private String selState;
	private String zipCode;
	
	private Integer noOfLatePayment;
	

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
	 * @return the selAddress
	 */
	public AddressModel getAddress() {
		if (addressModel == null) {
			addressModel = new AddressModel();
		}
		return addressModel;
	}

	/**
	 * @param selAddress
	 *            the selAddress to set
	 */
	public void setAddress(AddressModel addressModel) {
		this.addressModel = addressModel;
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
	 * @return the notePosition
	 */
	public int getNotePosition() {
		return notePosition;
	}

	/**
	 * @param notePosition the notePosition to set
	 */
	public void setNotePosition(int notePosition) {
		this.notePosition = notePosition;
	}

	/**
	 * @return the userScore
	 */
	public BigDecimal getUserScore() {
		return userScore;
	}

	/**
	 * @param userScore the userScore to set
	 */
	public void setUserScore(BigDecimal userScore) {
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
	 * @return the noOfLatePayment
	 */
	public Integer getNoOfLatePayment() {
		return noOfLatePayment;
	}

	/**
	 * @param noOfLatePayment the noOfLatePayment to set
	 */
	public void setNoOfLatePayment(Integer noOfLatePayment) {
		this.noOfLatePayment = noOfLatePayment;
	}



}
