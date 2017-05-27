package com.noteanalyzer.entity.notes;

import java.sql.Date;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;
import com.noteanalyzer.entity.valuation.BorrowerType;

import lombok.ToString;
 
@Entity
@Table(name="NOTE")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = Note.FETCH_NOTE_DETAILS,query="select noteId from Note n where n.noteId =:noteId")})
public class Note extends AbstractEntity {
	private static final long serialVersionUID = -8179556227491337368L;
	
	public static final String FETCH_NOTE_DETAILS = "FETCH_NOTE_DETAILS";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="NID")
    private Integer nId;
	
	
    @Column(name="NOTE_ID")
    private String noteId;
	
    @Column(name="FACE_VALUE")
    private Integer faceValue;
 
    @Column(name="DISCOUNT")
    private Float discount;
    
    @Column(name="PERFORMANCE")
    private String performance;
    
	@Column(name = "NOTE_TYPE")
    private String noteType;
    
    @Column(name="NOTE_POSITION")
    private Integer notePosition;
    
    @Column(name = "PROP_ID")
    private String propertyID;
    
	@Column(name = "BORROWER_TYPE")
    private String borrowerType;
    
    @Column(name="USER_USER_ID")
    private Integer userUserId;
    
    @Column(name="MONTHS_TO_MATURITY")
    private Integer monthsToMaturity;
    
    @Column(name="INTREST_RATE")
    private Float intrestRate;
    
    @Column(name="NEXT_INTREST_ADJUSTMENT_DATE")
    private Date nextIntrestAdjustmentDate;
    
    @Column(name="INTREST_RATE_ADJUSTMENT_RULE")
    private String intrestRateAdjustmentRule;
    
    @Column(name="SERACH_NAME")
    private String searchName;
    
    @Column(name="USER_SCORE")
    private Integer userScore;
    
    @Column(name="SYSTEM_SCORE")
    private Integer systemScore;
    
    @Column(name="VENDOR_NOTE_ID")
    private String vendorNoteId;
    
    @Column(name="DATE_OF_NOTE")
    private Date dateOfNote;
    
    @Column(name="UNPAID_PRIN_BAL")
    private Integer unpaidPrinBal;
    
    @Column(name="RATE")
    private Integer rate;
    
    @Column(name="PDI_PAY")
    private Integer preDeliveryInspectionPay;
    
    @Column(name="TDI_PAY")
    private Integer TDI;
    
    @Column(name="ORIGINAL_TERM")
    private Integer originalTerm;
    

	public Integer getnId() {
		return nId;
	}

	public void setnId(Integer nId) {
		this.nId = nId;
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

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

	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}
	public String getPropertyID() {
		return propertyID;
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
	 *//*
	public NoteSearchCriteria getSearchName() {
		return searchName;
	}

	*//**
	 * @param searchName the searchName to set
	 *//*
	public void setSearchName(NoteSearchCriteria searchName) {
		this.searchName = searchName;
	}*/

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((borrowerType == null) ? 0 : borrowerType.hashCode());
		result = prime * result + ((noteId == null) ? 0 : noteId.hashCode());
		result = prime * result + ((noteType == null) ? 0 : noteType.hashCode());
		result = prime * result + ((propertyID == null) ? 0 : propertyID.hashCode());
	
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (borrowerType == null) {
			if (other.borrowerType != null)
				return false;
		} else if (!borrowerType.equals(other.borrowerType))
			return false;
		if (noteId == null) {
			if (other.noteId != null)
				return false;
		} else if (!noteId.equals(other.noteId))
			return false;
		if (noteType == null) {
			if (other.noteType != null)
				return false;
		} else if (!noteType.equals(other.noteType))
			return false;
		if (propertyID == null) {
			if (other.propertyID != null)
				return false;
		} else if (!propertyID.equals(other.propertyID))
			return false;
		return true;
	}

 
   
}
