/**
 * 
 */
package com.noteanalyzer.mvc.service;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Arvind Ray
 *
 */
public class NoteAnalysisService {

	public static Double getEstimatedITV(String notePrice, String estimatedMarketValue) {
		if (StringUtils.isNotBlank(notePrice) && StringUtils.isNotBlank(estimatedMarketValue) && !"null".equalsIgnoreCase(estimatedMarketValue) && !"null".equalsIgnoreCase(notePrice)) {
			Double notePriceVal = Double.valueOf(notePrice);
			Double estimatedMarketValueVal = Double.valueOf(estimatedMarketValue);
	
			return (double) Math.round(notePriceVal/estimatedMarketValueVal);
		}
		return Double.valueOf(0);
	}

	public static Double getCurrentITV(String notePrice, String marketValue) {
		if (StringUtils.isNotBlank(notePrice) && StringUtils.isNotBlank(marketValue) && !"null".equalsIgnoreCase(marketValue) && !"null".equalsIgnoreCase(notePrice)) {
			Double notePriceVal = Double.valueOf(notePrice);
			Double marketValueVal = Double.valueOf(marketValue);
	
			return (double) Math.round(notePriceVal/marketValueVal);
		}
		return Double.valueOf(0);
	}
	
	public static Double getEstimatedLTV(String unpaidLoanBalance, String estimatedMarketValue) {
		if ( StringUtils.isNotBlank(unpaidLoanBalance) && StringUtils.isNotBlank(estimatedMarketValue) && !"null".equalsIgnoreCase(unpaidLoanBalance) && !"null".equalsIgnoreCase(estimatedMarketValue)) {
			Double unpaidLoanBalanceVal = Double.valueOf(unpaidLoanBalance);
			Double estimatedMarketValueVal = Double.valueOf(estimatedMarketValue);
			return (double) Math.round(unpaidLoanBalanceVal/estimatedMarketValueVal);
		}
		return Double.valueOf(0);
	}

	public static Double getCurrentLTV(String unpaidBal, String marketValue) {
		
		if (StringUtils.isNotBlank(unpaidBal) && StringUtils.isNotBlank(marketValue) && !"null".equalsIgnoreCase(unpaidBal) && !"null".equalsIgnoreCase(marketValue)) {
			Double notePriceVal = Double.valueOf(unpaidBal);
			Double marketVal = Double.valueOf(marketValue);
			return (double) Math.round(notePriceVal/marketVal);
		}
		return Double.valueOf(0);
	}

	
	public static Double getROI(Double payment, Double notePrice) {
		if (payment !=null && notePrice !=null){
			return (double) Math.round((payment*12)/notePrice);
		}
		return Double.valueOf(0);
	}

}
