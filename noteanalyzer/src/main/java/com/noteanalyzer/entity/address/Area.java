package com.noteanalyzer.entity.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AREA")
public class Area  {

	
	@Id
	@Column(name="AREA_ID")
	private Integer areaId;
	
	@Column(name="AREA_NAME")
	private String	areaName;
	
	@Column(name="DESCRIPTION")
	private String description;
	/**
	 * @return the areaId
	 */
	public Integer getAreaId() {
		return areaId;
	}
	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
