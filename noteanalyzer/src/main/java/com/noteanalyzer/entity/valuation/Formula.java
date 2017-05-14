package com.noteanalyzer.entity.valuation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;
import com.noteanalyzer.entity.notes.NoteType;
import com.noteanalyzer.entity.notes.PropertyType;

@Entity
@Table(name="FORMULA")
public class Formula extends AbstractEntity {

	@Id
	@Column(name="FORMULA_ID")
	private Integer formulaId;
	
	@Column(name="FORMULA_NAME")
	private String formulaName;
	
	@Column(name="USER_ID")
	private Integer userId;
	
	
	@Column(name="AREA_ID")
	private Integer areaId;
	
	@Column(name="NOTE_TYPE")
	private NoteType noteType;
	
	@Column(name="PROPERTY_TYPE")
	private PropertyType propertyType;

	@Column(name="BORROWER_TYPE")
	private BorrowerType borrowerType;
	
	@Column(name="VALUE_ADJUSTMENT")
	private String  valueAdjustment;
	
	@Column(name="RISK_ADJUSTMENT")
	private String riskAdjustment;
	
	
	
	
	
}
