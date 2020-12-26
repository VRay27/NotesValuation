package com.noteanalyzer.entity.user;

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
import com.noteanalyzer.entity.notes.NoteStore;


@Entity
@Table(name="SUBSCRIPTION")
public class Subscription extends AbstractEntity {

	private static final long serialVersionUID = 2383502272360951114L;
	
	@Id
	@Column(name = "SUBSCRIPTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subscriptionId;
	
	@Column(name = "USER_AGREEMENT")
	private String userAgreement;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subsription_name")
	private SubscriptionPrivileges subscriptionPrivileges;


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
	 * @return the userAgreement
	 */
	public String getUserAgreement() {
		return userAgreement;
	}


	/**
	 * @param userAgreement the userAgreement to set
	 */
	public void setUserAgreement(String userAgreement) {
		this.userAgreement = userAgreement;
	}


	/**
	 * @return the subscriptionPrivileges
	 */
	public SubscriptionPrivileges getSubscriptionPrivileges() {
		return subscriptionPrivileges;
	}


	/**
	 * @param subscriptionPrivileges the subscriptionPrivileges to set
	 */
	public void setSubscriptionPrivileges(SubscriptionPrivileges subscriptionPrivileges) {
		this.subscriptionPrivileges = subscriptionPrivileges;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((subscriptionId == null) ? 0 : subscriptionId.hashCode());
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
		Subscription other = (Subscription) obj;
		if (subscriptionId == null) {
			if (other.subscriptionId != null)
				return false;
		} else if (!subscriptionId.equals(other.subscriptionId))
			return false;
		return true;
	}
	
	
}
