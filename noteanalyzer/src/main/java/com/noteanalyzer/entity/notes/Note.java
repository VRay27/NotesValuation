package com.noteanalyzer.entity.notes;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name = "NOTE")
@ToString(callSuper = true)
@NamedQueries({
		@NamedQuery(name = Note.GET_NOTE_DETAILS_BY_NOTEID, query = "select n from Note n where n.noteId =:noteId"),
		@NamedQuery(name = Note.GET_NOTE_DETAILS_BY_USER, query = "select n from Note n where n.createdBy =:createdBy")})
public class Note extends AbstractEntity {
	private static final long serialVersionUID = -8179556227491337368L;

	public static final String GET_NOTE_DETAILS_BY_NOTEID = "GET_NOTE_DETAILS_BY_NOTEID";
	public static final String GET_NOTE_DETAILS_BY_USER = "GET_NOTE_DETAILS_BY_USER";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTE_ID")
	private int noteId;

	@Column(name = "FACE_VALUE")
	private Integer faceValue;

	@Column(name = "DISCOUNT")
	private Float discount;

	@Column(name = "PERFORMANCE")
	private String performance;

	@Column(name = "NOTE_TYPE")
	private String noteType;

	@Column(name = "NOTE_POSITION")
	private Integer notePosition;

	@Column(name = "PROP_TYPE")
	private String propertyType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PID")
	private Property property;

	@Column(name = "BORROWER_TYPE")
	private String borrowerType;

	@Column(name = "USER_USER_ID")
	private Integer userUserId;

	@Column(name = "MONTHS_TO_MATURITY")
	private Integer monthsToMaturity;

	@Column(name = "INTREST_RATE")
	private Float intrestRate;

	@Column(name = "NEXT_INTREST_ADJUSTMENT_DATE")
	private Date nextIntrestAdjustmentDate;

	@Column(name = "INTREST_RATE_ADJUSTMENT_RULE")
	private String intrestRateAdjustmentRule;

	@Column(name = "USER_SCORE")
	private Integer userScore;

	@Column(name = "SYSTEM_SCORE")
	private Integer systemScore;

	@Column(name = "VENDOR_NOTE_ID")
	private String vendorNoteId;

	@Column(name = "DATE_OF_NOTE")
	private Date dateOfNote;

	@Column(name = "UNPAID_PRIN_BAL")
	private Integer unpaidPrinBal;

	@Column(name = "RATE")
	private Integer rate;

	@Column(name = "PDI_PAY")
	private Integer preDeliveryInspectionPay;

	@Column(name = "TDI_PAY")
	private Integer TDI;

	@Column(name = "ORIGINAL_TERM")
	private Integer originalTerm;

	

	public Integer getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(Integer faceValue) {
		this.faceValue = faceValue;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getNoteType() {
		return noteType;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

	public Integer getNotePosition() {
		return notePosition;
	}

	public void setNotePosition(Integer notePosition) {
		this.notePosition = notePosition;
	}

	public void setBorrowerType(String borrowerType) {
		this.borrowerType = borrowerType;
	}

	public String getBorrowerType() {
		return borrowerType;
	}

	public Integer getUserUserId() {
		return userUserId;
	}

	public void setUserUserId(Integer userUserId) {
		this.userUserId = userUserId;
	}

	public Integer getMonthsToMaturity() {
		return monthsToMaturity;
	}

	public void setMonthsToMaturity(Integer monthsToMaturity) {
		this.monthsToMaturity = monthsToMaturity;
	}

	public Float getIntrestRate() {
		return intrestRate;
	}

	public void setIntrestRate(Float intrestRate) {
		this.intrestRate = intrestRate;
	}

	public Date getNextIntrestAdjustmentDate() {
		return nextIntrestAdjustmentDate;
	}

	public void setNextIntrestAdjustmentDate(Date nextIntrestAdjustmentDate) {
		this.nextIntrestAdjustmentDate = nextIntrestAdjustmentDate;
	}

	public String getIntrestRateAdjustmentRule() {
		return intrestRateAdjustmentRule;
	}

	public void setIntrestRateAdjustmentRule(String intrestRateAdjustmentRule) {
		this.intrestRateAdjustmentRule = intrestRateAdjustmentRule;
	}

	/*
		*//**
			 * @return the searchName
			 */

	/*
	 * public NoteSearchCriteria getSearchName() { return searchName; }
	 * 
	 *//**
		 * @param searchName
		 *            the searchName to set
		 *//*
		 * public void setSearchName(NoteSearchCriteria searchName) {
		 * this.searchName = searchName; }
		 */

	public Integer getUserScore() {
		return userScore;
	}

	public void setUserScore(Integer userScore) {
		this.userScore = userScore;
	}

	public Integer getSystemScore() {
		return systemScore;
	}

	public void setSystemScore(Integer systemScore) {
		this.systemScore = systemScore;
	}

	public String getVendorNoteId() {
		return vendorNoteId;
	}

	public void setVendorNoteId(String vendorNoteId) {
		this.vendorNoteId = vendorNoteId;
	}

	public Date getDateOfNote() {
		return dateOfNote;
	}

	public void setDateOfNote(Date dateOfNote) {
		this.dateOfNote = dateOfNote;
	}

	public Integer getUnpaidPrinBal() {
		return unpaidPrinBal;
	}

	public void setUnpaidPrinBal(Integer unpaidPrinBal) {
		this.unpaidPrinBal = unpaidPrinBal;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getPreDeliveryInspectionPay() {
		return preDeliveryInspectionPay;
	}

	public void setPreDeliveryInspectionPay(Integer preDeliveryInspectionPay) {
		this.preDeliveryInspectionPay = preDeliveryInspectionPay;
	}

	public Integer getTDI() {
		return TDI;
	}

	public void setTDI(Integer tDI) {
		TDI = tDI;
	}

	public Integer getOriginalTerm() {
		return originalTerm;
	}

	public void setOriginalTerm(Integer originalTerm) {
		this.originalTerm = originalTerm;
	}
	
	

	/**
	 * @return the propertyType
	 */
	public String getPropertyType() {
		return propertyType;
	}

	/**
	 * @param propertyType the propertyType to set
	 */
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	/**
	 * @return the property
	 */
	public Property getProperty() {
		return property;
	}

	/**
	 * @param property the property to set
	 */
	public void setProperty(Property property) {
		this.property = property;
	}

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
