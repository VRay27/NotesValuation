package com.noteanalyzer.entity.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.noteanalyzer.entity.AbstractEntity;
import com.noteanalyzer.entity.notes.NoteStore;


@Entity
@Table(name="SUBSCRIPTION")
public class Subscription extends AbstractEntity {

	private static final long serialVersionUID = 2383502272360951114L;
	
	@Id
	@Column(name = "SUBSCRIPTION_ID")
	private Integer subscriptionId;
	
	@Column(name = "PRIVILEDGE")
	private String priviledge;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "STORE_NAME")
	private NoteStore noteStore;
}
