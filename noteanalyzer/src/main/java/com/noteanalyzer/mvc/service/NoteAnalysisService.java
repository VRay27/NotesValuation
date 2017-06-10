/**
 * 
 */
package com.noteanalyzer.mvc.service;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Arvind Ray
 *
 */
public class NoteAnalysisService {

	
	public double getITV(double purchasePrice, double marketValue){
		return purchasePrice/marketValue;
	}

	public double getLTV(double currentPrincipleBal, double marketValue){
		return currentPrincipleBal/marketValue;
	}

	
	public double getEquity(double currentLoanBal, double marketValue){
		return currentLoanBal - marketValue;
	}
	
	public double getSimpleInterest(double principleBal,double rate,double term){
		return principleBal*rate*term;
	}
	
	public double generateOverAllScore(Map<Double,Double> weightedMap){
		double overAllScore = 0.0;
		for(Entry<Double, Double> entry :weightedMap.entrySet()){
			overAllScore += entry.getKey()*entry.getValue();
		}
		return overAllScore;
	}

	/*public double getYield(double )
*/
}

