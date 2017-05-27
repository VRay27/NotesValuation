package com.noteanalyzer.entity.notes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name="PROPERTY_TYPE")
@ToString(callSuper = true)
public class PropertyType extends AbstractEntity { 
	
	private static final long serialVersionUID = 3343766906297480204L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="P_TYPE_ID")
    private Integer P_TYPE_ID;
	
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
