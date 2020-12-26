package com.noteanalyzer.entity.appraisal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

@Entity
@Table(name = "APPRAISAL")
public class Appraisal extends AbstractEntity {

	private static final long serialVersionUID = 3305276391140825995L;

	@Id
	@Column(name = "appraisal_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appraisalId;
	
	@Column(name = "APPRAISAL_SOURCE")
	private String appraisalSource;

	@Column(name = "AREA_AREA_ID")
	private Integer area_AreaId;

	@Column(name = "AREA_TYPE")
	private String area_type;
	
	@Column(name = "APPRAISAL_RELIABILITY_INDEX")
	private Float appraisalReliabilityIndex;

	/**
	 * @return the appraisalId
	 */
	public int getAppraisalId() {
		return appraisalId;
	}

	/**
	 * @param appraisalId the appraisalId to set
	 */
	public void setAppraisalId(int appraisalId) {
		this.appraisalId = appraisalId;
	}

	/**
	 * @return the appraisalSource
	 */
	public String getAppraisalSource() {
		return appraisalSource;
	}

	/**
	 * @param appraisalSource the appraisalSource to set
	 */
	public void setAppraisalSource(String appraisalSource) {
		this.appraisalSource = appraisalSource;
	}

	/**
	 * @return the area_AreaId
	 */
	public Integer getArea_AreaId() {
		return area_AreaId;
	}

	/**
	 * @param area_AreaId the area_AreaId to set
	 */
	public void setArea_AreaId(Integer area_AreaId) {
		this.area_AreaId = area_AreaId;
	}

	/**
	 * @return the area_type
	 */
	public String getArea_type() {
		return area_type;
	}

	/**
	 * @param area_type the area_type to set
	 */
	public void setArea_type(String area_type) {
		this.area_type = area_type;
	}

	/**
	 * @return the appraisalReliabilityIndex
	 */
	public Float getAppraisalReliabilityIndex() {
		return appraisalReliabilityIndex;
	}

	/**
	 * @param appraisalReliabilityIndex the appraisalReliabilityIndex to set
	 */
	public void setAppraisalReliabilityIndex(Float appraisalReliabilityIndex) {
		this.appraisalReliabilityIndex = appraisalReliabilityIndex;
	}

	
}
