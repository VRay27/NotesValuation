package com.noteanalyzer.mvc.model;

import java.io.Serializable;
import java.util.List;

public class StateModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3290853523462668820L;
	
	private String id;
	private String stateCode;
	private String stateDisplayName;
	private List<CityModel> cityList;
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
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}
	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	/**
	 * @return the stateDisplayName
	 */
	public String getStateDisplayName() {
		return stateDisplayName;
	}
	/**
	 * @param stateDisplayName the stateDisplayName to set
	 */
	public void setStateDisplayName(String stateDisplayName) {
		this.stateDisplayName = stateDisplayName;
	}
	/**
	 * @return the cityList
	 */
	public List<CityModel> getCityList() {
		return cityList;
	}
	/**
	 * @param cityList the cityList to set
	 */
	public void setCityList(List<CityModel> cityList) {
		this.cityList = cityList;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StateModel [id=" + id + ", stateCode=" + stateCode + ", stateDisplayName=" + stateDisplayName
				+ ", cityList=" + cityList + "]";
	}
	
	
}
