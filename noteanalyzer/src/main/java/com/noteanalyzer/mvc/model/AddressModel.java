package com.noteanalyzer.mvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddressModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3290853140632668820L;
	
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private List<String> cityList = new ArrayList<>();
	private List<String> stateList = new ArrayList<>();
	
	/**
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}
	/**
	 * @param streetAddress the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the cityList
	 */
	public List<String> getCityList() {
		return cityList;
	}
	/**
	 * @param cityList the cityList to set
	 */
	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
	}
	/**
	 * @return the stateList
	 */
	public List<String> getStateList() {
		return stateList;
	}
	/**
	 * @param stateList the stateList to set
	 */
	public void setStateList(List<String> stateList) {
		this.stateList = stateList;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AddressModel [streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", zipCode="
				+ zipCode + ", cityList=" + cityList + ", stateList=" + stateList + "]";
	}
	
	
	
	
}
