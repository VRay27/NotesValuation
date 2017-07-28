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
@Table(name="PROPERTY_APPRAISALS")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = "GET_PROPERTY_APPRAISALS_AREA",query="select p from PropertyAppraisals p  where p.propertyId =:propertyId"),
				@NamedQuery(name = "GET_PROP_APPRAISALS_BY_AREA_ID",query="select p from PropertyAppraisals p  where p.areaId =:areaId")})
public class PropertyAppraisals extends AbstractEntity { 
	
	private static final long serialVersionUID = 3337645236789480204L;
	
	public static final String GET_PROPERTY_APPRAISALS_AREA = "GET_PROPERTY_APPRAISALS_AREA";
	public static final String GET_PROP_APPRAISALS_BY_AREA_ID = "GET_PROP_APPRAISALS_BY_AREA_ID";
	
	
	@Id
	@Column(name="Property_Appraisals_Id")
	private String propertyAppraisalsId;
	
    @Column(name="PROPERTY_ID")
	private String propertyId;
	
    @Column(name="AREA_ID")
	private String areaId;
    
    @Column(name="APPRAISAL_SOURCE")
   	private String appraisalsSource;
    
    @Column(name="APPRAISAL_VALUE")
   	private String appraisalsValue;
    
    @Column(name="APPRAISAL_DATE")
   	private Date appraisalsDate;

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
	public String getPropertyId() {
		return propertyId;
	}

	/**
	 * @param propertyId the propertyId to set
	 */
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	/**
	 * @return the areaId
	 */
	public String getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	/**
	 * @return the appraisalsSource
	 */
	public String getAppraisalsSource() {
		return appraisalsSource;
	}

	/**
	 * @param appraisalsSource the appraisalsSource to set
	 */
	public void setAppraisalsSource(String appraisalsSource) {
		this.appraisalsSource = appraisalsSource;
	}

	/**
	 * @return the appraisalsValue
	 */
	public String getAppraisalsValue() {
		return appraisalsValue;
	}

	/**
	 * @param appraisalsValue the appraisalsValue to set
	 */
	public void setAppraisalsValue(String appraisalsValue) {
		this.appraisalsValue = appraisalsValue;
	}

	/**
	 * @return the appraisalsDate
	 */
	public Date getAppraisalsDate() {
		return appraisalsDate;
	}

	/**
	 * @param appraisalsDate the appraisalsDate to set
	 */
	public void setAppraisalsDate(Date appraisalsDate) {
		this.appraisalsDate = appraisalsDate;
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
