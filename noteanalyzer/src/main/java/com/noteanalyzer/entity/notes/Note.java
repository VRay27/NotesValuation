package com.noteanalyzer.entity.notes;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name = "NOTE")
@ToString(callSuper = true)
@NamedQueries({
		@NamedQuery(name = Note.GET_NOTE_DETAILS_BY_NOTEID, query = "select n from Note n where  n.noteId =:noteId"),
		@NamedQuery(name = Note.GET_NOTE_DETAILS_BY_USER, query = "select n from Note n where   n.userId =:userId order by updatedDate desc "),
		@NamedQuery(name = Note.GET_NOTE_DETAILS_BY_USER_NOTE_ID, query = "select n from Note n, User u where u.userId = n.userId and  n.noteId =:noteId and u.emailID =:emailID")})
public class Note extends AbstractEntity {
	private static final long serialVersionUID = -8179556227491337368L;

	public static final String GET_NOTE_DETAILS_BY_NOTEID = "GET_NOTE_DETAILS_BY_NOTEID";
	public static final String GET_NOTE_DETAILS_BY_USER = "GET_NOTE_DETAILS_BY_USER";
	public static final String 	GET_NOTE_DETAILS_BY_USER_NOTE_ID = "GET_NOTE_DETAILS_BY_USER_NOTE_ID";


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTE_ID")
	private long noteId;
	
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "FACE_VALUE")
	private Double faceValue;
	
	@Column(name = "SALE_PRICE")
	private Double salePrice;


	@Column(name = "NOTE_TYPE")
	private String noteType;
	
	@Column(name = "LOAN_TYPE")
	private String loanType;

	@Column(name = "NOTE_POSITION")
	private Long notePosition;
	

	@Column(name = "PDI_PAYMENT")
	private Double pdiPayment;
	
	@Column(name = "TDI_PAYMENT")
	private Double tdiPayment;

	@Column(name = "BORROWER_CREDIT_SCORE")
	private String borrowerCreditScore;

	@Column(name = "INTEREST_RATE_INITIAL")
	private Double interestRateInitial;

	@Column(name = "ORIGINATION_DATE")
	private Date originationDate;
	
	@Column(name = "last_payment_recieved_date")
	private Date lastPaymentRecievedDate;

	@Column(name = "TERM_MONTHS")
	private Double termMonths;
	
	@Column(name = "LATE_PAYMENTS")
	private Long latePayments;

	@Column(name = "SEARCH_NAME")
	private String searchName;

	@Column(name = "SCORE_BY_USER")
	private Double userScore;

	@Column(name = "SYSTEM_ASSIGNED_SCORE")
	private Double systemScore;

	
	@Column(name = "STORE_NOTE_ID")
	private Long storeNoteId;

	@Column(name = "STORE_NAME")
	private String storeName;
	
	@Column(name = "PROPERTY_VALUE_AT_ORIGINATION")
	private Double propertyValueAtOrigination;
	
	@Column(name = "NOTE_PRICE")
	private Double notePrice;
	
	@Column(name = "REMAINING_NO_OF_PAYMENT")
	private Integer remainingNoOfPayment;
	
	@Column(name = "ORIGINAL_PROPERTY_VALUE")
	private Double originalPropertyValue;
	
	@Column(name = "ORIGINAL_LOAN_BAL")
	private Double originalLoanBal;
	
	@Column(name = "ROI")
	private Double roi;
	
	@Column(name = "YIELD")
	private Double yield;
	
	@Column(name = "Appraised_LTV")
	private Double appraisedLTV;
	
	@Column(name = "Appraised_ITV")
	private Double appraisedITV;
	
	@Column(name = "ORIGINAL_LTV")
	private Double estimatedLTV;
	
	@Column(name = "Estimated_ITV")
	private Double estimatedITV;
	
	@Column(name = "UNPAID_BALANCE")
	private Double unpaidBalance;
	
	@Column(name = "ESTIMATED_MARKET_VALUE")
	private String estimatedMarketValue;
	
	@Column(name = "Borrower_Name")
	private String borrowerName;
	
	@Column(name = "HOA_FEE")
	private String hoaFee;

	@Column(name = "NO_OF_PROP_UNIT")
	private String numberOfPropUnit;
	
	@Column(name = "CREATED_DATE_TIME")
	private ZonedDateTime createdDate;
	
	@Column(name = "UPDATED_DATE_TIME")
	private ZonedDateTime updatedDate;

	@ManyToOne(fetch = FetchType.LAZY,cascade={ CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "PROPERTY_ID",referencedColumnName = "PROPERTY_ID", nullable = false)
	private Property propertyId;

	
	
	/**
	 * @return the createdDate
	 */
	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param zonedDateTime the createdDate to set
	 */
	public void setCreatedDate(ZonedDateTime zonedDateTime) {
		this.createdDate = zonedDateTime;
	}

	/**
	 * @return the updatedDate
	 */
	public ZonedDateTime getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(ZonedDateTime updatedDate) {
		this.updatedDate = updatedDate;
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
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the faceValue
	 */
	public Double getFaceValue() {
		return faceValue;
	}

	/**
	 * @param faceValue the faceValue to set
	 */
	public void setFaceValue(Double faceValue) {
		this.faceValue = faceValue;
	}

	/**
	 * @return the salePrice
	 */
	public Double getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	
	/**
	 * @return the noteType
	 */
	public String getNoteType() {
		return noteType;
	}

	/**
	 * @param noteType the noteType to set
	 */
	public void setNoteType(String noteType) {
		this.noteType = noteType;
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
	 * @return the interestRateInitial
	 */
	public Double getInterestRateInitial() {
		return interestRateInitial;
	}

	/**
	 * @param interestRateInitial the interestRateInitial to set
	 */
	public void setInterestRateInitial(Double interestRateInitial) {
		this.interestRateInitial = interestRateInitial;
	}

	/**
	 * @return the originationDate
	 */
	public Date getOriginationDate() {
		return originationDate;
	}

	/**
	 * @param originationDate the originationDate to set
	 */
	public void setOriginationDate(Date originationDate) {
		this.originationDate = originationDate;
	}

	
	/**
	 * @return the termMonths
	 */
	public Double getTermMonths() {
		return termMonths;
	}

	/**
	 * @param termMonths the termMonths to set
	 */
	public void setTermMonths(Double termMonths) {
		this.termMonths = termMonths;
	}

	
	/**
	 * @return the notePosition
	 */
	public Long getNotePosition() {
		return notePosition;
	}

	/**
	 * @param notePosition the notePosition to set
	 */
	public void setNotePosition(Long notePosition) {
		this.notePosition = notePosition;
	}

	/**
	 * @return the latePayments
	 */
	public Long getLatePayments() {
		return latePayments;
	}

	/**
	 * @param latePayments the latePayments to set
	 */
	public void setLatePayments(Long latePayments) {
		this.latePayments = latePayments;
	}

	/**
	 * @return the searchName
	 */
	public String getSearchName() {
		return searchName;
	}

	/**
	 * @param searchName the searchName to set
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * @return the userScore
	 */
	public Double getUserScore() {
		return userScore;
	}

	/**
	 * @param userScore the userScore to set
	 */
	public void setUserScore(Double userScore) {
		this.userScore = userScore;
	}

	/**
	 * @return the systemScore
	 */
	public Double getSystemScore() {
		return systemScore;
	}

	/**
	 * @param systemScore the systemScore to set
	 */
	public void setSystemScore(Double systemScore) {
		this.systemScore = systemScore;
	}

	/**
	 * @return the storeNoteId
	 */
	public Long getStoreNoteId() {
		return storeNoteId;
	}

	/**
	 * @param storeNoteId the storeNoteId to set
	 */
	public void setStoreNoteId(Long storeNoteId) {
		this.storeNoteId = storeNoteId;
	}

	/**
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return the propertyValueAtOrigination
	 */
	public Double getPropertyValueAtOrigination() {
		return propertyValueAtOrigination;
	}

	/**
	 * @param propertyValueAtOrigination the propertyValueAtOrigination to set
	 */
	public void setPropertyValueAtOrigination(Double propertyValueAtOrigination) {
		this.propertyValueAtOrigination = propertyValueAtOrigination;
	}

	

	/**
	 * @return the notePrice
	 */
	public Double getNotePrice() {
		return notePrice;
	}

	/**
	 * @param notePrice the notePrice to set
	 */
	public void setNotePrice(Double notePrice) {
		this.notePrice = notePrice;
	}

	/**
	 * @return the remainingNoOfPayment
	 */
	public Integer getRemainingNoOfPayment() {
		return remainingNoOfPayment;
	}

	/**
	 * @param remainingNoOfPayment the remainingNoOfPayment to set
	 */
	public void setRemainingNoOfPayment(Integer remainingNoOfPayment) {
		this.remainingNoOfPayment = remainingNoOfPayment;
	}

	/**
	 * @return the originalPropertyValue
	 */
	public Double getOriginalPropertyValue() {
		return originalPropertyValue;
	}

	/**
	 * @param originalPropertyValue the originalPropertyValue to set
	 */
	public void setOriginalPropertyValue(Double originalPropertyValue) {
		this.originalPropertyValue = originalPropertyValue;
	}

	/**
	 * @return the propertyId
	 */
	public Property getPropertyId() {
		return propertyId;
	}

	/**
	 * @param propertyId the propertyId to set
	 */
	public void setPropertyId(Property propertyId) {
		this.propertyId = propertyId;
	}
	
	
	/**
	 * @return the roi
	 */
	public Double getRoi() {
		return roi;
	}

	/**
	 * @param roi the roi to set
	 */
	public void setRoi(Double roi) {
		this.roi = roi;
	}

	/**
	 * @return the yield
	 */
	public Double getYield() {
		return yield;
	}

	/**
	 * @param yield the yield to set
	 */
	public void setYield(Double yield) {
		this.yield = yield;
	}

	
	
	/**
	 * @return the estimatedLTV
	 */
	public Double getEstimatedLTV() {
		return estimatedLTV;
	}

	/**
	 * @param estimatedLTV the estimatedLTV to set
	 */
	public void setEstimatedLTV(Double estimatedLTV) {
		this.estimatedLTV = estimatedLTV;
	}

	/**
	 * @return the appraisedLTV
	 */
	public Double getAppraisedLTV() {
		return appraisedLTV;
	}

	/**
	 * @param appraisedLTV the appraisedLTV to set
	 */
	public void setAppraisedLTV(Double appraisedLTV) {
		this.appraisedLTV = appraisedLTV;
	}

	/**
	 * @return the appraisedITV
	 */
	public Double getAppraisedITV() {
		return appraisedITV;
	}

	/**
	 * @param appraisedITV the appraisedITV to set
	 */
	public void setAppraisedITV(Double appraisedITV) {
		this.appraisedITV = appraisedITV;
	}

	/**
	 * @return the estimatedITV
	 */
	public Double getEstimatedITV() {
		return estimatedITV;
	}

	/**
	 * @param estimatedITV the estimatedITV to set
	 */
	public void setEstimatedITV(Double estimatedITV) {
		this.estimatedITV = estimatedITV;
	}

	/**
	 * @return the unpaidBalance
	 */
	public Double getUnpaidBalance() {
		return unpaidBalance;
	}

	/**
	 * @param unpaidBalance the unpaidBalance to set
	 */
	public void setUnpaidBalance(Double unpaidBalance) {
		this.unpaidBalance = unpaidBalance;
	}

	/**
	 * @return the pdiPayment
	 */
	public Double getPdiPayment() {
		return pdiPayment;
	}

	/**
	 * @param pdiPayment the pdiPayment to set
	 */
	public void setPdiPayment(Double pdiPayment) {
		this.pdiPayment = pdiPayment;
	}

	/**
	 * @return the tdiPayment
	 */
	public Double getTdiPayment() {
		return tdiPayment;
	}

	/**
	 * @param tdiPayment the tdiPayment to set
	 */
	public void setTdiPayment(Double tdiPayment) {
		this.tdiPayment = tdiPayment;
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
	 * @return the lastPaymentRecievedDate
	 */
	public Date getLastPaymentRecievedDate() {
		return lastPaymentRecievedDate;
	}

	/**
	 * @param lastPaymentRecievedDate the lastPaymentRecievedDate to set
	 */
	public void setLastPaymentRecievedDate(Date lastPaymentRecievedDate) {
		this.lastPaymentRecievedDate = lastPaymentRecievedDate;
	}

	/**
	 * @return the loanType
	 */
	public String getLoanType() {
		return loanType;
	}

	/**
	 * @param loanType the loanType to set
	 */
	public void setLoanType(String loanType) {
		this.loanType = loanType;
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
	 * @return the originalLoanBal
	 */
	public Double getOriginalLoanBal() {
		return originalLoanBal;
	}

	/**
	 * @param originalLoanBal the originalLoanBal to set
	 */
	public void setOriginalLoanBal(Double originalLoanBal) {
		this.originalLoanBal = originalLoanBal;
	}

	
	/**
	 * @return the hoaFee
	 */
	public String getHoaFee() {
		return hoaFee;
	}

	/**
	 * @param hoaFee the hoaFee to set
	 */
	public void setHoaFee(String hoaFee) {
		this.hoaFee = hoaFee;
	}

	
	/**
	 * @return the numberOfPropUnit
	 */
	public String getNumberOfPropUnit() {
		return numberOfPropUnit;
	}

	/**
	 * @param numberOfPropUnit the numberOfPropUnit to set
	 */
	public void setNumberOfPropUnit(String numberOfPropUnit) {
		this.numberOfPropUnit = numberOfPropUnit;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (noteId ^ (noteId >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (noteId != other.noteId)
			return false;
		return true;
	}



}
