/**
 * 
 */
package com.noteanalyzer.mvc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * This class will hold the details of Property/Asset.
 * @author Arvind Ray
 *
 */
public class PropertyDetailModel implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6261889303717984545L;
	
	private Integer propertyId;
	
	private String propertyType;
	
	private String streetAddress;
	
	private String	city;
	
	private String	state;
	
	private Integer	zip;
	
	
	private Integer	age;
	
	
	private double	sizeSF;
	
	
	private String subdividable;
	
	
	private Integer	otherHigherPriorityDebt;
	
	private Integer OtherLowerPriorityDebt;
	
	private Integer otherMonthlyExpenses;
	
	private String appraisalPropertyId;
	
	private String propertyBuiltYear;
	
	private String propertyLotSize;
	
	private String propertyBuiltUpSize;
	
	private String numberOfBathrooms;
	
	private String numberOfBedrooms;
	
	private Date lastSoldDate;
	
	private String lastSoldPrice;
	
	private String rentValue;

	private String taxAssessmentYear;
	
	private String taxAssessmentValue;
	
	private String marketValue;
	
	private String HOAFee;
	
	private String propertyDetailUrl;
	
	private String propertyGraphAndDataUrl;
	
	private String propertyMapUrl;
	
	

	/**
	 * @return the propertyId
	 */
	public Integer getPropertyId() {
		return propertyId;
	}

	/**
	 * @param propertyId the propertyId to set
	 */
	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
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
	 * @return the zip
	 */
	public Integer getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(Integer zip) {
		this.zip = zip;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the sizeSF
	 */
	public double getSizeSF() {
		return sizeSF;
	}

	/**
	 * @param sizeSF the sizeSF to set
	 */
	public void setSizeSF(double sizeSF) {
		this.sizeSF = sizeSF;
	}

	/**
	 * @return the subdividable
	 */
	public String getSubdividable() {
		return subdividable;
	}

	/**
	 * @param subdividable the subdividable to set
	 */
	public void setSubdividable(String subdividable) {
		this.subdividable = subdividable;
	}

	/**
	 * @return the otherHigherPriorityDebt
	 */
	public Integer getOtherHigherPriorityDebt() {
		return otherHigherPriorityDebt;
	}

	/**
	 * @param otherHigherPriorityDebt the otherHigherPriorityDebt to set
	 */
	public void setOtherHigherPriorityDebt(Integer otherHigherPriorityDebt) {
		this.otherHigherPriorityDebt = otherHigherPriorityDebt;
	}

	/**
	 * @return the otherLowerPriorityDebt
	 */
	public Integer getOtherLowerPriorityDebt() {
		return OtherLowerPriorityDebt;
	}

	/**
	 * @param otherLowerPriorityDebt the otherLowerPriorityDebt to set
	 */
	public void setOtherLowerPriorityDebt(Integer otherLowerPriorityDebt) {
		OtherLowerPriorityDebt = otherLowerPriorityDebt;
	}

	/**
	 * @return the otherMonthlyExpenses
	 */
	public Integer getOtherMonthlyExpenses() {
		return otherMonthlyExpenses;
	}

	/**
	 * @param otherMonthlyExpenses the otherMonthlyExpenses to set
	 */
	public void setOtherMonthlyExpenses(Integer otherMonthlyExpenses) {
		this.otherMonthlyExpenses = otherMonthlyExpenses;
	}

	/**
	 * @return the appraisalPropertyId
	 */
	public String getAppraisalPropertyId() {
		return appraisalPropertyId;
	}

	/**
	 * @param appraisalPropertyId the appraisalPropertyId to set
	 */
	public void setAppraisalPropertyId(String appraisalPropertyId) {
		this.appraisalPropertyId = appraisalPropertyId;
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
	public Date getLastSoldDate() {
		return lastSoldDate;
	}

	/**
	 * @param lastSoldDate the lastSoldDate to set
	 */
	public void setLastSoldDate(Date lastSoldDate) {
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
	 * @return the hOAFee
	 */
	public String getHOAFee() {
		return HOAFee;
	}

	/**
	 * @param hOAFee the hOAFee to set
	 */
	public void setHOAFee(String hOAFee) {
		HOAFee = hOAFee;
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

	
	
}
