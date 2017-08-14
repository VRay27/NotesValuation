package com.noteanalyzer.entity.notes;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;
import com.noteanalyzer.entity.appraisal.AppriasalSources;

import lombok.ToString;

@Entity
@Table(name="PROPERTY_APPRAISALS")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = "GET_PROPERTY_APPRAISALS_AREA",query="select p from PropertyAppraisals p  where p.propertyId =:propertyId")})
public class PropertyAppraisals extends AbstractEntity { 
	
	private static final long serialVersionUID = 3337645236789480204L;
	
	public static final String GET_PROPERTY_APPRAISALS_AREA = "GET_PROPERTY_APPRAISALS_AREA";
	
	
	@Id
	@Column(name="Property_Appraisals_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String propertyAppraisalsId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "PROPERTY_ID", nullable = false)
	private Property propertyId;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch= FetchType.LAZY)
	@JoinColumn(name = "APPRAISAL_SOURCE", nullable = false)
   	private AppriasalSources appraisalsSource;
    
    @Column(name="APPRAISAL_VALUE")
   	private String marketValue;
    
    @Column(name="APPRAISAL_DATE")
   	private Date marketValueUpdatedDate;
    

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
	
	@Column(name="PROP_GRAPH_DATA_URL")
	private String propertyGraphAndDataUrl;
	
	@Column(name="PROP_MAP_URL")
	private String propertyMapUrl;
	
	@Column(name="PROP_DETAIL_URL")
	private String propertyDetailUrl;

	/**
	 * @return the propertyAppraisalsId
	 */
	public String getPropertyAppraisalsId() {
		return propertyAppraisalsId;
	}

	/**
	 * @param propertyAppraisalsId the propertyAppraisalsId to set
	 */
	public void setPropertyAppraisalsId(String propertyAppraisalsId) {
		this.propertyAppraisalsId = propertyAppraisalsId;
	}

	

	/**
	 * @return the propertyId
	 */
	public Property getPropertyId() {
		return propertyId;
	}

	/**
	 * @param propertyId the propertyId to set
	 */
	public void setPropertyId(Property propertyId) {
		this.propertyId = propertyId;
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
	 * @return the marketValueUpdatedDate
	 */
	public Date getMarketValueUpdatedDate() {
		return marketValueUpdatedDate;
	}

	/**
	 * @param marketValueUpdatedDate the marketValueUpdatedDate to set
	 */
	public void setMarketValueUpdatedDate(Date marketValueUpdatedDate) {
		this.marketValueUpdatedDate = marketValueUpdatedDate;
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
	 * @return the appraisalsSource
	 */
	public AppriasalSources getAppraisalsSource() {
		return appraisalsSource;
	}

	/**
	 * @param appraisalsSource the appraisalsSource to set
	 */
	public void setAppraisalsSource(AppriasalSources appraisalsSource) {
		this.appraisalsSource = appraisalsSource;
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
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyAppraisals other = (PropertyAppraisals) obj;
		if (propertyId == null) {
			if (other.propertyId != null)
				return false;
		} else if (!propertyId.equals(other.propertyId))
			return false;
		return true;
	}
    
    
    
    
}
