package com.noteanalyzer.mvc.model;

import java.io.Serializable;

public class PropertyType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3800824063535708236L;
	private String propertyTypeCode;
	private String propertyTypeValue;
	/**
	 * @return the propertyTypeCode
	 */
	public String getPropertyTypeCode() {
		return propertyTypeCode;
	}
	/**
	 * @param propertyTypeCode the propertyTypeCode to set
	 */
	public void setPropertyTypeCode(String propertyTypeCode) {
		this.propertyTypeCode = propertyTypeCode;
	}
	/**
	 * @return the propertyTypeValue
	 */
	public String getPropertyTypeValue() {
		return propertyTypeValue;
	}
	/**
	 * @param propertyTypeValue the propertyTypeValue to set
	 */
	public void setPropertyTypeValue(String propertyTypeValue) {
		this.propertyTypeValue = propertyTypeValue;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PropertyType [propertyTypeCode=" + propertyTypeCode + ", propertyTypeValue=" + propertyTypeValue + "]";
	}
	
	

}
