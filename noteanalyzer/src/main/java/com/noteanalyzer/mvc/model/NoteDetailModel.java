/**
 * 
 */
package com.noteanalyzer.mvc.model;

import java.io.Serializable;

/**
 * This class will be hold the data to display for note detail page.
 * @author Arvind Ray
 *
 */
public class NoteDetailModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2153058918300513346L;
	
	private String noteId;
	private String rate;
	private String payment;
	private String upb;
	
	private PropertyDetailModel propertyDetailModel;

	
	
	/**
	 * @return the noteId
	 */
	public String getNoteId() {
		return noteId;
	}

	/**
	 * @param noteId the noteId to set
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

	/**
	 * @return the payment
	 */
	public String getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}

	/**
	 * @return the upb
	 */
	public String getUpb() {
		return upb;
	}

	/**
	 * @param upb the upb to set
	 */
	public void setUpb(String upb) {
		this.upb = upb;
	}

	/**
	 * @return the propertyDetailModel
	 */
	public PropertyDetailModel getPropertyDetailModel() {
		return propertyDetailModel;
	}

	/**
	 * @param propertyDetailModel the propertyDetailModel to set
	 */
	public void setPropertyDetailModel(PropertyDetailModel propertyDetailModel) {
		this.propertyDetailModel = propertyDetailModel;
	}

	@Override
	public String toString() {
		return "NoteDetailModel [rate=" + rate + ", payment=" + payment + ", upb=" + upb + ", propertyDetailModel="
				+ propertyDetailModel + "]";
	}
	
	
	

}
