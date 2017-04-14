package com.noteanalyzer.entity.notes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

import lombok.ToString;

@Entity
@Table(name="NOTE_TYPE")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = "FETCH_NOTE_TYPE_DETAILS",query="select type from NoteType n where n.type =:type")})
public class NoteType  extends AbstractEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6885930415090373746L;

	@Id
	@Column(name="TYPE")
	private String type;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
