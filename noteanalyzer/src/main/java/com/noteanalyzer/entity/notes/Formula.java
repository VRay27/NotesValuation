package com.noteanalyzer.entity.notes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name = "FORMULA")
@ToString(callSuper = true)

public class Formula extends AbstractEntity {
	
	private static final long serialVersionUID = -4579715653832637368L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FORMULA_ID")
	private int formulaId;
	
	@Column(name = "FORMULA_NAME")
	private String formulaName;

	
	@Column(name = "USER_ID")
	private int userId;

	
	@Column(name = "PROPERTY_TYPE")
	private String propertyType;

	
	@Column(name = "AREA_ID")
	private int areaId;
	
	@Column(name = "BORROWER_TYPE")
	private String borrowerType;
	
	@Column(name = "NOTE_TYPE")
	private String noteType;
	
	@Column(name = "VALUE_ADJUSTMENT")
	private String valueAdjustment;
	
	@Column(name = "RISK_ADJUSTMENT")
	private String riskAdjustment;
	/**
	 * @return the formulaId
	 */
	public int getFormulaId() {
		return formulaId;
	}
	/**
	 * @param formulaId the formulaId to set
	 */
	public void setFormulaId(int formulaId) {
		this.formulaId = formulaId;
	}
	/**
	 * @return the formulaName
	 */
	public String getFormulaName() {
		return formulaName;
	}
	/**
	 * @param formulaName the formulaName to set
	 */
	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the propertyType
	 */
	public String getPropertyType() {
		return propertyType;
	}
	/**
	 * @param propertyType the propertyType to set
	 */
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	/**
	 * @return the areaId
	 */
	public int getAreaId() {
		return areaId;
	}
	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	/**
	 * @return the borrowerType
	 */
	public String getBorrowerType() {
		return borrowerType;
	}
	/**
	 * @param borrowerType the borrowerType to set
	 */
	public void setBorrowerType(String borrowerType) {
		this.borrowerType = borrowerType;
	}
	/**
	 * @return the noteType
	 */
	public String getNoteType() {
		return noteType;
	}
	/**
	 * @param noteType the noteType to set
	 */
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	/**
	 * @return the valueAdjustment
	 */
	public String getValueAdjustment() {
		return valueAdjustment;
	}
	/**
	 * @param valueAdjustment the valueAdjustment to set
	 */
	public void setValueAdjustment(String valueAdjustment) {
		this.valueAdjustment = valueAdjustment;
	}
	/**
	 * @return the riskAdjustment
	 */
	public String getRiskAdjustment() {
		return riskAdjustment;
	}
	/**
	 * @param riskAdjustment the riskAdjustment to set
	 */
	public void setRiskAdjustment(String riskAdjustment) {
		this.riskAdjustment = riskAdjustment;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + formulaId;
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
		Formula other = (Formula) obj;
		if (formulaId != other.formulaId)
			return false;
		return true;
	}

	
	

}
