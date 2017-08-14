package com.noteanalyzer.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

@Entity
@Table(name = "SCREEN_PRIVILEGES")
public class ScreensPrivileges extends AbstractEntity {

	private static final long serialVersionUID = 2383502272360951114L;

	@Id
	@Column(name = "SCREEN_PRIVILEGES_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String screenPrivilegesId;

	
	@Column(name = "SCREEN_NAME")
	private String screenName;

	@Column(name = "PRIVILEGE")
	private String privilege;
	
	
	
	
	/**
	 * @return the screenPrivilegesId
	 */
	public String getScreenPrivilegesId() {
		return screenPrivilegesId;
	}

	/**
	 * @param screenPrivilegesId the screenPrivilegesId to set
	 */
	public void setScreenPrivilegesId(String screenPrivilegesId) {
		this.screenPrivilegesId = screenPrivilegesId;
	}

	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * @param screenName the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * @return the privilege
	 */
	public String getPrivilege() {
		return privilege;
	}

	/**
	 * @param privilege the privilege to set
	 */
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((screenName == null) ? 0 : screenName.hashCode());
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
		ScreensPrivileges other = (ScreensPrivileges) obj;
		if (screenName == null) {
			if (other.screenName != null)
				return false;
		} else if (!screenName.equals(other.screenName))
			return false;
		return true;
	}
	
	

}
