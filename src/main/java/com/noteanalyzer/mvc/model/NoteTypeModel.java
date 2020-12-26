package com.noteanalyzer.mvc.model;

import java.io.Serializable;

public class NoteTypeModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3800824063535708236L;
	private String noteType;
	private String description;
	
	public NoteTypeModel() {
		super();
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noteType == null) ? 0 : noteType.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoteTypeModel other = (NoteTypeModel) obj;
		if (noteType == null) {
			if (other.noteType != null)
				return false;
		} else if (!noteType.equals(other.noteType))
			return false;
		return true;
	}
	

}
