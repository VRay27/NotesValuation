package com.noteanalyzer.entity.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name="USER_STORES")
@ToString(callSuper = true)
public class UserStores extends AbstractEntity  {
	
	private static final long serialVersionUID = -32621371474829L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_STORES_ID")
	private int userStoresId;

	@Column(name = "USER_USER_ID")
	private Integer userUserId;

	@Column(name = "STORE_NAME")
	private Integer storeName;
	
	@Column(name="CREDENTIALS")
	private Date credentials;

	/**
	 * @return the userStoresId
	 */
	public int getUserStoresId() {
		return userStoresId;
	}

	/**
	 * @param userStoresId the userStoresId to set
	 */
	public void setUserStoresId(int userStoresId) {
		this.userStoresId = userStoresId;
	}

	/**
	 * @return the userUserId
	 */
	public Integer getUserUserId() {
		return userUserId;
	}

	/**
	 * @param userUserId the userUserId to set
	 */
	public void setUserUserId(Integer userUserId) {
		this.userUserId = userUserId;
	}

	/**
	 * @return the storeName
	 */
	public Integer getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(Integer storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return the credentials
	 */
	public Date getCredentials() {
		return credentials;
	}

	/**
	 * @param credentials the credentials to set
	 */
	public void setCredentials(Date credentials) {
		this.credentials = credentials;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + userStoresId;
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
		UserStores other = (UserStores) obj;
		if (userStoresId != other.userStoresId)
			return false;
		return true;
	}

	

	
}
