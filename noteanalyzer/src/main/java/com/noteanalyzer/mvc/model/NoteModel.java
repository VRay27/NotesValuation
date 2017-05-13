package com.noteanalyzer.mvc.model;

import java.io.Serializable;

public class NoteModel implements Serializable {

	/**
	 * This class will hold the data for Note input form.
	 * 
	 * @author Arvind Ray
	 */
	private static final long serialVersionUID = 7343742294405817564L;

	private String typeOfNote;
	private String address;
	private String property;
	private String dateOfNote;
	private String upb;
	private String rate;
	private String pdiPayment;
	private String tdiPayment;
	private String remainingTerm;
	
	
	public String getTypeOfNote() {
		return typeOfNote;
	}
	public void setTypeOfNote(String typeOfNote) {
		this.typeOfNote = typeOfNote;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getDateOfNote() {
		return dateOfNote;
	}
	public void setDateOfNote(String dateOfNote) {
		this.dateOfNote = dateOfNote;
	}
	public String getUpb() {
		return upb;
	}
	public void setUpb(String upb) {
		this.upb = upb;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getPdiPayment() {
		return pdiPayment;
	}
	public void setPdiPayment(String pdiPayment) {
		this.pdiPayment = pdiPayment;
	}
	public String getTdiPayment() {
		return tdiPayment;
	}
	public void setTdiPayment(String tdiPayment) {
		this.tdiPayment = tdiPayment;
	}
	public String getRemainingTerm() {
		return remainingTerm;
	}
	public void setRemainingTerm(String remainingTerm) {
		this.remainingTerm = remainingTerm;
	}
	@Override
	public String toString() {
		return "NoteModel [typeOfNote=" + typeOfNote + ", address=" + address + ", property=" + property
				+ ", dateOfNote=" + dateOfNote + ", upb=" + upb + ", rate=" + rate + ", pdiPayment=" + pdiPayment
				+ ", tdiPayment=" + tdiPayment + ", remainingTerm=" + remainingTerm + "]";
	}

		
	
}
