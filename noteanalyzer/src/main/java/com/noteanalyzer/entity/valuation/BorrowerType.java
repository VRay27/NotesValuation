package com.noteanalyzer.entity.valuation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

@Entity
@Table(name="BORROWER_TYPE")
public class BorrowerType  extends AbstractEntity {

	@Id
	@Column(name="BORROWER_TYPE")
	private String borrowerType;
	
	@Column(name="Description")
	private String description;
	
	
}
