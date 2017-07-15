/**
 * 
 */
package com.noteanalyzer.mvc.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Arvind Ray
 *
 */
public class NoteAnalysisService {

	public static BigDecimal getITV(BigDecimal purchasePrice, BigDecimal marketValue) {
		if (purchasePrice != null && marketValue != null) {
			return purchasePrice.divide(marketValue);
		}
		return BigDecimal.ZERO;
	}

	public static BigDecimal getLTV(BigDecimal currentPrincipleBal, BigDecimal marketValue) {
		if (currentPrincipleBal != null && marketValue != null) {

			return currentPrincipleBal.divide(marketValue);
		}
		return BigDecimal.ZERO;
	}

	public static BigDecimal getEquity(BigDecimal currentLoanBal, BigDecimal marketValue) {
		if (currentLoanBal != null && marketValue != null) {

			return currentLoanBal.subtract(marketValue);
		}
		return BigDecimal.ZERO;
	}

	public static BigDecimal getSimpleInterest(BigDecimal principleBal, BigDecimal rate, BigDecimal term) {
		if (principleBal != null && rate != null && term != null) {
			return principleBal.multiply(term).multiply(rate);
		}
		return BigDecimal.ZERO;
	}

	public static BigDecimal generateOverAllScore(Map<Double, Double> weightedMap) {
		if (weightedMap != null) {
			BigDecimal overAllScore = BigDecimal.ZERO;
			for (Entry<Double, Double> entry : weightedMap.entrySet()) {
				overAllScore = overAllScore.add(BigDecimal.valueOf(entry.getKey() * entry.getValue()));
			}
			return overAllScore;
		}
		return BigDecimal.ZERO;
	}

}
