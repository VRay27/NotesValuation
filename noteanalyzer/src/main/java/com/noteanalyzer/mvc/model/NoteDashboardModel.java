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
	private String estimatedITV;
	private String currentITV;
	private String currentLTV;
	private String estimatedLTV;
	private Date marketUpdateDate;
	private String marketValueAvailable;
	private String schoolScore;
	private String crimeScore;
	private String roi;
	private String city;
	private String state;
	private String zipCode;
	private String streetAddress;
	private String propertyType;

	public NoteDashboardModel() {
	}

	
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
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
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}


	/**
	 * @param streetAddress the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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
	public String isMarketValueAvailable() {
		return marketValueAvailable;
	}

	/**
	 * @param marketValueAvailable
	 *            the marketValueAvailable to set
	 */
	public void setMarketValueAvailable(String marketValueAvailable) {
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
	 * @return the estimatedITV
	 */
	public String getEstimatedITV() {
		return estimatedITV;
	}


	/**
	 * @param estimatedITV the estimatedITV to set
	 */
	public void setEstimatedITV(String estimatedITV) {
		this.estimatedITV = estimatedITV;
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
