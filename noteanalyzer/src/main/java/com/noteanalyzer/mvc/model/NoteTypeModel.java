package com.noteanalyzer.mvc.model;

import java.io.Serializable;

public class NoteTypeModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1924873727695219842L;
	private String noteTypeCode;
	private String noteTypeValue;
	
	
	public NoteTypeModel() {
		super();
	}
	
	public NoteTypeModel(String noteTypeCode, String noteTypeValue) {
		super();
		this.noteTypeCode = noteTypeCode;
		this.noteTypeValue = noteTypeValue;
	}
	/**
	 * @return the noteTypeCode
	 */
	public String getNoteTypeCode() {
		return noteTypeCode;
	}
	/**
	 * @param noteTypeCode the noteTypeCode to set
	 */
	public void setNoteTypeCode(String noteTypeCode) {
		this.noteTypeCode = noteTypeCode;
	}
	/**
	 * @return the noteTypeValue
	 */
	public String getNoteTypeValue() {
		return noteTypeValue;
	}
	/**
	 * @param noteTypeValue the noteTypeValue to set
	 */
	public void setNoteTypeValue(String noteTypeValue) {
		this.noteTypeValue = noteTypeValue;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NoteType [noteTypeCode=" + noteTypeCode + ", noteTypeValue=" + noteTypeValue + "]";
	}
	
	
	

}
