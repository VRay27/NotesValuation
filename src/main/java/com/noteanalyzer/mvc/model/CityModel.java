package com.noteanalyzer.mvc.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;

public class CityModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 332347140632668820L;
	
	private String id;
	private String cityCode;
	private String cityDisplayName;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * @return the cityDisplayName
	 */
	public String getCityDisplayName() {
		return cityDisplayName;
	}
	/**
	 * @param cityDisplayName the cityDisplayName to set
	 */
	public void setCityDisplayName(String cityDisplayName) {
		this.cityDisplayName = cityDisplayName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CityModel [id=" + id + ", cityCode=" + cityCode + ", cityDisplayName=" + cityDisplayName + "]";
	}

	
	
	
}
