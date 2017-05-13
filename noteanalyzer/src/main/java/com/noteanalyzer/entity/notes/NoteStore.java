package com.noteanalyzer.entity.notes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;

@Entity
@Table(name="NOTE_STORE")
public class NoteStore extends AbstractEntity {
	
	private static final long serialVersionUID = 393756493746689997L;

	
	@Id
	@Column(name = "STORE_NAME")
	private String StoreName;
	
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "STORE_API")
	private String storeAPI;
	

}
