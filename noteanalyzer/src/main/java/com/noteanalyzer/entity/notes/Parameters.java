package com.noteanalyzer.entity.notes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name="PARAMETERS")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = "GET_PARAMETERS_VALUE",query="select p from Parameters p, User u  where u.userId = p.userId and u.userId =:userId and p.parameterName =:parameterName")})
public class Parameters extends AbstractEntity { 
	
	private static final long serialVersionUID = 3337645236789480204L;
	
	public static final String GET_PARAMETERS_VALUE = "GET_PARAMETERS_VALUE";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Parameter_Id")
	private String parameterId;
	
	@Column(name="PARAMETER_NAME")
	private String parameterName;
	
    @Column(name="USER_ID")
	private int userId;
    
    @Column(name="PARAMETER_VALUE")
	private double parameterValue;
    
    @Column(name="MODIFYABLE")
	private String modifyable;
    
    @Column(name="AFFECTED_ENTITY_TYPE")
	private String affectedEntityType;
    

    @Column(name="AFFECTED_ENTITY_ID")
	private String affectedEntityID;
    
    @Column(name="SOURCE")
   	private String source;

	/**
	 * @return the parameterName
	 */
	public String getParameterName() {
		return parameterName;
	}

	/**
	 * @param parameterName the parameterName to set
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
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
	 * @return the parameterValue
	 */
	public double getParameterValue() {
		return parameterValue;
	}

	/**
	 * @param parameterValue the parameterValue to set
	 */
	public void setParameterValue(double parameterValue) {
		this.parameterValue = parameterValue;
	}

	/**
	 * @return the modifyable
	 */
	public String getModifyable() {
		return modifyable;
	}

	/**
	 * @param modifyable the modifyable to set
	 */
	public void setModifyable(String modifyable) {
		this.modifyable = modifyable;
	}

	/**
	 * @return the affectedEntityType
	 */
	public String getAffectedEntityType() {
		return affectedEntityType;
	}

	/**
	 * @param affectedEntityType the affectedEntityType to set
	 */
	public void setAffectedEntityType(String affectedEntityType) {
		this.affectedEntityType = affectedEntityType;
	}

	/**
	 * @return the affectedEntityID
	 */
	public String getAffectedEntityID() {
		return affectedEntityID;
	}

	/**
	 * @param affectedEntityID the affectedEntityID to set
	 */
	public void setAffectedEntityID(String affectedEntityID) {
		this.affectedEntityID = affectedEntityID;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	

	/**
	 * @return the parameterId
	 */
	public String getParameterId() {
		return parameterId;
	}

	/**
	 * @param parameterId the parameterId to set
	 */
	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((parameterName == null) ? 0 : parameterName.hashCode());
		result = prime * result + userId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Parameters)) {
			return false;
		}
		Parameters other = (Parameters) obj;
		if (parameterName == null) {
			if (other.parameterName != null) {
				return false;
			}
		} else if (!parameterName.equals(other.parameterName)) {
			return false;
		}
		if (userId != other.userId) {
			return false;
		}
		return true;
	}
       
    
    
}
