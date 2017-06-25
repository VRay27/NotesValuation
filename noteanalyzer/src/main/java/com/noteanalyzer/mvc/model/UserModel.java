package com.noteanalyzer.mvc.model;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserModel {

	private long userId;
	
	private String displayName;
	
	private String password;
	
	private AddressModel addressModel;
	
	private String email;
	
	private String phoneNumber;
	
	@JsonIgnore
	private String resetToken;
	
	@JsonIgnore
	private String verificationToken;
	
	@JsonIgnore
	private ZonedDateTime resetCreationTime;
	
	
	private List<UserRoleModel> roles;
	
	public UserModel() {
		super();
	}

	public UserModel(long userId, String displayName, String email){
		this.userId = userId;
		this.displayName = displayName;
		this.email = email;
	}

	
	
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	
	

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the addressModel
	 */
	public AddressModel getAddressModel() {
		return addressModel;
	}

	/**
	 * @param addressModel the addressModel to set
	 */
	public void setAddressModel(AddressModel addressModel) {
		this.addressModel = addressModel;
	}

	/**
	 * @return the resetCreationTime
	 */
	public ZonedDateTime getResetCreationTime() {
		return resetCreationTime;
	}

	/**
	 * @param resetCreationTime the resetCreationTime to set
	 */
	public void setResetCreationTime(ZonedDateTime resetCreationTime) {
		this.resetCreationTime = resetCreationTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the resetToken
	 */
	public String getResetToken() {
		return resetToken;
	}

	/**
	 * @param resetToken the resetToken to set
	 */
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		UserModel other = (UserModel) obj;
		if (userId != other.userId)
			return false;
		return true;
	}

	/**
	 * @return the address
	 */
	public AddressModel getAddress() {
		return addressModel;
	}

	/**
	 * @param addressModel the address to set
	 */
	public void setAddress(AddressModel addressModel) {
		this.addressModel = addressModel;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the roles
	 */
	public List<UserRoleModel> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<UserRoleModel> roles) {
		this.roles = roles;
	}
	
	

	/**
	 * @return the verificationToken
	 */
	public String getVerificationToken() {
		return verificationToken;
	}

	/**
	 * @param verificationToken the verificationToken to set
	 */
	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", displayName=" + displayName 
				+ ", addressModel=" + addressModel + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", resetToken=" + resetToken + ", resetCreationTime=" + resetCreationTime + ", roles=" + roles + "]";
	}

	
	

	
	
	
}
