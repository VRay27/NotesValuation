package com.noteanalyzer.mvc.model;

import java.io.Serializable;

public class PropertyTypeModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3800824063535708236L;
	private String propertyTypeCode;
	private String propertyTypeValue;
	
	public PropertyTypeModel() {
		super();
	}
	
	public PropertyTypeModel(String propertyTypeCode, String propertyTypeValue) {
		super();
		this.propertyTypeCode = propertyTypeCode;
		this.propertyTypeValue = propertyTypeValue;
	}
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((propertyTypeCode == null) ? 0 : propertyTypeCode.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyTypeModel other = (PropertyTypeModel) obj;
		if (propertyTypeCode == null) {
			if (other.propertyTypeCode != null)
				return false;
		} else if (!propertyTypeCode.equals(other.propertyTypeCode))
			return false;
		return true;
	}
	
	

}
