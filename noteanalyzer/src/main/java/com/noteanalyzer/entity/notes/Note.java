package com.noteanalyzer.entity.notes;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;
 
@Entity
@Table(name="NOTE")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = Note.FETCH_NOTE_DETAILS,query="select noteId from Note n where n.noteId =:noteId")})
public class Note extends AbstractEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8179556227491337368L;
	
	public static final String FETCH_NOTE_DETAILS = "FETCH_NOTE_DETAILS";
	
	@Id
    @Column(name="NOTE_ID")
    private Integer noteId;
	
    @Column(name="FACE_VALUE")
    private Integer faceValue;
 
    @Column(name="DISCOUNT")
    private Float discount;
    
    @Column(name="PERFORMANCE")
    private String performance;
    
    @Column(name="NOTE_TYPE")
    private String noteType;
    
    @Column(name="NOTE_POSITION")
    private Integer notePosition;
    
    @Column(name="PROPERTY_ID")
    private Integer propertyId;
    
    @Column(name="PROPERTY_AREA")
    private String propertyArea;
    
    @Column(name="BORROWER_TYPE")
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

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
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

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyArea() {
		return propertyArea;
	}

	public void setPropertyArea(String propertyArea) {
		this.propertyArea = propertyArea;
	}

	public String getBorrowerType() {
		return borrowerType;
	}

	public void setBorrowerType(String borrowerType) {
		this.borrowerType = borrowerType;
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

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((noteId == null) ? 0 : noteId.hashCode());
		result = prime * result
				+ ((notePosition == null) ? 0 : notePosition.hashCode());
		result = prime * result
				+ ((noteType == null) ? 0 : noteType.hashCode());
		result = prime * result
				+ ((propertyId == null) ? 0 : propertyId.hashCode());
		result = prime * result
				+ ((vendorNoteId == null) ? 0 : vendorNoteId.hashCode());
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
		if (noteId == null) {
			if (other.noteId != null)
				return false;
		} else if (!noteId.equals(other.noteId))
			return false;
		if (notePosition == null) {
			if (other.notePosition != null)
				return false;
		} else if (!notePosition.equals(other.notePosition))
			return false;
		if (noteType == null) {
			if (other.noteType != null)
				return false;
		} else if (!noteType.equals(other.noteType))
			return false;
		if (propertyId == null) {
			if (other.propertyId != null)
				return false;
		} else if (!propertyId.equals(other.propertyId))
			return false;
		if (vendorNoteId == null) {
			if (other.vendorNoteId != null)
				return false;
		} else if (!vendorNoteId.equals(other.vendorNoteId))
			return false;
		return true;
	}

	
	
 
   
}
