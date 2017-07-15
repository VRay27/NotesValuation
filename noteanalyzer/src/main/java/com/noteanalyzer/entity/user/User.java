package com.noteanalyzer.entity.user;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name = "USER")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = User.GET_IN_ACTIVE_USER_DETAILS, query="select u from User u where lower(u.userName) =:userName and  u.isActive !='Y'"),
				@NamedQuery(name = User.GET_ACTIVE_USER_DETAILS, query="select u from User u where lower(u.userName) =:userName and u.isActive='Y'"),
				@NamedQuery(name = User.GET_USER_DETAILS, query="select u from User u where lower(u.userName) =:userName")})
public class User extends AbstractEntity{

	public static final String GET_USER_DETAILS = "GET_USER_DETAILS";
	public static final String GET_IN_ACTIVE_USER_DETAILS = "GET_IN_ACTIVE_USER_DETAILS";
	public static final String GET_ACTIVE_USER_DETAILS = "GET_ACTIVE_USER_DETAILS";
	
	private static final long serialVersionUID = -2173424644486392900L;
	
	@Id 
	@Column(name="USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
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
	
	@Column(name="DISPLAY_NAME")
	private String displayName;
	
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
	
	@Column(name="STREET")
	private String street;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="ZIP_CODE")
	private String zipcode;

	@Column(name="STATE")
	private String state;
	
	@Column(name="COUNTRY")
	private String country;

	@Column(name="RESET_TOKEN")
	private String resetToken;

	@Column(name="RESET_TOKEN_CREATION_TIME")
	private ZonedDateTime resetTokenCreationTime;
	
	@Column(name="VERIFICATION_TOKEN")
	private String verificationToken;

	@Column(name="VERIFICATION_TOKEN_CREATION_TIME")
	private ZonedDateTime verificationTokenCreationTime;
	
	@Column(name="IS_ACTIVE")
	private String isActive;

	
	public User() {
		super();
	}

	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailID
	 */
	public String getEmailID() {
		return emailID;
	}

	/**
	 * @param emailID the emailID to set
	 */
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	
	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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


	/**
	 * @return the resetTokenCreationTime
	 */
	public ZonedDateTime getResetTokenCreationTime() {
		return resetTokenCreationTime;
	}

	/**
	 * @param resetTokenCreationTime the resetTokenCreationTime to set
	 */
	public void setResetTokenCreationTime(ZonedDateTime resetTokenCreationTime) {
		this.resetTokenCreationTime = resetTokenCreationTime;
	}
	
	

	/**
	 * @return the verificationTokenCreationTime
	 */
	public ZonedDateTime getVerificationTokenCreationTime() {
		return verificationTokenCreationTime;
	}

	/**
	 * @param verificationTokenCreationTime the verificationTokenCreationTime to set
	 */
	public void setVerificationTokenCreationTime(ZonedDateTime verificationTokenCreationTime) {
		this.verificationTokenCreationTime = verificationTokenCreationTime;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	
	
}
