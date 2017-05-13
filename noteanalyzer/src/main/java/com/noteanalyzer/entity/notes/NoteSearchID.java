package com.noteanalyzer.entity.notes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NoteSearchID implements Serializable { 
	
	@Column(name = "USER_ID")
    protected Integer userId;
   
    @Column(name = "SEARCH_NAME")
    private String searchName;

}