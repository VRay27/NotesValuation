package com.noteanalyzer.entity.notes;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

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
		@NamedQuery(name = Note.GET_NOTE_DETAILS_BY_USER, query = "select n from Note n where n.createdBy =:userName")})
public class Note extends AbstractEntity {
	private static final long serialVersionUID = -8179556227491337368L;

	public static final String GET_NOTE_DETAILS_BY_NOTEID = "GET_NOTE_DETAILS_BY_NOTEID";
	public static final String GET_NOTE_DETAILS_BY_USER = "GET_NOTE_DETAILS_BY_USER";


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTE_ID")
	private int noteId;

	@Column(name = "FACE_VALUE")
	private BigDecimal faceValue;

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


	@Column(name = "BORROWER_TYPE")
	private String borrowerType;

	@Column(name = "USER_USER_ID")
	private Integer userUserId;

	@Column(name = "MONTHS_TO_MATURITY")
	private Integer monthsToMaturity;

	@Column(name = "INTREST_RATE")
	private BigDecimal intrestRate;

	@Column(name = "NEXT_INTREST_ADJUSTMENT_DATE")
	private ZonedDateTime nextIntrestAdjustmentDate;

	@Column(name = "INTREST_RATE_ADJUSTMENT_RULE")
	private BigDecimal intrestRateAdjustmentRule;

	@Column(name = "USER_SCORE")
	private BigDecimal userScore;

	@Column(name = "SYSTEM_SCORE")
	private BigDecimal systemScore;

	@Column(name = "VENDOR_NOTE_ID")
	private String vendorNoteId;

	@Column(name = "DATE_OF_NOTE")
	private ZonedDateTime dateOfNote;

	@Column(name = "UNPAID_PRIN_BAL")
	private BigDecimal unpaidPrincpalBal;

	@Column(name = "RATE")
	private BigDecimal rate;

	@Column(name = "PDI_PAY")
	private BigDecimal preDeliveryInspectionPay;

	@Column(name = "TDI_PAY")
	private BigDecimal TDI;

	@Column(name = "ORIGINAL_TERM")
	private Integer originalTerm;
	
	@Column(name = "ORIGINAL_PRINCIPAL_BAL")
	private BigDecimal originalPrincipleBal;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NOTE_ADDRESS_ID")
	private NoteAddress noteAddress;

	

	
	/**
	 * @return the originalPrincipleBal
	 */
	public BigDecimal getOriginalPrincipleBal() {
		return originalPrincipleBal;
	}

	/**
	 * @param originalPrincipleBal the originalPrincipleBal to set
	 */
	public void setOriginalPrincipleBal(BigDecimal originalPrincipleBal) {
		this.originalPrincipleBal = originalPrincipleBal;
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
	 * @return the intrestRate
	 */
	public BigDecimal getIntrestRate() {
		return intrestRate;
	}

	/**
	 * @param intrestRate the intrestRate to set
	 */
	public void setIntrestRate(BigDecimal intrestRate) {
		this.intrestRate = intrestRate;
	}

	/**
	 * @return the nextIntrestAdjustmentDate
	 */
	public ZonedDateTime getNextIntrestAdjustmentDate() {
		return nextIntrestAdjustmentDate;
	}

	/**
	 * @param nextIntrestAdjustmentDate the nextIntrestAdjustmentDate to set
	 */
	public void setNextIntrestAdjustmentDate(ZonedDateTime nextIntrestAdjustmentDate) {
		this.nextIntrestAdjustmentDate = nextIntrestAdjustmentDate;
	}

	/**
	 * @return the intrestRateAdjustmentRule
	 */
	public BigDecimal getIntrestRateAdjustmentRule() {
		return intrestRateAdjustmentRule;
	}

	/**
	 * @param intrestRateAdjustmentRule the intrestRateAdjustmentRule to set
	 */
	public void setIntrestRateAdjustmentRule(BigDecimal intrestRateAdjustmentRule) {
		this.intrestRateAdjustmentRule = intrestRateAdjustmentRule;
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
	 * @return the unpaidPrincpalBal
	 */
	public BigDecimal getUnpaidPrincpalBal() {
		return unpaidPrincpalBal;
	}

	/**
	 * @param unpaidPrincpalBal the unpaidPrincpalBal to set
	 */
	public void setUnpaidPrincpalBal(BigDecimal unpaidPrincpalBal) {
		this.unpaidPrincpalBal = unpaidPrincpalBal;
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
	 * @return the preDeliveryInspectionPay
	 */
	public BigDecimal getPreDeliveryInspectionPay() {
		return preDeliveryInspectionPay;
	}

	/**
	 * @param preDeliveryInspectionPay the preDeliveryInspectionPay to set
	 */
	public void setPreDeliveryInspectionPay(BigDecimal preDeliveryInspectionPay) {
		this.preDeliveryInspectionPay = preDeliveryInspectionPay;
	}

	/**
	 * @return the tDI
	 */
	public BigDecimal getTDI() {
		return TDI;
	}

	/**
	 * @param tDI the tDI to set
	 */
	public void setTDI(BigDecimal tDI) {
		TDI = tDI;
	}

	public String getVendorNoteId() {
		return vendorNoteId;
	}

	public void setVendorNoteId(String vendorNoteId) {
		this.vendorNoteId = vendorNoteId;
	}

	public ZonedDateTime getDateOfNote() {
		return dateOfNote;
	}

	public void setDateOfNote(ZonedDateTime dateOfNote) {
		this.dateOfNote = dateOfNote;
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
	 * @return the noteAddress
	 */
	public NoteAddress getNoteAddress() {
		return noteAddress;
	}

	/**
	 * @param noteAddress the noteAddress to set
	 */
	public void setNoteAddress(NoteAddress noteAddress) {
		this.noteAddress = noteAddress;
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
