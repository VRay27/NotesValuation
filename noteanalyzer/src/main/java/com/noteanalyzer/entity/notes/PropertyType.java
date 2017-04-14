package com.noteanalyzer.entity.notes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name="PROPERTY_TYPE")
@ToString(callSuper = true)
public class PropertyType extends AbstractEntity { 
	
	private static final long serialVersionUID = -6809609970307104305L;

	@Id
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
