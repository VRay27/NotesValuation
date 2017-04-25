package com.noteanalyzer.mvc.model;

import java.io.Serializable;

public class NoteModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7343742294405817564L;

	private String noteName;
	private String noteValue;
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public String getNoteValue() {
		return noteValue;
	}
	public void setNoteValue(String noteValue) {
		this.noteValue = noteValue;
	}
	@Override
	public String toString() {
		return "NoteModel [noteName=" + noteName + ", noteValue=" + noteValue + "]";
	}
	
	
}
