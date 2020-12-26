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
@Table(name="PRIVILEGE")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = "GET_PRIVILEGE",query="select p from Privileges p  where p.privilegeId =:privilegeId")})
public class Privileges extends AbstractEntity { 
	
	private static final long serialVersionUID = 3337645236789480204L;
	
	public static final String GET_PRIVILEGE = "GET_PRIVILEGE";
	
	@Id
    @Column(name="PRIVILEGE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int privilegeId;
	
    @Column(name="PRIVILEGE_NAME")
	private String privilegeName;

	/**
	 * @return the privilegeId
	 */
	public int getPrivilegeId() {
		return privilegeId;
	}

	/**
	 * @param privilegeId the privilegeId to set
	 */
	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}

	/**
	 * @return the privilegeName
	 */
	public String getPrivilegeName() {
		return privilegeName;
	}

	/**
	 * @param privilegeName the privilegeName to set
	 */
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + privilegeId;
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
		Privileges other = (Privileges) obj;
		if (privilegeId != other.privilegeId)
			return false;
		return true;
	}
    
    
    
    
}
