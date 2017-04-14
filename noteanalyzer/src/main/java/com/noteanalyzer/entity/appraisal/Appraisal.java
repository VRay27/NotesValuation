package com.noteanalyzer.entity.appraisal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;


@Entity
@Table(name="APPRAISAL")
public class Appraisal extends AbstractEntity {

	private static final long serialVersionUID = 3305276391140825995L;

	@Embeddable
	public static class AppraisalId implements Serializable {

		private static final long serialVersionUID = -5144322817622752666L;

		@Column(name="APPRAISAL_SOURCE")
		private String  appraisalSource;
		
		@Column(name="AREA_ID")
		private Integer	areaId;

		public AppraisalId() {
			// TODO Auto-generated constructor stub
		}

		public AppraisalId(String appraisalSource, Integer areaId) {
			this.appraisalSource = appraisalSource;
			this.areaId = areaId;
		}
	}
	 
	@EmbeddedId
	AppraisalId appraisalId = new AppraisalId();
	
	@Column(name="APPRAISAL_RELIABILITY_INDEX")
	private Float appraisalReliabilityIndex;

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
