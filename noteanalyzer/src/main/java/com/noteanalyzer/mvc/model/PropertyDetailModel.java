/**
 * 
 */
package com.noteanalyzer.mvc.model;

import java.io.Serializable;

/**
 * This class will hold the details of Property/Asset.
 * @author Arvind Ray
 *
 */
public class PropertyDetailModel implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6261889303717984545L;
	
	private int numOfBedRoom;
	private double propertySize;
	private int numOfBathRoom;
	
	private String propertyOwner;
	private String avgEducation;
	/**
	 * @return the numOfBedRoom
	 */
	public int getNumOfBedRoom() {
		return numOfBedRoom;
	}
	/**
	 * @param numOfBedRoom the numOfBedRoom to set
	 */
	public void setNumOfBedRoom(int numOfBedRoom) {
		this.numOfBedRoom = numOfBedRoom;
	}
	/**
	 * @return the propertySize
	 */
	public double getPropertySize() {
		return propertySize;
	}
	/**
	 * @param propertySize the propertySize to set
	 */
	public void setPropertySize(double propertySize) {
		this.propertySize = propertySize;
	}
	/**
	 * @return the numOfBathRoom
	 */
	public int getNumOfBathRoom() {
		return numOfBathRoom;
	}
	/**
	 * @param numOfBathRoom the numOfBathRoom to set
	 */
	public void setNumOfBathRoom(int numOfBathRoom) {
		this.numOfBathRoom = numOfBathRoom;
	}
	/**
	 * @return the propertyOwner
	 */
	public String getPropertyOwner() {
		return propertyOwner;
	}
	/**
	 * @param propertyOwner the propertyOwner to set
	 */
	public void setPropertyOwner(String propertyOwner) {
		this.propertyOwner = propertyOwner;
	}
	/**
	 * @return the avgEducation
	 */
	public String getAvgEducation() {
		return avgEducation;
	}
	/**
	 * @param avgEducation the avgEducation to set
	 */
	public void setAvgEducation(String avgEducation) {
		this.avgEducation = avgEducation;
	}

	@Override
	public String toString() {
		return "PropertyDetailModel [numOfBedRoom=" + numOfBedRoom + ", propertySize=" + propertySize
				+ ", numOfBathRoom=" + numOfBathRoom + ", propertyOwner=" + propertyOwner + ", avgEducation="
				+ avgEducation + "]";
	}

	
	
}
