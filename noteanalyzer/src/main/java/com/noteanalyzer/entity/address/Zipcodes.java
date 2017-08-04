package com.noteanalyzer.entity.address;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;
import com.noteanalyzer.entity.notes.Note;

import lombok.ToString;

@Entity
@Table(name = "ZIPCODES")
@ToString(callSuper = true)
@NamedQueries({
		@NamedQuery(name = Zipcodes.GET_ZIPCODE_DETAILS_BY_ZIPCODE, query = "select z from Zipcodes z where z.zip =:zipCode"),
		@NamedQuery(name = Zipcodes.GET_ALL_LOCATION, query = "select z from Zipcodes z")})

public class Zipcodes extends AbstractEntity {

	private static final long serialVersionUID = -7382699468661533735L;
	
	public final static String  GET_ZIPCODE_DETAILS_BY_ZIPCODE = "GET_ZIPCODE_DETAILS_BY_ZIPCODE";
	public final static String  GET_ALL_LOCATION= "GET_ALL_LOCATION";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ZIP_ID")
	private int zipId;

	@Column(name = "ZIP")
	private String zip;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "AREA_TYPE")
	private String areaType;
	
	@Column(name = "AREA_ID")
	private String areaId;
	

	/*@OneToMany
	@JoinColumn(name = "A_ID")
	private Set<Area> area;*/

	
	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
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
	 * @return the areaType
	 */
	public String getAreaType() {
		return areaType;
	}

	/**
	 * @param areaType the areaType to set
	 */
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	/**
	 * @return the zipId
	 */
	public int getZipId() {
		return zipId;
	}
	
	

	/**
	 * @return the areaId
	 */
	public String getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	
	/**
	 * @return the area
	 *//*
	public Set<Area> getArea() {
		return area;
	}

	*//**
	 * @param area the area to set
	 *//*
	public void setArea(Set<Area> area) {
		this.area = area;
	}*/

	/**
	 * @param zipId the zipId to set
	 */
	public void setZipId(int zipId) {
		this.zipId = zipId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + zipId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zipcodes other = (Zipcodes) obj;
		if (zipId != other.zipId)
			return false;
		return true;
	}

	
}
