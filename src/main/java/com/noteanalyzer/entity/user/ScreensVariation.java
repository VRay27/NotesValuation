package com.noteanalyzer.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

@Entity
@Table(name = "SCREEN_VARIATIONS")
public class ScreensVariation extends AbstractEntity {

	private static final long serialVersionUID = 2383502272360951114L;

	@Id
	@Column(name = "SCREEN_VARIATIONS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String screenVariationId;
	
	
	@Column(name = "SCREEN_NAME")
	private String screenName;

	@Column(name = "VARIATION")
	private String variation;
	
	@Column(name = "SCREEN_FILE_NAME")
	private String screenFileName;

	
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
	 * @return the variation
	 */
	public String getVariation() {
		return variation;
	}

	/**
	 * @param variation the variation to set
	 */
	public void setVariation(String variation) {
		this.variation = variation;
	}

	/**
	 * @return the screenFileName
	 */
	public String getScreenFileName() {
		return screenFileName;
	}

	/**
	 * @param screenFileName the screenFileName to set
	 */
	public void setScreenFileName(String screenFileName) {
		this.screenFileName = screenFileName;
	}
	
	

	/**
	 * @return the screenVariationId
	 */
	public String getScreenVariationId() {
		return screenVariationId;
	}

	/**
	 * @param screenVariationId the screenVariationId to set
	 */
	public void setScreenVariationId(String screenVariationId) {
		this.screenVariationId = screenVariationId;
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
		ScreensVariation other = (ScreensVariation) obj;
		if (screenName == null) {
			if (other.screenName != null)
				return false;
		} else if (!screenName.equals(other.screenName))
			return false;
		return true;
	}
	
	

}
