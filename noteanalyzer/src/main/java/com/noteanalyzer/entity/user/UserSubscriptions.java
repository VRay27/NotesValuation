package com.noteanalyzer.entity.user;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;
import com.noteanalyzer.entity.user.UserRole.Id;

import lombok.ToString;

@Entity
@Table(name="USER_SUBSCRIPTION")
@ToString(callSuper = true)
public class UserSubscriptions extends AbstractEntity  {
	
	private static final long serialVersionUID = -3262112354371474829L;

	@Embeddable
	public static class UserSubscriptionId implements Serializable {
		private static final long serialVersionUID = 1322120000551624359L;

		@Column(name = "SUBSCRIPTION_ID")
		private Integer subscriptionId;

		@Column(name = "USER_ID")
		private Integer userId;

		public UserSubscriptionId() {
			// TODO Auto-generated constructor stub
		}

		public UserSubscriptionId(Integer subscriptionId, Integer userId) {
			this.subscriptionId = subscriptionId;
			this.userId = userId;
		}
	}
	 
	@EmbeddedId
	UserSubscriptionId userSubscriptionId = new UserSubscriptionId();
	    
	 
	@Column(name="EXPIRATION_DATE")
	private Date expirationDate;

	
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
	
	
	
}
