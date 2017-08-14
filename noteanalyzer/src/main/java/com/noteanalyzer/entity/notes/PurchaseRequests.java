package com.noteanalyzer.entity.notes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

@Entity
@Table(name="PURCHASE_REQUESTS")
public class PurchaseRequests extends AbstractEntity {
	
	private static final long serialVersionUID = 393756493746689997L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_requests_id")
	private int purchaseRequestsId;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "note_id")
	private int noteId;
	
	@Column(name = "STORE_NAME")
	private String storeName;
	
	@Column(name = "REQUEST_DATE")
	private Date requestDate;
	
	@Column(name = "APPROVED_REJECTED")
	private String approvedRejected;
	
	@Column(name = "vendor_note_id")
	private int vendorNoteId;

	@Column(name = "AMOUNT")
	private double storeAPI;

	/**
	 * @return the purchaseRequestsId
	 */
	public int getPurchaseRequestsId() {
		return purchaseRequestsId;
	}

	/**
	 * @param purchaseRequestsId the purchaseRequestsId to set
	 */
	public void setPurchaseRequestsId(int purchaseRequestsId) {
		this.purchaseRequestsId = purchaseRequestsId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the noteId
	 */
	public int getNoteId() {
		return noteId;
	}

	/**
	 * @param noteId the noteId to set
	 */
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	/**
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return the requestDate
	 */
	public Date getRequestDate() {
		return requestDate;
	}

	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * @return the approvedRejected
	 */
	public String getApprovedRejected() {
		return approvedRejected;
	}

	/**
	 * @param approvedRejected the approvedRejected to set
	 */
	public void setApprovedRejected(String approvedRejected) {
		this.approvedRejected = approvedRejected;
	}

	/**
	 * @return the vendorNoteId
	 */
	public int getVendorNoteId() {
		return vendorNoteId;
	}

	/**
	 * @param vendorNoteId the vendorNoteId to set
	 */
	public void setVendorNoteId(int vendorNoteId) {
		this.vendorNoteId = vendorNoteId;
	}

	/**
	 * @return the storeAPI
	 */
	public double getStoreAPI() {
		return storeAPI;
	}

	/**
	 * @param storeAPI the storeAPI to set
	 */
	public void setStoreAPI(double storeAPI) {
		this.storeAPI = storeAPI;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + purchaseRequestsId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchaseRequests other = (PurchaseRequests) obj;
		if (purchaseRequestsId != other.purchaseRequestsId)
			return false;
		return true;
	}
	

	
	

}
