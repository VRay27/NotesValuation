package com.noteanalyzer.entity.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

@Entity
@Table(name = "ZIPCODES")
public class Zipcodes extends AbstractEntity {

	private static final long serialVersionUID = -7773399468661533735L;

	@Column(name = "ZIP_ID")
	private String zipId;

	@Column(name = "ZIP")
	private String zip;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@OneToMany
	@JoinColumn(name = "AREA_ID")
	private Area area;

	@Column(name = "AREA_TYPE")
	private String areaType;

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
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
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * @return the areaType
	 */
	public String getAreaType() {
		return areaType;
	}

	/**
	 * @param areaType
	 *            the areaType to set
	 */
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	/**
	 * @return the zipId
	 */
	public String getZipId() {
		return zipId;
	}

	/**
	 * @param zipId
	 *            the zipId to set
	 */
	public void setZipId(String zipId) {
		this.zipId = zipId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zipId == null) ? 0 : zipId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Zipcodes)) {
			return false;
		}
		Zipcodes other = (Zipcodes) obj;
		if (zipId == null) {
			if (other.zipId != null) {
				return false;
			}
		} else if (!zipId.equals(other.zipId)) {
			return false;
		}
		return true;
	}

}
