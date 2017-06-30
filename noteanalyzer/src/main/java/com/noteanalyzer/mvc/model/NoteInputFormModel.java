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

	private List<NoteTypeModel> noteTypeList;
	private NoteTypeModel selNoteType;
	private List<PropertyTypeModel> propTypeList;
	private PropertyTypeModel selPropType;
	private AddressModel addressModel;
	private String noteDate;
	private String upb;
	private String rate;
	private String pdiPayment;
	private String tdiPayment;
	private String originalTerm;
	private String originalPrincipleBalance;
	private String paymentHistory;
	private String noOfPaymentRemaining;

	/**
	 * @return the noteTypeList
	 */
	public List<NoteTypeModel> getNoteTypeList() {
		if (noteTypeList == null) {
			noteTypeList = new ArrayList<NoteTypeModel>();
		}
		return noteTypeList;
	}

	/**
	 * @param noteTypeList
	 *            the noteTypeList to set
	 */
	public void setNoteTypeList(List<NoteTypeModel> noteTypeList) {
		this.noteTypeList = noteTypeList;
	}

	/**
	 * @return the selNoteType
	 */
	public NoteTypeModel getSelNoteType() {
		if (selNoteType == null) {
			selNoteType = new NoteTypeModel();
		}
		return selNoteType;
	}

	/**
	 * @param selNoteType
	 *            the selNoteType to set
	 */
	public void setSelNoteType(NoteTypeModel selNoteType) {
		this.selNoteType = selNoteType;
	}

	/**
	 * @return the propTypeList
	 */
	public List<PropertyTypeModel> getPropTypeList() {
		if (propTypeList == null) {
			propTypeList = new ArrayList<PropertyTypeModel>();
		}
		return propTypeList;
	}

	/**
	 * @param propTypeList
	 *            the propTypeList to set
	 */
	public void setPropTypeList(List<PropertyTypeModel> propTypeList) {
		this.propTypeList = propTypeList;
	}

	/**
	 * @return the selPropType
	 */
	public PropertyTypeModel getSelPropType() {
		return selPropType;
	}

	/**
	 * @param selPropType
	 *            the selPropType to set
	 */
	public void setSelPropType(PropertyTypeModel selPropType) {
		this.selPropType = selPropType;
	}

	/**
	 * @return the selAddress
	 */
	public AddressModel getAddress() {
		if (addressModel == null) {
			addressModel = new AddressModel();
		}
		return addressModel;
	}

	/**
	 * @param selAddress
	 *            the selAddress to set
	 */
	public void setAddress(AddressModel addressModel) {
		this.addressModel = addressModel;
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
	
	

	/**
	 * @return the addressModel
	 *//*
	public AddressModel getAddressModel() {
		return addressModel;
	}

	*//**
	 * @param addressModel the addressModel to set
	 *//*
	public void setAddressModel(AddressModel addressModel) {
		this.addressModel = addressModel;
	}
*/
	/**
	 * @return the originalPrincipleBalance
	 */
	public String getOriginalPrincipleBalance() {
		return originalPrincipleBalance;
	}

	/**
	 * @param originalPrincipleBalance the originalPrincipleBalance to set
	 */
	public void setOriginalPrincipleBalance(String originalPrincipleBalance) {
		this.originalPrincipleBalance = originalPrincipleBalance;
	}
	
	

	/**
	 * @return the paymentHistory
	 */
	public String getPaymentHistory() {
		return paymentHistory;
	}

	/**
	 * @param paymentHistory the paymentHistory to set
	 */
	public void setPaymentHistory(String paymentHistory) {
		this.paymentHistory = paymentHistory;
	}

	/**
	 * @return the noOfPaymentRemaining
	 */
	public String getNoOfPaymentRemaining() {
		return noOfPaymentRemaining;
	}

	/**
	 * @param noOfPaymentRemaining the noOfPaymentRemaining to set
	 */
	public void setNoOfPaymentRemaining(String noOfPaymentRemaining) {
		this.noOfPaymentRemaining = noOfPaymentRemaining;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NoteInputFormModel [noteTypeList=" + noteTypeList + ", selNoteType=" + selNoteType + ", propTypeList="
				+ propTypeList + ", selPropType=" + selPropType + ", address=" + addressModel + ", noteDate=" + noteDate
				+ ", upb=" + upb + ", rate=" + rate + ", pdiPayment=" + pdiPayment + ", tdiPayment=" + tdiPayment
				+ ", originalTerm=" + originalTerm + "]";
	}

}
