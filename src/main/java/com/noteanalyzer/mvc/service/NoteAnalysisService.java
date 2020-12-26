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
			BigDecimal outVal=  notePriceVal.divide(estimatedMarketValueVal, 4, RoundingMode.HALF_UP);
			outVal = outVal.multiply(new BigDecimal(100));
			outVal = outVal.setScale(2, BigDecimal.ROUND_HALF_UP);
			return outVal.doubleValue();
		}
		return Double.valueOf(0);
	}

	public static Double getCurrentITV(String notePrice, String marketValue) {
		if (StringUtils.isNotBlank(notePrice) && StringUtils.isNotBlank(marketValue) && !"null".equalsIgnoreCase(marketValue) && !"null".equalsIgnoreCase(notePrice)) {
			BigDecimal notePriceVal = new  BigDecimal(notePrice);
			BigDecimal marketValueVal =  new  BigDecimal(marketValue);
			BigDecimal outVal=  notePriceVal.divide(marketValueVal, 4, RoundingMode.HALF_UP);
			outVal = outVal.multiply(new BigDecimal(100));
			outVal = outVal.setScale(2, BigDecimal.ROUND_HALF_UP);
			return outVal.doubleValue();
		}
		return Double.valueOf(0);
	}
	
	public static Double getEstimatedLTV(String unpaidLoanBalance, String estimatedMarketValue) {
		if ( StringUtils.isNotBlank(unpaidLoanBalance) && StringUtils.isNotBlank(estimatedMarketValue) && !"null".equalsIgnoreCase(unpaidLoanBalance) && !"null".equalsIgnoreCase(estimatedMarketValue)) {
			BigDecimal unpaidLoanBalanceVal = new  BigDecimal(unpaidLoanBalance);
			BigDecimal estimatedMarketValueVal = new  BigDecimal(estimatedMarketValue);
			BigDecimal outVal=  unpaidLoanBalanceVal.divide(estimatedMarketValueVal, 4, RoundingMode.HALF_UP);
			outVal = outVal.multiply(new BigDecimal(100));
			outVal = outVal.setScale(2, BigDecimal.ROUND_HALF_UP);
			return outVal.doubleValue();
		}
		return Double.valueOf(0);
	}

	public static Double getCurrentLTV(String unpaidBal, String marketValue) {
		
		if (StringUtils.isNotBlank(unpaidBal) && StringUtils.isNotBlank(marketValue) && !"null".equalsIgnoreCase(unpaidBal) && !"null".equalsIgnoreCase(marketValue)) {
			BigDecimal notePriceVal = new  BigDecimal(unpaidBal);
			BigDecimal marketVal = new  BigDecimal(marketValue);
			BigDecimal outVal=  notePriceVal.divide(marketVal, 4, RoundingMode.HALF_UP);
			outVal = outVal.multiply(new BigDecimal(100));
			outVal = outVal.setScale(2, BigDecimal.ROUND_HALF_UP);
			return outVal.doubleValue();
		}
		return Double.valueOf(0);
	}

	
	public static Double getROI(Double payment, Double notePrice, String noteType) {
		if (payment !=null && notePrice !=null && !"N".equalsIgnoreCase(noteType)){
			BigDecimal paymentVal = new  BigDecimal(Math.abs(payment));
			BigDecimal notePriceVal = new  BigDecimal(notePrice);
			BigDecimal outVal =  paymentVal.multiply(new BigDecimal(12));
			outVal = outVal.divide(notePriceVal, 4, RoundingMode.HALF_UP);
			outVal = outVal.multiply(new BigDecimal(100));
			outVal = outVal.setScale(2, BigDecimal.ROUND_HALF_UP);
			return outVal.doubleValue();
		}
		return Double.valueOf(0);
	}
	
	
	public static  double calculateRate(double nper, double pmt, double pv, double fv, double type, double guess) {
	      //FROM MS http://office.microsoft.com/en-us/excel-help/rate-HP005209232.aspx
	      int FINANCIAL_MAX_ITERATIONS = 20;//Bet accuracy with 128
	      double FINANCIAL_PRECISION = 0.0000001;//1.0e-8

	      double y, y0, y1, x0, x1 = 0, f = 0, i = 0;
	      double rate = guess;
	      if (Math.abs(rate) < FINANCIAL_PRECISION) {
	         y = pv * (1 + nper * rate) + pmt * (1 + rate * type) * nper + fv;
	      } else {
	         f = Math.exp(nper * Math.log(1 + rate));
	         y = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;
	      }
	      y0 = pv + pmt * nper + fv;
	      y1 = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;

	      // find root by Newton secant method
	      i = x0 = 0.0;
	      x1 = rate;
	      while ((Math.abs(y0 - y1) > FINANCIAL_PRECISION) && (i < FINANCIAL_MAX_ITERATIONS)) {
	         rate = (y1 * x0 - y0 * x1) / (y1 - y0);
	         x0 = x1;
	         x1 = rate;

	         if (Math.abs(rate) < FINANCIAL_PRECISION) {
	            y = pv * (1 + nper * rate) + pmt * (1 + rate * type) * nper + fv;
	         } else {
	            f = Math.exp(nper * Math.log(1 + rate));
	            y = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;
	         }

	         y0 = y1;
	         y1 = y;
	         ++i;
	      }
	      return rate;
	   }


}
