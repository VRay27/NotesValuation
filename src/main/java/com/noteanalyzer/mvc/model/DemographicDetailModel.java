/**
 * 
 */
package com.noteanalyzer.mvc.model;

import java.io.Serializable;

/**
 * This class will hold the demographic details for note  details page.
 * @author Arvind ray
 *
 */
public class DemographicDetailModel implements Serializable {
	
	private String crime;

	private String foreClosure;
	
	private String schoolScore;

	/**
	 * @return the crime
	 */
	public String getCrime() {
		return crime;
	}

	/**
	 * @param crime the crime to set
	 */
	public void setCrime(String crime) {
		this.crime = crime;
	}

	/**
	 * @return the foreClosure
	 */
	public String getForeClosure() {
		return foreClosure;
	}

	/**
	 * @param foreClosure the foreClosure to set
	 */
	public void setForeClosure(String foreClosure) {
		this.foreClosure = foreClosure;
	}

	/**
	 * @return the schoolScore
	 */
	public String getSchoolScore() {
		return schoolScore;
	}

	/**
	 * @param schoolScore the schoolScore to set
	 */
	public void setSchoolScore(String schoolScore) {
		this.schoolScore = schoolScore;
	}

	
	
}
