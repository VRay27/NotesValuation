package com.noteanalyzer.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.noteanalyzer.utility.NoteUtility;

import lombok.ToString;

@ToString
@MappedSuperclass
public abstract class AbstractEntity implements Serializable{

	/**
	 * Serial Version Id.
	 */
	private static final long serialVersionUID = 2051112041784928754L;
	
	/**
	 * The last time this record get updated.
	 */
	@Column(name="UPDATED_DATE_TIME")
	private ZonedDateTime updatedDateTime;
	
	/**
	 * This will store the updated user name.
	 */
	@Column(name="UPDATED_BY")
	private String updatedBy;
	/**
	 * The time when this record get created.
	 */
	@Column(name="CREATED_DATE_TIME")
	private ZonedDateTime createdDateTime;

	/**
	 * It will store created user name.
	 */
	
	@Column(name="CREATED_BY")
	private String createdBy;

	public ZonedDateTime getUpdatedDateTime() {
		if(updatedDateTime == null){
			return ZonedDateTime.now();
		}
		return updatedDateTime;
	}

	public void setUpdatedDateTime(ZonedDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public String getUpdatedBy() {
		if(updatedBy == null){
			return NoteUtility.getLoggedInUserName();
		}
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public ZonedDateTime getCreatedDateTime() {
		if(createdDateTime == null){
			return ZonedDateTime.now();
		}
		return createdDateTime;
	}

	public void setCreatedDateTime(ZonedDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getCreatedBy() {
		if(createdBy == null){
			return NoteUtility.getLoggedInUserName();
		}
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdDateTime == null) ? 0 : createdDateTime.hashCode());
		result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result + ((updatedDateTime == null) ? 0 : updatedDateTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdDateTime == null) {
			if (other.createdDateTime != null)
				return false;
		} else if (!createdDateTime.equals(other.createdDateTime))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (updatedDateTime == null) {
			if (other.updatedDateTime != null)
				return false;
		} else if (!updatedDateTime.equals(other.updatedDateTime))
			return false;
		return true;
	}
	
	
	
}
