package com.noteanalyzer.entity.appraisal;

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
@Table(name="appraisal_sources")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = "GET_APPRAISAL_SOURCE",query="select p from AppriasalSources p  where p.source =:appraisalSource")})

public class AppriasalSources extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4680579370837974453L;
	public static final String GET_APPRAISAL_SOURCE_DETAIL = "GET_APPRAISAL_SOURCE";
	
	@Id
	@Column(name="APPRAISAL_SOURCE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String sourceAppraisalID;
	
    @Column(name="SOURCE")
	private String source;
	
    @Column(name="APPRAISAL_API")
	private String appraisal_api;
	/**
	 * @return the appraisal_api
	 */
	public String getAppraisal_api() {
		return appraisal_api;
	}
	/**
	 * @param appraisal_api the appraisal_api to set
	 */
	public void setAppraisal_api(String appraisal_api) {
		this.appraisal_api = appraisal_api;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the sourceAppraisalID
	 */
	public String getSourceAppraisalID() {
		return sourceAppraisalID;
	}
	/**
	 * @param sourceAppraisalID the sourceAppraisalID to set
	 */
	public void setSourceAppraisalID(String sourceAppraisalID) {
		this.sourceAppraisalID = sourceAppraisalID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((sourceAppraisalID == null) ? 0 : sourceAppraisalID.hashCode());
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
		AppriasalSources other = (AppriasalSources) obj;
		if (sourceAppraisalID == null) {
			if (other.sourceAppraisalID != null)
				return false;
		} else if (!sourceAppraisalID.equals(other.sourceAppraisalID))
			return false;
		return true;
	}
	
	
	
}
