package com.noteanalyzer.entity.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;
import com.noteanalyzer.entity.notes.Property;
/*
UPDATE AREA_RELATIONS e,
(SELECT @n := 0) m
SET e.AREA_RELATIONS_ID = @n := @n + 1;*/
@Entity
@Table(name = "AREA_RELATIONS")
public class AreaRelations extends AbstractEntity {

	private static final long serialVersionUID = -7773399468661533735L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AREA_RELATIONS_ID")
	private int areaRelationsId;
	
	@Column(name = "AREA_ID")
	private String areaId;
	
	@Column(name = "PARENT_AREA_ID")
	private String parentAreaId;
	
	@Column(name = "PARENT_AREA_TYPE")
	private String parentAreaType;
	
	@Column(name = "AREA_TYPE")
	private String areaType;

	@Column(name = "Relation")
	private String relation;

	/**
	 * @return the areaRelationsId
	 */
	public int getAreaRelationsId() {
		return areaRelationsId;
	}

	/**
	 * @param areaRelationsId the areaRelationsId to set
	 */
	public void setAreaRelationsId(int areaRelationsId) {
		this.areaRelationsId = areaRelationsId;
	}

	/**
	 * @return the areaId
	 */
	public String getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	/**
	 * @return the parentAreaId
	 */
	public String getParentAreaId() {
		return parentAreaId;
	}

	/**
	 * @param parentAreaId the parentAreaId to set
	 */
	public void setParentAreaId(String parentAreaId) {
		this.parentAreaId = parentAreaId;
	}

	/**
	 * @return the parentAreaType
	 */
	public String getParentAreaType() {
		return parentAreaType;
	}

	/**
	 * @param parentAreaType the parentAreaType to set
	 */
	public void setParentAreaType(String parentAreaType) {
		this.parentAreaType = parentAreaType;
	}

	/**
	 * @return the areaType
	 */
	public String getAreaType() {
		return areaType;
	}

	/**
	 * @param areaType the areaType to set
	 */
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * @param relation the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + areaRelationsId;
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
		AreaRelations other = (AreaRelations) obj;
		if (areaRelationsId != other.areaRelationsId)
			return false;
		return true;
	}




}
