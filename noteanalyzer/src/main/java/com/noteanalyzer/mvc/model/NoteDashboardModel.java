package com.noteanalyzer.mvc.model;

import java.io.Serializable;
import java.util.Date;

public class NoteDashboardModel extends RequestStatusModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1746624356754962655L;

	private long noteId;
	private String noteAddress;
	private String yield;
	private String marketValue;
	private String crime;
	private String originalEstimatedITV;
	private String currentITV;
	private String currentLTV;
	private String estimatedLTV;
	private Date marketUpdateDate;
	private boolean marketValueAvailable;
	private String schoolScore;
	private String crimeScore;
	private String roi;

	public NoteDashboardModel() {
	}

	
	/**
	 * @return the roi
	 */
	public String getRoi() {
		return roi;
	}


	/**
	 * @param roi the roi to set
	 */
	public void setRoi(String roi) {
		this.roi = roi;
	}


	/**
	 * @return the schoolScore
	 */
	public String getSchoolScore() {
		return schoolScore;
	}

	/**
	 * @param schoolScore
	 *            the schoolScore to set
	 */
	public void setSchoolScore(String schoolScore) {
		this.schoolScore = schoolScore;
	}

	/**
	 * @return the crimeScore
	 */
	public String getCrimeScore() {
		return crimeScore;
	}

	/**
	 * @param crimeScore
	 *            the crimeScore to set
	 */
	public void setCrimeScore(String crimeScore) {
		this.crimeScore = crimeScore;
	}

	/**
	 * @return the marketValueAvailable
	 */
	public boolean isMarketValueAvailable() {
		return marketValueAvailable;
	}

	/**
	 * @param marketValueAvailable
	 *            the marketValueAvailable to set
	 */
	public void setMarketValueAvailable(boolean marketValueAvailable) {
		this.marketValueAvailable = marketValueAvailable;
	}

	

	/**
	 * @return the estimatedLTV
	 */
	public String getEstimatedLTV() {
		return estimatedLTV;
	}


	/**
	 * @param estimatedLTV the estimatedLTV to set
	 */
	public void setEstimatedLTV(String estimatedLTV) {
		this.estimatedLTV = estimatedLTV;
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
	 * @return the originalEstimatedITV
	 */
	public String getOriginalEstimatedITV() {
		return originalEstimatedITV;
	}

	/**
	 * @param originalEstimatedITV the originalEstimatedITV to set
	 */
	public void setOriginalEstimatedITV(String originalEstimatedITV) {
		this.originalEstimatedITV = originalEstimatedITV;
	}

	/**
	 * @return the currentITV
	 */
	public String getCurrentITV() {
		return currentITV;
	}

	/**
	 * @param currentITV the currentITV to set
	 */
	public void setCurrentITV(String currentITV) {
		this.currentITV = currentITV;
	}

	/**
	 * @return the currentLTV
	 */
	public String getCurrentLTV() {
		return currentLTV;
	}

	/**
	 * @param currentLTV the currentLTV to set
	 */
	public void setCurrentLTV(String currentLTV) {
		this.currentLTV = currentLTV;
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
	public String getYield() {
		return yield;
	}

	/**
	 * @param yield
	 *            the yield to set
	 */
	public void setYield(String yield) {
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
