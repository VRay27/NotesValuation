package com.noteanalyzer.entity.user;

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
@Table(name = "subscription_privileges")
@ToString(callSuper = true)
@NamedQueries({
		@NamedQuery(name = SubscriptionPrivileges.GET_SUBS_PRIVILEGE_DETAILS, query = "select u from SubscriptionPrivileges u where u.subscriptionName in (:subscriptionName)") })

public class SubscriptionPrivileges extends AbstractEntity {

	private static final long serialVersionUID = 2383502272360951114L;
	public static final String GET_SUBS_PRIVILEGE_DETAILS = "GET_SUBS_PRIVILEGE_DETAILS";

	@Id
	@Column(name = "subscription_privileges_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subscriptionprivilegesID;

	@Column(name = "privilege_id")
	private String privilegesId;

	@Column(name = "subsription_name")
	private String subscriptionName;

	@Column(name = "privilege_code")
	private String privilegesCode;

	/**
	 * @return the privilegesCode
	 */
	public String getPrivilegesCode() {
		return privilegesCode;
	}

	/**
	 * @param privilegesCode
	 *            the privilegesCode to set
	 */
	public void setPrivilegesCode(String privilegesCode) {
		this.privilegesCode = privilegesCode;
	}

	/**
	 * @return the subscriptionprivilegesID
	 */
	public Integer getSubscriptionprivilegesID() {
		return subscriptionprivilegesID;
	}

	/**
	 * @param subscriptionprivilegesID
	 *            the subscriptionprivilegesID to set
	 */
	public void setSubscriptionprivilegesID(Integer subscriptionprivilegesID) {
		this.subscriptionprivilegesID = subscriptionprivilegesID;
	}

	/**
	 * @return the privilegesId
	 */
	public String getPrivilegesId() {
		return privilegesId;
	}

	/**
	 * @param privilegesId
	 *            the privilegesId to set
	 */
	public void setPrivilegesId(String privilegesId) {
		this.privilegesId = privilegesId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((subscriptionprivilegesID == null) ? 0 : subscriptionprivilegesID.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		SubscriptionPrivileges other = (SubscriptionPrivileges) obj;
		if (subscriptionprivilegesID == null) {
			if (other.subscriptionprivilegesID != null)
				return false;
		} else if (!subscriptionprivilegesID.equals(other.subscriptionprivilegesID))
			return false;
		return true;
	}

}
