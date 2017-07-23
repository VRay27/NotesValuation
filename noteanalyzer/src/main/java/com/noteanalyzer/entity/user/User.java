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
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="USER_FIRST_LAST_NAME")
	private String displayName;
	
	@Column(name="EMAIL_ADDRESS")
	private String emailID;

	@Column(name="RECOVERY_PHONE_NUMBER")
	private String contactNumber;
		
	@Column(name="STREET_ADDRESS")
	private String street;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="ZIPCODE")
	private String zipcode;

	@Column(name="STATE")
	private String state;
	
	@Column(name="RESET_TOKEN")
	private String resetToken;

	@Column(name="RESET_TOKEN_CREATION_TIME")
	private ZonedDateTime resetTokenCreationTime;
	
	@Column(name="VERIFICATION_TOKEN")
	private String verificationToken;

	@Column(name="VERIFICATION_TOKEN_CREATION_TIME")
	private ZonedDateTime verificationTokenCreationTime;
	
	@Column(name="CREATE_DATE")
	private ZonedDateTime createDate;
	
	@Column(name="UPDATE_DATE")
	private ZonedDateTime updateDate;

	@Column(name="UNSUCCESSFUL_LOGIN_ATTEMPTS")
	private Long unsuccessfulLoginAttempts;
	
	@Column(name="STATUS")
	private String isActive;

	
	public User() {
		super();
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



	/**
	 * @return the createDate
	 */
	public ZonedDateTime getCreateDate() {
		return createDate;
	}



	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(ZonedDateTime createDate) {
		this.createDate = createDate;
	}



	/**
	 * @return the updateDate
	 */
	public ZonedDateTime getUpdateDate() {
		return updateDate;
	}



	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(ZonedDateTime updateDate) {
		this.updateDate = updateDate;
	}



	/**
	 * @return the unsuccessfulLoginAttempts
	 */
	public Long getUnsuccessfulLoginAttempts() {
		return unsuccessfulLoginAttempts;
	}



	/**
	 * @param unsuccessfulLoginAttempts the unsuccessfulLoginAttempts to set
	 */
	public void setUnsuccessfulLoginAttempts(Long unsuccessfulLoginAttempts) {
		this.unsuccessfulLoginAttempts = unsuccessfulLoginAttempts;
	}



	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailID == null) ? 0 : emailID.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}



	/* (non-Javadoc)
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
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (emailID == null) {
			if (other.emailID != null) {
				return false;
			}
		} else if (!emailID.equals(other.emailID)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
	}




}
