package com.noteanalyzer.mvc.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserModel extends RequestStatusModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1229343341896851765L;

	private long userId;
	
	private String displayName;
	
	private String password;
	
	private AddressModel addressModel;
	
	private String zipCode;
	
	private String selCity;
	
	private String selState;
	
	private String email;
	
	private String streetAddress;
	
	private String phoneNumber;
	
	@JsonIgnore
	private String resetToken;
	
	@JsonIgnore
	private String verificationToken;
	
	@JsonIgnore
	private ZonedDateTime resetCreationTime;
	
	@JsonIgnore
	private String isActive;
	
	private String subscriptionName;
		
	
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
	 * @return the subscriptionName
	 */
	public String getSubscriptionName() {
		return subscriptionName;
	}

	/**
	 * @param subscriptionName the subscriptionName to set
	 */
	public void setSubscriptionName(String subscriptionName) {
		this.subscriptionName = subscriptionName;
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
	
	

	/**
	 * @return the isActive
	 */
	public String getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
	 * @return the selCity
	 */
	public String getSelCity() {
		return selCity;
	}

	/**
	 * @param selCity the selCity to set
	 */
	public void setSelCity(String selCity) {
		this.selCity = selCity;
	}

	/**
	 * @return the selState
	 */
	public String getSelState() {
		return selState;
	}

	/**
	 * @param selState the selState to set
	 */
	public void setSelState(String selState) {
		this.selState = selState;
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
	
	


}
