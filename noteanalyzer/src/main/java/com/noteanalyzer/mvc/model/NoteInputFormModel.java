package com.noteanalyzer.mvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

public class NoteInputFormModel implements Serializable {

	/**
	 * This class will hold the data for Note input form.
	 * 
	 * @author Arvind Ray
	 */
	private static final long serialVersionUID = 7343742294405817564L;

	private List<NoteType> noteTypeList;
	private NoteType selNoteType;
	private List<PropertyType> propTypeList;
	private PropertyType selPropType;
	private Address address;
	private String noteDate;
	private String upb;
	private String rate;
	private String pdiPayment;
	private String tdiPayment;
	private String originalTerm;

	/**
	 * @return the noteTypeList
	 */
	public List<NoteType> getNoteTypeList() {
		if (noteTypeList == null) {
			noteTypeList = new ArrayList<NoteType>();
		}
		return noteTypeList;
	}

	/**
	 * @param noteTypeList
	 *            the noteTypeList to set
	 */
	public void setNoteTypeList(List<NoteType> noteTypeList) {
		this.noteTypeList = noteTypeList;
	}

	/**
	 * @return the selNoteType
	 */
	public NoteType getSelNoteType() {
		if (selNoteType == null) {
			selNoteType = new NoteType();
		}
		return selNoteType;
	}

	/**
	 * @param selNoteType
	 *            the selNoteType to set
	 */
	public void setSelNoteType(NoteType selNoteType) {
		this.selNoteType = selNoteType;
	}

	/**
	 * @return the propTypeList
	 */
	public List<PropertyType> getPropTypeList() {
		if (propTypeList == null) {
			propTypeList = new ArrayList<PropertyType>();
		}
		return propTypeList;
	}

	/**
	 * @param propTypeList
	 *            the propTypeList to set
	 */
	public void setPropTypeList(List<PropertyType> propTypeList) {
		this.propTypeList = propTypeList;
	}

	/**
	 * @return the selPropType
	 */
	public PropertyType getSelPropType() {
		return selPropType;
	}

	/**
	 * @param selPropType
	 *            the selPropType to set
	 */
	public void setSelPropType(PropertyType selPropType) {
		this.selPropType = selPropType;
	}

	/**
	 * @return the selAddress
	 */
	public Address getAddress() {
		if (address == null) {
			address = new Address();
		}
		return address;
	}

	/**
	 * @param selAddress
	 *            the selAddress to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the noteDate
	 */
	public String getNoteDate() {
		return noteDate;
	}

	/**
	 * @param noteDate
	 *            the noteDate to set
	 */
	public void setNoteDate(String noteDate) {
		this.noteDate = noteDate;
	}

	/**
	 * @return the upb
	 */
	public String getUpb() {
		return upb;
	}

	/**
	 * @param upb
	 *            the upb to set
	 */
	public void setUpb(String upb) {
		this.upb = upb;
	}

	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

	/**
	 * @return the pdiPayment
	 */
	public String getPdiPayment() {
		return pdiPayment;
	}

	/**
	 * @param pdiPayment
	 *            the pdiPayment to set
	 */
	public void setPdiPayment(String pdiPayment) {
		this.pdiPayment = pdiPayment;
	}

	/**
	 * @return the tdiPayment
	 */
	public String getTdiPayment() {
		return tdiPayment;
	}

	/**
	 * @param tdiPayment
	 *            the tdiPayment to set
	 */
	public void setTdiPayment(String tdiPayment) {
		this.tdiPayment = tdiPayment;
	}

	/**
	 * @return the originalTerm
	 */
	public String getOriginalTerm() {
		return originalTerm;
	}

	/**
	 * @param originalTerm
	 *            the originalTerm to set
	 */
	public void setOriginalTerm(String originalTerm) {
		this.originalTerm = originalTerm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NoteInputFormModel [noteTypeList=" + noteTypeList + ", selNoteType=" + selNoteType + ", propTypeList="
				+ propTypeList + ", selPropType=" + selPropType + ", address=" + address + ", noteDate=" + noteDate
				+ ", upb=" + upb + ", rate=" + rate + ", pdiPayment=" + pdiPayment + ", tdiPayment=" + tdiPayment
				+ ", originalTerm=" + originalTerm + "]";
	}

}
