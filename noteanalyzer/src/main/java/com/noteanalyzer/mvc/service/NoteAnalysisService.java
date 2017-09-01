/**
 * 
 */
package com.noteanalyzer.mvc.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Arvind Ray
 *
 */
public class NoteAnalysisService {

	public static Double getEstimatedITV(String notePrice, String estimatedMarketValue) {
		if (StringUtils.isNotBlank(notePrice) && StringUtils.isNotBlank(estimatedMarketValue) && !"null".equalsIgnoreCase(estimatedMarketValue) && !"null".equalsIgnoreCase(notePrice)) {
			BigDecimal notePriceVal = new  BigDecimal(notePrice);
			BigDecimal estimatedMarketValueVal = new  BigDecimal(estimatedMarketValue);
			BigDecimal outVal=  notePriceVal.divide(estimatedMarketValueVal, 2, RoundingMode.HALF_UP);
			return outVal.doubleValue();
		}
		return Double.valueOf(0);
	}

	public static Double getCurrentITV(String notePrice, String marketValue) {
		if (StringUtils.isNotBlank(notePrice) && StringUtils.isNotBlank(marketValue) && !"null".equalsIgnoreCase(marketValue) && !"null".equalsIgnoreCase(notePrice)) {
			BigDecimal notePriceVal = new  BigDecimal(notePrice);
			BigDecimal marketValueVal =  new  BigDecimal(marketValue);
			BigDecimal outVal=  notePriceVal.divide(marketValueVal, 2, RoundingMode.HALF_UP);
			return outVal.doubleValue();
		}
		return Double.valueOf(0);
	}
	
	public static Double getEstimatedLTV(String unpaidLoanBalance, String estimatedMarketValue) {
		if ( StringUtils.isNotBlank(unpaidLoanBalance) && StringUtils.isNotBlank(estimatedMarketValue) && !"null".equalsIgnoreCase(unpaidLoanBalance) && !"null".equalsIgnoreCase(estimatedMarketValue)) {
			BigDecimal unpaidLoanBalanceVal = new  BigDecimal(unpaidLoanBalance);
			BigDecimal estimatedMarketValueVal = new  BigDecimal(estimatedMarketValue);
			BigDecimal outVal=  unpaidLoanBalanceVal.divide(estimatedMarketValueVal, 2, RoundingMode.HALF_UP);
			return outVal.doubleValue();
		}
		return Double.valueOf(0);
	}

	public static Double getCurrentLTV(String unpaidBal, String marketValue) {
		
		if (StringUtils.isNotBlank(unpaidBal) && StringUtils.isNotBlank(marketValue) && !"null".equalsIgnoreCase(unpaidBal) && !"null".equalsIgnoreCase(marketValue)) {
			BigDecimal notePriceVal = new  BigDecimal(unpaidBal);
			BigDecimal marketVal = new  BigDecimal(marketValue);
			BigDecimal outVal=  notePriceVal.divide(marketVal, 2, RoundingMode.HALF_UP);
			return outVal.doubleValue();
		}
		return Double.valueOf(0);
	}

	
	public static Double getROI(Double payment, Double notePrice, String noteType) {
		if (payment !=null && notePrice !=null && !"N".equalsIgnoreCase(noteType)){
			BigDecimal paymentVal = new  BigDecimal(payment);
			BigDecimal notePriceVal = new  BigDecimal(notePrice);
			BigDecimal outVal =  paymentVal.multiply(new BigDecimal(12));
			outVal = outVal.divide(notePriceVal, 2, RoundingMode.HALF_UP);
			return outVal.doubleValue();
		}
		return Double.valueOf(0);
	}

}
