package com.noteanalyzer.mvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class NoteTypeModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1924873727695219842L;
	private String noteTypeCode;
	private String noteTypeValue;
	private String interestAdjustmentRules;
	private String termMonths;
	private long interestOnlyMonths;
	private long baloonAfterMonths;
	private String armIndexName;
	private BigDecimal margin;
	private BigDecimal interestCap;
	
	
	public NoteTypeModel() {
		super();
	}
	
	public NoteTypeModel(String noteTypeCode, String noteTypeValue) {
		super();
		this.noteTypeCode = noteTypeCode;
		this.noteTypeValue = noteTypeValue;
	}
	/**
	 * @return the noteTypeCode
	 */
	public String getNoteTypeCode() {
		return noteTypeCode;
	}
	/**
	 * @param noteTypeCode the noteTypeCode to set
	 */
	public void setNoteTypeCode(String noteTypeCode) {
		this.noteTypeCode = noteTypeCode;
	}
	/**
	 * @return the noteTypeValue
	 */
	public String getNoteTypeValue() {
		return noteTypeValue;
	}
	/**
	 * @param noteTypeValue the noteTypeValue to set
	 */
	public void setNoteTypeValue(String noteTypeValue) {
		this.noteTypeValue = noteTypeValue;
	}

	/**
	 * @return the interestAdjustmentRules
	 */
	public String getInterestAdjustmentRules() {
		return interestAdjustmentRules;
	}

	/**
	 * @param interestAdjustmentRules the interestAdjustmentRules to set
	 */
	public void setInterestAdjustmentRules(String interestAdjustmentRules) {
		this.interestAdjustmentRules = interestAdjustmentRules;
	}

	/**
	 * @return the termMonths
	 */
	public String getTermMonths() {
		return termMonths;
	}

	/**
	 * @param termMonths the termMonths to set
	 */
	public void setTermMonths(String termMonths) {
		this.termMonths = termMonths;
	}

	
	
	/**
	 * @return the interestOnlyMonths
	 */
	public long getInterestOnlyMonths() {
		return interestOnlyMonths;
	}

	/**
	 * @param interestOnlyMonths the interestOnlyMonths to set
	 */
	public void setInterestOnlyMonths(long interestOnlyMonths) {
		this.interestOnlyMonths = interestOnlyMonths;
	}

	/**
	 * @return the baloonAfterMonths
	 */
	public long getBaloonAfterMonths() {
		return baloonAfterMonths;
	}

	/**
	 * @param baloonAfterMonths the baloonAfterMonths to set
	 */
	public void setBaloonAfterMonths(long baloonAfterMonths) {
		this.baloonAfterMonths = baloonAfterMonths;
	}

	/**
	 * @return the armIndexName
	 */
	public String getArmIndexName() {
		return armIndexName;
	}

	/**
	 * @param armIndexName the armIndexName to set
	 */
	public void setArmIndexName(String armIndexName) {
		this.armIndexName = armIndexName;
	}

	/**
	 * @return the margin
	 */
	public BigDecimal getMargin() {
		return margin;
	}

	/**
	 * @param margin the margin to set
	 */
	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}

	/**
	 * @return the interestCap
	 */
	public BigDecimal getInterestCap() {
		return interestCap;
	}

	/**
	 * @param interestCap the interestCap to set
	 */
	public void setInterestCap(BigDecimal interestCap) {
		this.interestCap = interestCap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NoteTypeModel [noteTypeCode=" + noteTypeCode + ", noteTypeValue=" + noteTypeValue
				+ ", interestAdjustmentRules=" + interestAdjustmentRules + ", termMonths=" + termMonths
				+ ", interestOnlyMonths=" + interestOnlyMonths + ", baloonAfterMonths=" + baloonAfterMonths
				+ ", armIndexName=" + armIndexName + ", margin=" + margin + ", interestCap=" + interestCap + "]";
	}
	
	
	

}
