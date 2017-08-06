package com.noteanalyzer.entity.notes;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;
 
@Entity
@Table(name="PROPERTY")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = Property.GET_PROPERTY_DETAILS_BY_ID,query="select p from Property p where p.propertyId =:propertyId"),
	 			@NamedQuery(name = Property.GET_PROPERTY_DETAILS_BY_ADDRESS,query="select p from Property p where p.streetAddress =:streetAddress and p.zip =:zipCode and p.state =:state and p.city=:city")})
public class Property extends AbstractEntity {
	private static final long serialVersionUID = 6408731281219126859L;

	public static final String GET_PROPERTY_DETAILS_BY_ID = "GET_PROPERTY_DETAILS_BY_ID";
	public static final String GET_PROPERTY_DETAILS_BY_ADDRESS = "GET_PROPERTY_DETAILS_BY_ADDRESS";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PROPERTY_ID")
    private Integer propertyId;
	
	@Column(name = "PROPERTY_TYPE")
	private String propertyType;
	
	@Column(name="STREET_ADDRESS")
	private String streetAddress;
	
	@Column(name="CITY")
	private String	city;
	
	@Column(name="STATE")
	private String	state;
	
	@Column(name="ZIP")
	private Integer	zip;
	
	
	@Column(name="AGE")
	private Integer	age;
	
	
	@Column(name="SIZE_SF")
	private double	sizeSF;
	
	
	@Column(name="SUBDIVIDABLE")
	private String subdividable;
	
	
	@Column(name="OTHER_HIGHER_PRIORITY_DEBT")
	private Integer	otherHigherPriorityDebt;
	
	@Column(name="OTHER_LOWER_PRIORITY_DEBT")
	private Integer OtherLowerPriorityDebt;
	
	@Column(name="Other_Monthly_expenses")
	private Integer otherMonthlyExpenses;
	
	@Column(name="APPRAISAL_PROPERTY_ID")
	private String appraisalPropertyId;
	
	@Column(name="BUILT_YEAR")
	private String propertyBuiltYear;
	
	@Column(name="PROP_LOT_SIZE")
	private String propertyLotSize;
	
	@Column(name="PROP_LOT_FINISHED_SIZE")
	private String propertyBuiltUpSize;
	
	@Column(name="NO_OF_BATHROOMS")
	private String numberOfBathrooms;
	
	@Column(name="NO_OF_BEDROOMS")
	private String numberOfBedrooms;
	
	@Column(name="LAST_SOLD_DATE")
	private Date lastSoldDate;
	
	@Column(name="LAST_SOLD_PRICE")
	private String lastSoldPrice;
	
	@Column(name="RENT_VALUE")
	private String rentValue;

	@Column(name="TAX_ASSESSMENT_YEAR")
	private String taxAssessmentYear;
	
	@Column(name="TAX_ASSESSMENT_VALUE")
	private String taxAssessmentValue;
	

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((propertyId == null) ? 0 : propertyId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Property)) {
			return false;
		}
		Property other = (Property) obj;
		if (propertyId == null) {
			if (other.propertyId != null) {
				return false;
			}
		} else if (!propertyId.equals(other.propertyId)) {
			return false;
		}
		return true;
	}
	
	
 
   
}
