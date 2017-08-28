/**
 * 
 */
package com.noteanalyzer.mvc.service;

import java.math.RoundingMode;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Arvind Ray
 *
 */
public class NoteAnalysisService {

	public static Double getOriginalEstimatedITV(String notePrice, String estimatedMarketValue) {
		if (StringUtils.isNotBlank(notePrice) && StringUtils.isNotBlank(estimatedMarketValue) && !"null".equalsIgnoreCase(estimatedMarketValue) && !"null".equalsIgnoreCase(notePrice)) {
			Double notePriceVal = Double.valueOf(notePrice);
			Double estimatedMarketValueVal = Double.valueOf(estimatedMarketValue);
	
			return notePriceVal/estimatedMarketValueVal;
		}
		return Double.valueOf(0);
	}

	public static Double getCurrentITV(String notePrice, String marketValue) {
		if (StringUtils.isNotBlank(notePrice) && StringUtils.isNotBlank(marketValue) && !"null".equalsIgnoreCase(marketValue) && !"null".equalsIgnoreCase(notePrice)) {
			Double notePriceVal = Double.valueOf(notePrice);
			Double marketValueVal = Double.valueOf(marketValue);
	
			return notePriceVal/marketValueVal;
		}
		return Double.valueOf(0);
	}
	
	public static Double getOriginalLTV(String originalPrincipalBal, String originalPropertyValue) {
		if ( StringUtils.isNotBlank(originalPrincipalBal) && StringUtils.isNotBlank(originalPropertyValue) && !"null".equalsIgnoreCase(originalPropertyValue) && !"null".equalsIgnoreCase(originalPrincipalBal)) {
			Double originalPrincipalBalance = Double.valueOf(originalPrincipalBal);
			Double originalPropertyVal = Double.valueOf(originalPropertyValue);
			return originalPrincipalBalance/originalPropertyVal;
		}
		return Double.valueOf(0);
	}

	public static Double getCurrentLTV(String unpaidBal, String marketValue) {
		
		if (StringUtils.isNotBlank(unpaidBal) && StringUtils.isNotBlank(marketValue) && !"null".equalsIgnoreCase(unpaidBal) && !"null".equalsIgnoreCase(marketValue)) {
			Double notePriceVal = Double.valueOf(unpaidBal);
			Double marketVal = Double.valueOf(marketValue);
			return notePriceVal/marketVal;
		}
		return Double.valueOf(0);
	}

	
	public static Double getROI(Double payment, Double notePrice) {
		if (payment !=null && notePrice !=null){
			return (payment*12)/notePrice;
		}
		return Double.valueOf(0);
	}

}
