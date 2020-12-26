package com.noteanalyzer.entity.notes;

import java.math.BigDecimal;

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
@Table(name="LOAN_TYPE")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = "GET_LOAN_TYPE_BY_TYPE",query="select n from LoanType n where n.loanType =:noteTypeCode")})
public class LoanType  extends AbstractEntity {
	
	private static final long serialVersionUID = 6885930415090373746L;
	
	public static final String GET_LOAN_TYPE_BY_TYPE = "GET_LOAN_TYPE_BY_TYPE";

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="LOAN_TYPE_ID")
    private int loanTypeId;
	
	@Column(name="TYPE")
    private String loanType;

	@Column(name="INTEREST_ADJUSTMENT_RULES")
	private String interestAdjustmentRules;
	
	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="TERM_MONTHS")
	private String termMonths;
	
	@Column(name="INTEREST_ONLY_MONTHS")
	private Long interestOnlyMonths;
	
	@Column(name="BALOON_AFTER_MONTHS")
	private Long baloonAfterMonths;
	
	@Column(name="ARM_INDEX_NAME")
	private String armIndexName;
	
	@Column(name="MARGIN")
	private BigDecimal margin;
	
	@Column(name="INTEREST_CAP")
	private BigDecimal interestCap;
	
	
	public LoanType() {
	}


	



	/**
	 * @return the interestAdjustmentRules
	 */
	public String getInterestAdjustmentRules() {
		return interestAdjustmentRules;
	}


	/**
	 * @param interestAdjustmentRules the interestAdjustmentRules to set
	 */
	public void setInterestAdjustmentRules(String interestAdjustmentRules) {
		this.interestAdjustmentRules = interestAdjustmentRules;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the termMonths
	 */
	public String getTermMonths() {
		return termMonths;
	}


	/**
	 * @param termMonths the termMonths to set
	 */
	public void setTermMonths(String termMonths) {
		this.termMonths = termMonths;
	}


	/**
	 * @return the interestOnlyMonths
	 */
	public Long getInterestOnlyMonths() {
		return interestOnlyMonths;
	}


	/**
	 * @param interestOnlyMonths the interestOnlyMonths to set
	 */
	public void setInterestOnlyMonths(Long interestOnlyMonths) {
		this.interestOnlyMonths = interestOnlyMonths;
	}


	/**
	 * @return the baloonAfterMonths
	 */
	public Long getBaloonAfterMonths() {
		return baloonAfterMonths;
	}


	/**
	 * @param baloonAfterMonths the baloonAfterMonths to set
	 */
	public void setBaloonAfterMonths(Long baloonAfterMonths) {
		this.baloonAfterMonths = baloonAfterMonths;
	}


	/**
	 * @return the armIndexName
	 */
	public String getArmIndexName() {
		return armIndexName;
	}


	/**
	 * @param armIndexName the armIndexName to set
	 */
	public void setArmIndexName(String armIndexName) {
		this.armIndexName = armIndexName;
	}


	/**
	 * @return the margin
	 */
	public BigDecimal getMargin() {
		return margin;
	}


	/**
	 * @param margin the margin to set
	 */
	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}


	/**
	 * @return the interestCap
	 */
	public BigDecimal getInterestCap() {
		return interestCap;
	}


	/**
	 * @param interestCap the interestCap to set
	 */
	public void setInterestCap(BigDecimal interestCap) {
		this.interestCap = interestCap;
	}






	/**
	 * @return the loanTypeId
	 */
	public int getLoanTypeId() {
		return loanTypeId;
	}






	/**
	 * @param loanTypeId the loanTypeId to set
	 */
	public void setLoanTypeId(int loanTypeId) {
		this.loanTypeId = loanTypeId;
	}






	/**
	 * @return the loanType
	 */
	public String getLoanType() {
		return loanType;
	}






	/**
	 * @param loanType the loanType to set
	 */
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}






	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + loanTypeId;
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
		LoanType other = (LoanType) obj;
		if (loanTypeId != other.loanTypeId)
			return false;
		return true;
	}


	
}
