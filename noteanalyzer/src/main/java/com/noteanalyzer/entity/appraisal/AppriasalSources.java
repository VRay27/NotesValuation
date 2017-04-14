package com.noteanalyzer.entity.appraisal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;
@Entity
@Table(name="APPRIASAL_SOURCE")
public class AppriasalSources extends AbstractEntity {
	
	@Id
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
	
	
}
