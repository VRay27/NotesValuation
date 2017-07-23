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
@Table(name="PROPERTY_AREAS")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = "GET_PROPERTY_AREA",query="select p from PropertyArea p  where p.propertyId =:propertyId"),
				@NamedQuery(name = "GET_PROP_BY_AREA_ID",query="select p from PropertyArea p  where p.areaId =:areaId and p.areaType =:areaType")})
public class PropertyArea extends AbstractEntity { 
	
	private static final long serialVersionUID = 3337645236789480204L;
	
	public static final String GET_AREA_BY_PROP_ID = "GET_AREA_BY_PROP_ID";
	public static final String GET_PROP_BY_AREA_ID = "GET_PROP_BY_AREA_ID";
	
    @Column(name="PROPERTY_ID")
	private String propertyId;
	
    @Column(name="AREA_ID")
	private String areaId;
    
    @Column(name="AREA_TYPE")
   	private String areaType;
    
    
}
