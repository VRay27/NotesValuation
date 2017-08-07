package com.noteanalyzer.entity.notes;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name = "NOTE")
@ToString(callSuper = true)
@NamedQueries({
		@NamedQuery(name = Note.GET_NOTE_DETAILS_BY_NOTEID, query = "select n from Note n, Property p where p.propertyId = n.propertyId and  n.noteId =:noteId"),
		@NamedQuery(name = Note.GET_NOTE_DETAILS_BY_USER, query = "select n from Note n, Property p where p.propertyId = n.propertyId and  n.userId =:userId")})
public class Note extends AbstractEntity {
	private static final long serialVersionUID = -8179556227491337368L;

	public static final String GET_NOTE_DETAILS_BY_NOTEID = "GET_NOTE_DETAILS_BY_NOTEID";
	public static final String GET_NOTE_DETAILS_BY_USER = "GET_NOTE_DETAILS_BY_USER";


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTE_ID")
	private int noteId;
	
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "FACE_VALUE")
	private BigDecimal faceValue;
	
	@Column(name = "SALE_PRICE")
	private BigDecimal salePrice;

	@Column(name = "PERFORMING")
	private String performing;

	@Column(name = "NOTE_TYPE")
	private String noteType;

	@Column(name = "NOTE_POSITION")
	private Integer notePosition;

	@Column(name = "BORROWER_CREDIT_SCORE")
	private String borrowerCreditScore;

	@Column(name = "INTEREST_RATE_INITIAL")
	private BigDecimal interestRateInitial;

	@Column(name = "ORIGINATION_DATE")
	private Date originationDate;

	@Column(name = "TERM_MONTHS")
	private BigDecimal termMonths;
	
	@Column(name = "LATE_PAYMENTS")
	private Integer latePayments;

	@Column(name = "SEARCH_NAME")
	private String searchName;

	@Column(name = "SCORE_BY_USER")
	private BigDecimal userScore;

	@Column(name = "SYSTEM_ASSIGNED_SCORE")
	private BigDecimal systemScore;

	
	@Column(name = "STORE_NOTE_ID")
	private Long storeNoteId;

	@Column(name = "STORE_NAME")
	private String storeName;
	
	@Column(name = "PROPERTY_VALUE_AT_ORIGINATION")
	private BigDecimal propertyValueAtOrigination;
	
	@Column(name = "NOTE_PRICE")
	private BigDecimal notePrice;
	
	@Column(name = "REMAINING_NO_OF_PAYMENT")
	private Integer remainingNoOfPayment;
	
	@Column(name = "ORIGINAL_PROPERTY_VALUE")
	private BigDecimal originalPropertyValue;
	
	@Column(name = "ROI")
	private BigDecimal roi;
	
	@Column(name = "YIELD")
	private BigDecimal yield;
	
	@Column(name = "CURRENT_EFFECTIVE_LTV")
	private BigDecimal currentEffectiveLTV;
	
	@Column(name = "EFFECTIVE_LTV")
	private BigDecimal effectiveLTV;
	
	@Column(name = "ORIGINAL_LTV")
	private BigDecimal originalLTV;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "PROPERTY_ID")
	private Property propertyId;

	/**
	 * @return the noteId
	 */
	public int getNoteId() {
		return noteId;
	}

	/**
	 * @param noteId the noteId to set
	 */
	public void setNoteId(int noteId) {
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
	public BigDecimal getFaceValue() {
		return faceValue;
	}

	/**
	 * @param faceValue the faceValue to set
	 */
	public void setFaceValue(BigDecimal faceValue) {
		this.faceValue = faceValue;
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
	 * @return the notePosition
	 */
	public Integer getNotePosition() {
		return notePosition;
	}

	/**
	 * @param notePosition the notePosition to set
	 */
	public void setNotePosition(Integer notePosition) {
		this.notePosition = notePosition;
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
	public BigDecimal getInterestRateInitial() {
		return interestRateInitial;
	}

	/**
	 * @param interestRateInitial the interestRateInitial to set
	 */
	public void setInterestRateInitial(BigDecimal interestRateInitial) {
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
	public BigDecimal getTermMonths() {
		return termMonths;
	}

	/**
	 * @param termMonths the termMonths to set
	 */
	public void setTermMonths(BigDecimal termMonths) {
		this.termMonths = termMonths;
	}

	/**
	 * @return the latePayments
	 */
	public Integer getLatePayments() {
		return latePayments;
	}

	/**
	 * @param latePayments the latePayments to set
	 */
	public void setLatePayments(Integer latePayments) {
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
	 * @return the systemScore
	 */
	public BigDecimal getSystemScore() {
		return systemScore;
	}

	/**
	 * @param systemScore the systemScore to set
	 */
	public void setSystemScore(BigDecimal systemScore) {
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
	public BigDecimal getPropertyValueAtOrigination() {
		return propertyValueAtOrigination;
	}

	/**
	 * @param propertyValueAtOrigination the propertyValueAtOrigination to set
	 */
	public void setPropertyValueAtOrigination(BigDecimal propertyValueAtOrigination) {
		this.propertyValueAtOrigination = propertyValueAtOrigination;
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
	public BigDecimal getRoi() {
		return roi;
	}

	/**
	 * @param roi the roi to set
	 */
	public void setRoi(BigDecimal roi) {
		this.roi = roi;
	}

	/**
	 * @return the yield
	 */
	public BigDecimal getYield() {
		return yield;
	}

	/**
	 * @param yield the yield to set
	 */
	public void setYield(BigDecimal yield) {
		this.yield = yield;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + noteId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Note)) {
			return false;
		}
		Note other = (Note) obj;
		if (noteId != other.noteId) {
			return false;
		}
		return true;
	}

}
