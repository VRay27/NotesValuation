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
@Table(name="NOTE_TYPE")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = "GET_NOTE_TYPE_BY_TYPE",query="select n from NoteType n where n.noteType =:noteType")})
public class NoteType  extends AbstractEntity {
	
	private static final long serialVersionUID = 6885930415090373746L;
	
	public static final String GET_NOTE_TYPE_BY_TYPE = "GET_NOTE_TYPE_BY_TYPE";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="N_TYPE_ID")
    private Integer nTypeId;

	@Column(name="NOTE_TYPE")
	private String noteType;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	public NoteType() {
	}
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	public String getNoteType() {
		return noteType;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	

}
