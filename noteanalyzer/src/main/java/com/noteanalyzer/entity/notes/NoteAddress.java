package com.noteanalyzer.entity.notes;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name = "NOTE_ADDRESS")
@ToString(callSuper = true)

public class NoteAddress extends AbstractEntity {
	
	private static final long serialVersionUID = -4579749131249637368L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTE_ADDRESS_ID")
	private int noteAddressId;
	
	@Column(name = "STREET_ADDRESS")
	private String streetAddress;

	
	@Column(name = "CITY")
	private String city;

	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "ZIPCODE")
	private String zipCode;

	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "AREA_ID")
	private int areaId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PID")
	private Property property;

	/**
	 * @return the noteAddressId
	 */
	public int getNoteAddressId() {
		return noteAddressId;
	}

	/**
	 * @param noteAddressId the noteAddressId to set
	 */
	public void setNoteAddressId(int noteAddressId) {
		this.noteAddressId = noteAddressId;
	}

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
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
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
	 * @return the areaId
	 */
	public int getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	

	/**
	 * @return the property
	 */
	public Property getProperty() {
		return property;
	}

	/**
	 * @param property the property to set
	 */
	public void setProperty(Property property) {
		this.property = property;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + noteAddressId;
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
		NoteAddress other = (NoteAddress) obj;
		if (noteAddressId != other.noteAddressId)
			return false;
		return true;
	}

	

}
