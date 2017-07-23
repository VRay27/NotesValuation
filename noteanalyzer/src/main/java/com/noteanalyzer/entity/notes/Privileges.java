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
	
    @Column(name="PRIVILEGE_ID")
	private String privilegeId;
	
    @Column(name="PRIVILEGE_NAME")
	private String privilegeName;
    
    
}
