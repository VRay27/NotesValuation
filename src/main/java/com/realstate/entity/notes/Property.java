package com.realstate.entity.notes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.realstate.entity.AbstractEntity;

import lombok.ToString;
 
@Entity
@Table(name="PROPERTY")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = Property.FETCH_PROPERTY_DETAILS,query="select propertyId from Property p where p.propertyId =:propertyId")})
public class Property extends AbstractEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6408731281219126859L;

	/**
	 * 
	 */
	
	public static final String FETCH_PROPERTY_DETAILS = "FETCH_PROPERTY_DETAILS";

	@Id
    @Column(name="PROP_ID")
    private Integer propertyId;
 
    @Column(name="PROP_NAME")
    private String propertyName;
 
    @Column(name="PROP_DESC")
    private String propertyDescription;

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyDescription() {
		return propertyDescription;
	}

	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((propertyId == null) ? 0 : propertyId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		if (propertyId == null) {
			if (other.propertyId != null)
				return false;
		} else if (!propertyId.equals(other.propertyId))
			return false;
		return true;
	}

	
 
   
}
