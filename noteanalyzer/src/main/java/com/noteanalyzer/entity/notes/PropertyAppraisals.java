package com.noteanalyzer.entity.notes;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name="PROPERTY_APPRAISALS")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = "GET_PROPERTY_APPRAISALS_AREA",query="select p from PropertyAppraisals p  where p.propertyId =:propertyId"),
				@NamedQuery(name = "GET_PROP_APPRAISALS_BY_AREA_ID",query="select p from PropertyAppraisals p  where p.areaId =:areaId")})
public class PropertyAppraisals extends AbstractEntity { 
	
	private static final long serialVersionUID = 3337645236789480204L;
	
	public static final String GET_PROPERTY_APPRAISALS_AREA = "GET_PROPERTY_APPRAISALS_AREA";
	public static final String GET_PROP_APPRAISALS_BY_AREA_ID = "GET_PROP_APPRAISALS_BY_AREA_ID";
	
	
    @Column(name="PROPERTY_ID")
	private String propertyId;
	
    @Column(name="AREA_ID")
	private String areaId;
    
    @Column(name="APPRAISAL_SOURCE")
   	private String appraisalsSource;
    
    @Column(name="APPRAISAL_VALUE")
   	private String appraisalsValue;
    
    @Column(name="APPRAISAL_DATE")
   	private Date appraisalsDate;
    
    
}
