package com.noteanalyzer.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name = "USER")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = "FETCH_USER_DETAILS", query="select userId from User u where u.userId =:userId")})
public class User extends AbstractEntity{

	/**
	 * Generated Serial ID.
	 */
	private static final long serialVersionUID = -2173424644486392900L;
	
	/**
	 * This will store the unique identifier for each user
	 */
	@Id
	@Column(name="USER_ID")
	private Integer userId;

	/**
	 * This will store the userName given to this user.
	 */
	@Column(name="USER_NAME")
	private String userName;
	
	/**
	 * This will store the userName given to this user.
	 */
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="EMAIL_ID")
	private String emailID;

	@Column(name="CONTACT_NUMBER")
	private String contactNumber;

	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="ZIP_CODE")
	private String zipCODE;

	@Column(name="STATE")
	private String state;
	
	@Column(name="COUNTRY")
	private String country;

	public Integer getUserID() {
		return userId;
	}

	public void setUserID(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCODE() {
		return zipCODE;
	}

	public void setZipCODE(String zipCODE) {
		this.zipCODE = zipCODE;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	
	
}
