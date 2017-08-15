/**
 * 
 */
package com.noteanalyzer.mvc.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Arvind Ray
 *
 */
public class NoteAnalysisService {

	public static BigDecimal getEffectiveLTV(String notePrice, String originalPropertyValue) {
		if (StringUtils.isNotBlank(notePrice) && StringUtils.isNotBlank(originalPropertyValue) && !"null".equalsIgnoreCase(originalPropertyValue) && !"null".equalsIgnoreCase(notePrice)) {
			BigDecimal notePriceVal = BigDecimal.valueOf(Double.valueOf(notePrice));
			BigDecimal originalPropertyVal = BigDecimal.valueOf(Double.valueOf(originalPropertyValue));
	
			return notePriceVal.divide(originalPropertyVal,2, RoundingMode.HALF_UP);
		}
		return BigDecimal.ZERO;
	}

	public static BigDecimal getOriginalLTV(String originalPrincipalBal, String originalPropertyValue) {
		if ( StringUtils.isNotBlank(originalPrincipalBal) && StringUtils.isNotBlank(originalPropertyValue) && !"null".equalsIgnoreCase(originalPropertyValue) && !"null".equalsIgnoreCase(originalPrincipalBal)) {
			BigDecimal originalPrincipalBalance = BigDecimal.valueOf(Double.valueOf(originalPrincipalBal));
			BigDecimal originalPropertyVal = BigDecimal.valueOf(Double.valueOf(originalPropertyValue));
			return originalPrincipalBalance.divide(originalPropertyVal,2, RoundingMode.HALF_UP);
		}
		return BigDecimal.ZERO;
	}

	public static BigDecimal getCurrentEffectiveLTV(String notePrice, String marketValue) {
		
		if (StringUtils.isNotBlank(notePrice) && StringUtils.isNotBlank(marketValue) && !"null".equalsIgnoreCase(notePrice) && !"null".equalsIgnoreCase(marketValue)) {
			BigDecimal notePriceVal = BigDecimal.valueOf(Double.valueOf(notePrice));
			BigDecimal marketVal = BigDecimal.valueOf(Double.valueOf(marketValue));
			return notePriceVal.divide(marketVal,2, RoundingMode.HALF_UP);
		}
		return BigDecimal.ZERO;
	}

	public static BigDecimal getSimpleInterest(BigDecimal principleBal, BigDecimal rate, BigDecimal term) {
		if (principleBal != null && rate != null && term != null ) {
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

	public static BigDecimal getROI() {
		// TODO Auto-generated method stub
		return BigDecimal.ZERO;
	}

}
