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
@Table(name="USER_SUBSCRIPTION")
@ToString(callSuper = true)
public class UserSubscriptions extends AbstractEntity  {
	
	private static final long serialVersionUID = -3262112354371474829L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_SUBSCRIPTION_ID")
	private int userSubscriptionId;

	@Column(name = "SUBSCRIPTION_ID")
	private Integer subscriptionId;

	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name="EXPIRATION_DATE")
	private Date expirationDate;

	@Column(name="UA_SIGNED_ON")
	private Date uaSignedOn;

	/**
	 * @return the userSubscriptionId
	 */
	public int getUserSubscriptionId() {
		return userSubscriptionId;
	}

	/**
	 * @param userSubscriptionId the userSubscriptionId to set
	 */
	public void setUserSubscriptionId(int userSubscriptionId) {
		this.userSubscriptionId = userSubscriptionId;
	}

	/**
	 * @return the subscriptionId
	 */
	public Integer getSubscriptionId() {
		return subscriptionId;
	}

	/**
	 * @param subscriptionId the subscriptionId to set
	 */
	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the uaSignedOn
	 */
	public Date getUaSignedOn() {
		return uaSignedOn;
	}

	/**
	 * @param uaSignedOn the uaSignedOn to set
	 */
	public void setUaSignedOn(Date uaSignedOn) {
		this.uaSignedOn = uaSignedOn;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + userSubscriptionId;
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
		UserSubscriptions other = (UserSubscriptions) obj;
		if (userSubscriptionId != other.userSubscriptionId)
			return false;
		return true;
	}

	


	
}
