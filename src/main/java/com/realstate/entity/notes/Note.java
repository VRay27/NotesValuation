package com.realstate.entity.notes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.realstate.entity.AbstractEntity;

import lombok.ToString;
 
@Entity
@Table(name="NOTE")
@ToString(callSuper = true)
@NamedQueries({ @NamedQuery(name = Note.FETCH_NOTE_DETAILS,query="select * from note where noteId =:noteId")})
public class Note extends AbstractEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8179556227491337368L;
	
	public static final String FETCH_NOTE_DETAILS = "FETCH_NOTE_DETAILS";
	
	@Id
    @Column(name="NOTE_ID")
    private Integer noteId;
 
    @Column(name="NOTE_NAME")
    private String noteName;
 
    @Column(name="NOTE_DESC")
    private String noteDescription;

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public String getNoteDescription() {
		return noteDescription;
	}

	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((noteId == null) ? 0 : noteId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (noteId == null) {
			if (other.noteId != null)
				return false;
		} else if (!noteId.equals(other.noteId))
			return false;
		return true;
	}
 
   
}
