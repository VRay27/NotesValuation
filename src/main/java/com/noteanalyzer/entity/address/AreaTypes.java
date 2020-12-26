package com.noteanalyzer.entity.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

@Entity
@Table(name = "AREA_TYPES")
public class AreaTypes extends AbstractEntity {

	private static final long serialVersionUID = -7773399468661533735L;

	@Id
	@Column(name = "AREA_TYPE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int areaTypeId;

	
	@Column(name = "AREA_TYPE")
	private String areaType;

	@Column(name = "DESCRIPTION")
	private String description;

	/**
	 * @return the areaType
	 */
	public String getAreaType() {
		return areaType;
	}

	/**
	 * @param areaType
	 *            the areaType to set
	 */
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	

	/**
	 * @return the areaTypeId
	 */
	public int getAreaTypeId() {
		return areaTypeId;
	}

	/**
	 * @param areaTypeId the areaTypeId to set
	 */
	public void setAreaTypeId(int areaTypeId) {
		this.areaTypeId = areaTypeId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + areaTypeId;
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
		AreaTypes other = (AreaTypes) obj;
		if (areaTypeId != other.areaTypeId)
			return false;
		return true;
	}

	

}
