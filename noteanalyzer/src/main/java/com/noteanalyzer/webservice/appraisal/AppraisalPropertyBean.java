/**
 * 
 */
package com.noteanalyzer.webservice.appraisal;

import java.io.Serializable;

/**
 * This class will hold the property details fetch from Apprisal Source.
 * @author Arvind Ray
 *
 */
public class AppraisalPropertyBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5492784215037887434L;
	
	private String apprisalPropertyId;
	private String marketValue;
	private String currency;
	private String propertyDetailUrl;
	private String propertyCompareUrl;
	private String propertyBuiltYear;
	private String propertyLotSize;
	private String propertyBuiltUpSize;
	private String numberOfBathrooms;
	private String numberOfBedrooms;
	private String lastSoldDate;
	private String lastSoldPrice;
	private String rentValue;
	private String propertyGraphAndDataUrl;
	private String propertyMapUrl;
	private String propertyType;
	private String taxAssessmentYear;
	private String taxAssessmentValue;
	
	
	/**
	 * @return the apprisalPropertyId
	 */
	public String getApprisalPropertyId() {
		return apprisalPropertyId;
	}
	/**
	 * @param apprisalPropertyId the apprisalPropertyId to set
	 */
	public void setApprisalPropertyId(String apprisalPropertyId) {
		this.apprisalPropertyId = apprisalPropertyId;
	}
	/**
	 * @return the marketValue
	 */
	public String getMarketValue() {
		return marketValue;
	}
	/**
	 * @param marketValue the marketValue to set
	 */
	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}
	/**
	 * @return the propertyDetailUrl
	 */
	public String getPropertyDetailUrl() {
		return propertyDetailUrl;
	}
	/**
	 * @param propertyDetailUrl the propertyDetailUrl to set
	 */
	public void setPropertyDetailUrl(String propertyDetailUrl) {
		this.propertyDetailUrl = propertyDetailUrl;
	}
	/**
	 * @return the propertyCompareUrl
	 */
	public String getPropertyCompareUrl() {
		return propertyCompareUrl;
	}
	/**
	 * @param propertyCompareUrl the propertyCompareUrl to set
	 */
	public void setPropertyCompareUrl(String propertyCompareUrl) {
		this.propertyCompareUrl = propertyCompareUrl;
	}
	/**
	 * @return the propertyBuiltYear
	 */
	public String getPropertyBuiltYear() {
		return propertyBuiltYear;
	}
	/**
	 * @param propertyBuiltYear the propertyBuiltYear to set
	 */
	public void setPropertyBuiltYear(String propertyBuiltYear) {
		this.propertyBuiltYear = propertyBuiltYear;
	}
	/**
	 * @return the propertyLotSize
	 */
	public String getPropertyLotSize() {
		return propertyLotSize;
	}
	/**
	 * @param propertyLotSize the propertyLotSize to set
	 */
	public void setPropertyLotSize(String propertyLotSize) {
		this.propertyLotSize = propertyLotSize;
	}
	/**
	 * @return the propertyBuiltUpSize
	 */
	public String getPropertyBuiltUpSize() {
		return propertyBuiltUpSize;
	}
	/**
	 * @param propertyBuiltUpSize the propertyBuiltUpSize to set
	 */
	public void setPropertyBuiltUpSize(String propertyBuiltUpSize) {
		this.propertyBuiltUpSize = propertyBuiltUpSize;
	}
	/**
	 * @return the numberOfBathrooms
	 */
	public String getNumberOfBathrooms() {
		return numberOfBathrooms;
	}
	/**
	 * @param numberOfBathrooms the numberOfBathrooms to set
	 */
	public void setNumberOfBathrooms(String numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}
	/**
	 * @return the numberOfBedrooms
	 */
	public String getNumberOfBedrooms() {
		return numberOfBedrooms;
	}
	/**
	 * @param numberOfBedrooms the numberOfBedrooms to set
	 */
	public void setNumberOfBedrooms(String numberOfBedrooms) {
		this.numberOfBedrooms = numberOfBedrooms;
	}
	/**
	 * @return the lastSoldDate
	 */
	public String getLastSoldDate() {
		return lastSoldDate;
	}
	/**
	 * @param lastSoldDate the lastSoldDate to set
	 */
	public void setLastSoldDate(String lastSoldDate) {
		this.lastSoldDate = lastSoldDate;
	}
	/**
	 * @return the lastSoldPrice
	 */
	public String getLastSoldPrice() {
		return lastSoldPrice;
	}
	/**
	 * @param lastSoldPrice the lastSoldPrice to set
	 */
	public void setLastSoldPrice(String lastSoldPrice) {
		this.lastSoldPrice = lastSoldPrice;
	}
	/**
	 * @return the rentValue
	 */
	public String getRentValue() {
		return rentValue;
	}
	/**
	 * @param rentValue the rentValue to set
	 */
	public void setRentValue(String rentValue) {
		this.rentValue = rentValue;
	}
	
	
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the propertyGraphAndDataUrl
	 */
	public String getPropertyGraphAndDataUrl() {
		return propertyGraphAndDataUrl;
	}
	/**
	 * @param propertyGraphAndDataUrl the propertyGraphAndDataUrl to set
	 */
	public void setPropertyGraphAndDataUrl(String propertyGraphAndDataUrl) {
		this.propertyGraphAndDataUrl = propertyGraphAndDataUrl;
	}
	
	
	/**
	 * @return the taxAssessmentYear
	 */
	public String getTaxAssessmentYear() {
		return taxAssessmentYear;
	}
	/**
	 * @param taxAssessmentYear the taxAssessmentYear to set
	 */
	public void setTaxAssessmentYear(String taxAssessmentYear) {
		this.taxAssessmentYear = taxAssessmentYear;
	}
	/**
	 * @return the taxAssessmentValue
	 */
	public String getTaxAssessmentValue() {
		return taxAssessmentValue;
	}
	/**
	 * @param taxAssessmentValue the taxAssessmentValue to set
	 */
	public void setTaxAssessmentValue(String taxAssessmentValue) {
		this.taxAssessmentValue = taxAssessmentValue;
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
	 * @return the propertyMapUrl
	 */
	public String getPropertyMapUrl() {
		return propertyMapUrl;
	}
	/**
	 * @param propertyMapUrl the propertyMapUrl to set
	 */
	public void setPropertyMapUrl(String propertyMapUrl) {
		this.propertyMapUrl = propertyMapUrl;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AppraisalPropertyBean [apprisalPropertyId=" + apprisalPropertyId + ", marketValue=" + marketValue
				+ ", propertyDetailUrl=" + propertyDetailUrl + ", propertyCompareUrl=" + propertyCompareUrl
				+ ", propertyBuiltYear=" + propertyBuiltYear + ", propertyLotSize=" + propertyLotSize
				+ ", propertyBuiltUpSize=" + propertyBuiltUpSize + ", numberOfBathrooms=" + numberOfBathrooms
				+ ", numberOfBedrooms=" + numberOfBedrooms + ", lastSoldDate=" + lastSoldDate + ", lastSoldPrice="
				+ lastSoldPrice + ", rentValue=" + rentValue + "]";
	}
	
	

}
