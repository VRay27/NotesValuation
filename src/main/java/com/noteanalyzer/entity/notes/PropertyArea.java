package com.noteanalyzer.entity.notes;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name="PROPERTY_AREAS")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = "GET_PROPERTY_AREA",query="select p from PropertyArea p  where p.propertyId =:propertyId"),
				@NamedQuery(name = "GET_PROP_BY_AREA_ID",query="select p from PropertyArea p  where p.areaId =:areaId and p.areaType =:areaType")})
public class PropertyArea extends AbstractEntity { 
	
	private static final long serialVersionUID = 3337645236789480204L;
	
	public static final String GET_AREA_BY_PROP_ID = "GET_AREA_BY_PROP_ID";
	public static final String GET_PROP_BY_AREA_ID = "GET_PROP_BY_AREA_ID";
	
	
	@Id
	@Column(name="Property_Area_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String propertyAreaId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "PROPERTY_ID", nullable = false)
	private Property propertyId;
	
	
    @Column(name="AREA_ID")
	private String areaId;
    
    @Column(name="AREA_TYPE")
   	private String areaType;

	/**
	 * @return the propertyAreaId
	 */
	public String getPropertyAreaId() {
		return propertyAreaId;
	}

	/**
	 * @param propertyAreaId the propertyAreaId to set
	 */
	public void setPropertyAreaId(String propertyAreaId) {
		this.propertyAreaId = propertyAreaId;
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
	 * @return the areaType
	 */
	public String getAreaType() {
		return areaType;
	}

	/**
	 * @param areaType the areaType to set
	 */
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((propertyAreaId == null) ? 0 : propertyAreaId.hashCode());
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
		PropertyArea other = (PropertyArea) obj;
		if (propertyAreaId == null) {
			if (other.propertyAreaId != null)
				return false;
		} else if (!propertyAreaId.equals(other.propertyAreaId))
			return false;
		return true;
	}

    
    
}
