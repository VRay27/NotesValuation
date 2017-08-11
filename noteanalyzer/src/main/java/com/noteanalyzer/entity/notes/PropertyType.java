package com.noteanalyzer.entity.notes;

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
@Table(name="PropertyTypes")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = "GET_PROPERTY_TYPE_BY_TYPE",query="select p from PropertyType p  where p.propertyType =:propertyTypeCode")})
public class PropertyType extends AbstractEntity { 
	
	private static final long serialVersionUID = 3343766906297480204L;
	
	public static final String GET_PROPERTY_TYPE_BY_TYPE = "GET_PROPERTY_TYPE_BY_TYPE";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Property_Type_Id")
    private Integer propertyTypeId;
	
    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((propertyTypeId == null) ? 0 : propertyTypeId.hashCode());
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
		PropertyType other = (PropertyType) obj;
		if (propertyTypeId == null) {
			if (other.propertyTypeId != null)
				return false;
		} else if (!propertyTypeId.equals(other.propertyTypeId))
			return false;
		return true;
	}

	@Column(name="PROPERTY_TYPE")
	private String propertyType;
	
    @Column(name="DESCRIPTION")
	private String description;

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
    
    
}
