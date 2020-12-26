package com.noteanalyzer.entity.valuation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;


public class BorrowerType  extends AbstractEntity {

	private static final long serialVersionUID = 2057071124772914109L;

	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="BRW_TYPE_ID")*/
    private Integer brrwTypeId;

	//@Column(name="BORROWER_TYPE")
	private String borrowerType;
	
	//@Column(name="Description")
	private String description;
	
	
	public void setBorrowerType(String borrowerType) {
		this.borrowerType = borrowerType;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBorrowerType() {
		return borrowerType;
	}
	public String getDescription() {
		return description;
	}
	
}
