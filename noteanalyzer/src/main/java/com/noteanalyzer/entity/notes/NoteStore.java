package com.noteanalyzer.entity.notes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

@Entity
@Table(name="NOTE_STORE")
public class NoteStore extends AbstractEntity {
	
	private static final long serialVersionUID = 393756493746689997L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STORE_ID")
	private int storeId;
	
	@Column(name = "STORE_NAME")
	private String storeName;
	
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "STORE_API")
	private String storeAPI;

	/**
	 * @return the storeId
	 */
	public int getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the storeAPI
	 */
	public String getStoreAPI() {
		return storeAPI;
	}

	/**
	 * @param storeAPI the storeAPI to set
	 */
	public void setStoreAPI(String storeAPI) {
		this.storeAPI = storeAPI;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + storeId;
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
		NoteStore other = (NoteStore) obj;
		if (storeId != other.storeId)
			return false;
		return true;
	}
	
	
	

}
