package com.noteanalyzer.entity.notes;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="NOTE_SERACH_CRITERIA")
public class NoteSearchCriteria {

	
	@EmbeddedId
	private NoteSearchID noteSearcId;
    
    @Column(name = "MAX_PRICE")
    private Integer maxPrice;
	
    
    @Column(name = "MIN_PRICE")
    private Integer minPrice;

    @Column(name = "PERFROMING")
    private String performing;
	
    
    @Column(name = "MIN_ROI")
    private Integer minROI;
	
    
    @Column(name = "MAX_RISK_FACTOR")
    private Integer maxRiskFactor;
    
    @Column(name = "DATE_ENTERD")
	private Date dateEntered;
    
    @Column(name = "PROPERTY_LOCATION")
    private String propertyLocations;

    @Column(name = "STORE_NAME")
    private String  storeName;

    @Column(name = "USER_STORE_SUBSCRIPTION")
    private Integer  userStoresSubsriptionId;
	
    @Column(name = "PROPERTY_TYPE")
    private PropertyType  propertyTypes;
    
    @Column(name = "RUNTIME")
    private String  runTime;
}
