package com.noteanalyzer.mvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoanTypeModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1924873727695219842L;
	private String loanTypeCode;
	private String loanTypeValue;
	private String interestAdjustmentRules;
	private String termMonths;
	private Long interestOnlyMonths;
	private Long baloonAfterMonths;
	private String armIndexName;
	private BigDecimal margin;
	private BigDecimal interestCap;
	
	
	public LoanTypeModel() {
		super();
	}
	
	
	/**
	 * @return the loanTypeCode
	 */
	public String getLoanTypeCode() {
		return loanTypeCode;
	}


	/**
	 * @param loanTypeCode the loanTypeCode to set
	 */
	public void setLoanTypeCode(String loanTypeCode) {
		this.loanTypeCode = loanTypeCode;
	}


	/**
	 * @return the loanTypeValue
	 */
	public String getLoanTypeValue() {
		return loanTypeValue;
	}


	/**
	 * @param loanTypeValue the loanTypeValue to set
	 */
	public void setLoanTypeValue(String loanTypeValue) {
		this.loanTypeValue = loanTypeValue;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loanTypeCode == null) ? 0 : loanTypeCode.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanTypeModel other = (LoanTypeModel) obj;
		if (loanTypeCode == null) {
			if (other.loanTypeCode != null)
				return false;
		} else if (!loanTypeCode.equals(other.loanTypeCode))
			return false;
		return true;
	}


}
