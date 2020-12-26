package com.noteanalyzer.entity.notes;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NOTE_SERACH_CRITERIA")
public class NoteSearchCriteria {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Search_Id")
	private int searchId ;
    
    @Column(name = "MAX_PRICE")
    private Integer maxPrice;
	
    
    @Column(name = "MIN_PRICE")
    private Integer minPrice;

    @Column(name = "PERFROMING")
    private String performing;
	
    
    @Column(name = "MIN_ROI")
    private Integer minROI;
	
    
    @Column(name = "MAX_RISK_FACTOR")
    private Integer maxRiskFactor;
    
    @Column(name = "DATE_ENTERD")
	private Date dateEntered;
    
    @Column(name = "PROPERTY_LOCATION")
    private String propertyLocations;

    @Column(name = "STORE_NAME")
    private String  storeName;

    @Column(name = "USER_STORE_SUBSCRIPTION")
    private Integer  userStoresSubsriptionId;
	
    @Column(name = "PROPERTY_TYPE")
    private PropertyType  propertyTypes;
    
    @Column(name = "RUNTIME")
    private String  runTime;

	/**
	 * @return the searchId
	 */
	public int getSearchId() {
		return searchId;
	}

	/**
	 * @param searchId the searchId to set
	 */
	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}

	/**
	 * @return the maxPrice
	 */
	public Integer getMaxPrice() {
		return maxPrice;
	}

	/**
	 * @param maxPrice the maxPrice to set
	 */
	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	/**
	 * @return the minPrice
	 */
	public Integer getMinPrice() {
		return minPrice;
	}

	/**
	 * @param minPrice the minPrice to set
	 */
	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
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
	 * @return the minROI
	 */
	public Integer getMinROI() {
		return minROI;
	}

	/**
	 * @param minROI the minROI to set
	 */
	public void setMinROI(Integer minROI) {
		this.minROI = minROI;
	}

	/**
	 * @return the maxRiskFactor
	 */
	public Integer getMaxRiskFactor() {
		return maxRiskFactor;
	}

	/**
	 * @param maxRiskFactor the maxRiskFactor to set
	 */
	public void setMaxRiskFactor(Integer maxRiskFactor) {
		this.maxRiskFactor = maxRiskFactor;
	}

	/**
	 * @return the dateEntered
	 */
	public Date getDateEntered() {
		return dateEntered;
	}

	/**
	 * @param dateEntered the dateEntered to set
	 */
	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	/**
	 * @return the propertyLocations
	 */
	public String getPropertyLocations() {
		return propertyLocations;
	}

	/**
	 * @param propertyLocations the propertyLocations to set
	 */
	public void setPropertyLocations(String propertyLocations) {
		this.propertyLocations = propertyLocations;
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
	 * @return the userStoresSubsriptionId
	 */
	public Integer getUserStoresSubsriptionId() {
		return userStoresSubsriptionId;
	}

	/**
	 * @param userStoresSubsriptionId the userStoresSubsriptionId to set
	 */
	public void setUserStoresSubsriptionId(Integer userStoresSubsriptionId) {
		this.userStoresSubsriptionId = userStoresSubsriptionId;
	}

	/**
	 * @return the propertyTypes
	 */
	public PropertyType getPropertyTypes() {
		return propertyTypes;
	}

	/**
	 * @param propertyTypes the propertyTypes to set
	 */
	public void setPropertyTypes(PropertyType propertyTypes) {
		this.propertyTypes = propertyTypes;
	}

	/**
	 * @return the runTime
	 */
	public String getRunTime() {
		return runTime;
	}

	/**
	 * @param runTime the runTime to set
	 */
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + searchId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoteSearchCriteria other = (NoteSearchCriteria) obj;
		if (searchId != other.searchId)
			return false;
		return true;
	}
    
    
	
    
    
}
