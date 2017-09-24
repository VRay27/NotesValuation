package com.noteanalyzer.entity.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name="user_subscriptions")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = UserSubscriptions.GET_USER_SUBS_DETAILS, query="select u from UserSubscriptions u where u.userId =:userId")})

public class UserSubscriptions extends AbstractEntity  {
	
	private static final long serialVersionUID = -3222112354371474829L;
	public static final  String GET_USER_SUBS_DETAILS = "GET_USER_SUBS_DETAILS";
	
	@Id
	@Column(name = "USER_SUBSCRIPTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userSubscriptionId;

	@Column(name = "Subsription_Name")
	private String subscriptionName;

	@Column(name="USER_ID")
	private Long userId ;
	
	@Column(name="EXPIRATION_DATE")
	private Date expirationDate;

	@Column(name="UA_SIGNED_ON")
	private Date uaSignedOn;
	
	

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
