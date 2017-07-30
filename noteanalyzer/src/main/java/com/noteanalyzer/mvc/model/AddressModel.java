package com.noteanalyzer.mvc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class AddressModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3290853140632668820L;
	
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private Set<String> cityList = new HashSet<>();
	private Set<String> stateList = new HashSet<>();
	
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
	public Set<String> getCityList() {
		return cityList;
	}
	/**
	 * @param cityList the cityList to set
	 */
	public void setCityList(Set<String> cityList) {
		this.cityList = cityList;
	}
	/**
	 * @return the stateList
	 */
	public Set<String> getStateList() {
		return stateList;
	}
	/**
	 * @param stateList the stateList to set
	 */
	public void setStateList(Set<String> stateList) {
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
