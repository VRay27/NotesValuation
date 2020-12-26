package com.noteanalyzer.entity.notes;

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
@Table(name="NOTE_CONFIGURATION")

@ToString(callSuper = true)
@NamedQueries({
		@NamedQuery(name = NoteConfiguration.GET_CONFIG_VALUE, query = "select n from NoteConfiguration n where n.configCode in (:configCode)")})

public class NoteConfiguration extends AbstractEntity {
	
	private static final long serialVersionUID = 393756414246689997L;


	public static final String GET_CONFIG_VALUE = "GET_CONFIG_VALUE";

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONFIG_ID")
	private String configId;
	
	@Column(name = "CONFIG_CODE")
	private String configCode;

	@Column(name = "CONFIG_VALUE")
	private String configValue;

	/**
	 * @return the configId
	 */
	public String getConfigId() {
		return configId;
	}

	/**
	 * @param configId the configId to set
	 */
	public void setConfigId(String configId) {
		this.configId = configId;
	}

	/**
	 * @return the configCode
	 */
	public String getConfigCode() {
		return configCode;
	}

	/**
	 * @param configCode the configCode to set
	 */
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	/**
	 * @return the configValue
	 */
	public String getConfigValue() {
		return configValue;
	}

	/**
	 * @param configValue the configValue to set
	 */
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((configCode == null) ? 0 : configCode.hashCode());
		result = prime * result + ((configId == null) ? 0 : configId.hashCode());
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
		NoteConfiguration other = (NoteConfiguration) obj;
		if (configCode == null) {
			if (other.configCode != null)
				return false;
		} else if (!configCode.equals(other.configCode))
			return false;
		if (configId == null) {
			if (other.configId != null)
				return false;
		} else if (!configId.equals(other.configId))
			return false;
		return true;
	}

		

}
