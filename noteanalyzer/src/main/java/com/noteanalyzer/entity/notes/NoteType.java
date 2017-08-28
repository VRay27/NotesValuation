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
@Table(name="NOTE_TYPES")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = "GET_NOTE_TYPE_BY_TYPE",query="select n from NoteType n where n.noteType =:noteTypeCode"),
	 			@NamedQuery(name = "GET_ALL_NOTE_TYPE",query="select n from NoteType n ")})
public class NoteType extends AbstractEntity { 
	
	private static final long serialVersionUID = 3343766906297480204L;
	
	public static final String GET_NOTE_TYPE_BY_TYPE = "GET_NOTE_TYPE_BY_TYPE";
	
	public static final String GET_ALL_NOTE_TYPE = "GET_ALL_NOTE_TYPE";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="note_type_id")
    private Integer noteTypeId;
	

	@Column(name="TYPE")
	private String noteType;
	
    @Column(name="DESCRIPTION")
	private String description;

    

	/**
	 * @return the noteType
	 */
	public String getNoteType() {
		return noteType;
	}

	/**
	 * @param noteType the noteType to set
	 */
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the noteTypeId
	 */
	public Integer getNoteTypeId() {
		return noteTypeId;
	}

	/**
	 * @param noteTypeId the noteTypeId to set
	 */
	public void setNoteTypeId(Integer noteTypeId) {
		this.noteTypeId = noteTypeId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((noteTypeId == null) ? 0 : noteTypeId.hashCode());
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
		NoteType other = (NoteType) obj;
		if (noteTypeId == null) {
			if (other.noteTypeId != null)
				return false;
		} else if (!noteTypeId.equals(other.noteTypeId))
			return false;
		return true;
	}
	
    
	
	
}
