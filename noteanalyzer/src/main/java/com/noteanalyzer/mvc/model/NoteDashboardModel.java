package com.noteanalyzer.mvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class NoteDashboardModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1746624356754962655L;

	private long noteId;
	private String noteAddress;
	private BigDecimal yield;
	private String marketValue;
	private String crime;
	private BigDecimal effectiveLTV;
	private BigDecimal currentEffectiveLTV;
	private BigDecimal originalLTV;
	private Date marketUpdateDate;
	private boolean marketValueAvailable;

	public NoteDashboardModel() {
	}
	
	


	/**
	 * @return the marketValueAvailable
	 */
	public boolean isMarketValueAvailable() {
		return marketValueAvailable;
	}



	/**
	 * @param marketValueAvailable the marketValueAvailable to set
	 */
	public void setMarketValueAvailable(boolean marketValueAvailable) {
		this.marketValueAvailable = marketValueAvailable;
	}





	/**
	 * @return the originalLTV
	 */
	public BigDecimal getOriginalLTV() {
		return originalLTV;
	}

	/**
	 * @param originalLTV
	 *            the originalLTV to set
	 */
	public void setOriginalLTV(BigDecimal originalLTV) {
		this.originalLTV = originalLTV;
	}

	/**
	 * @return the marketUpdateDate
	 */
	public Date getMarketUpdateDate() {
		return marketUpdateDate;
	}

	/**
	 * @param marketUpdateDate
	 *            the marketUpdateDate to set
	 */
	public void setMarketUpdateDate(Date marketUpdateDate) {
		this.marketUpdateDate = marketUpdateDate;
	}

	/**
	 * @return the effectiveLTV
	 */
	public BigDecimal getEffectiveLTV() {
		return effectiveLTV;
	}

	/**
	 * @param effectiveLTV
	 *            the effectiveLTV to set
	 */
	public void setEffectiveLTV(BigDecimal effectiveLTV) {
		this.effectiveLTV = effectiveLTV;
	}

	/**
	 * @return the currentEffectiveLTV
	 */
	public BigDecimal getCurrentEffectiveLTV() {
		return currentEffectiveLTV;
	}

	/**
	 * @param currentEffectiveLTV
	 *            the currentEffectiveLTV to set
	 */
	public void setCurrentEffectiveLTV(BigDecimal currentEffectiveLTV) {
		this.currentEffectiveLTV = currentEffectiveLTV;
	}

	/**
	 * @return the noteId
	 */
	public long getNoteId() {
		return noteId;
	}

	/**
	 * @param noteId
	 *            the noteId to set
	 */
	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}

	/**
	 * @return the noteAddress
	 */
	public String getNoteAddress() {
		return noteAddress;
	}

	/**
	 * @param noteAddress
	 *            the noteAddress to set
	 */
	public void setNoteAddress(String noteAddress) {
		this.noteAddress = noteAddress;
	}

	/**
	 * @return the yield
	 */
	public BigDecimal getYield() {
		return yield;
	}

	/**
	 * @param yield
	 *            the yield to set
	 */
	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	public String getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}

	public String getCrime() {
		return crime;
	}

	public void setCrime(String crime) {
		this.crime = crime;
	}

}
