CREATE TABLE note_type (
 N_TYPE_ID INTEGER NOT NULL AUTO_INCREMENT,
 NOTE_TYPE VARCHAR(15) NOT NULL,
 DESCRIPTION VARCHAR(45) NULL,
  UPDATED_DATE_TIME DATE NULL,
  UPDATED_BY  VARCHAR(45) NULL,
  CREATED_DATE_TIME DATE NULL,
  CREATED_BY  VARCHAR(45) NULL,
  PRIMARY KEY (N_TYPE_ID),
  UNIQUE INDEX NOTE_TYPE_UNIQUE (NOTE_TYPE ASC));
  
  insert into note_type (n_type_id, note_type, description,updated_date_time,updated_by,created_date_time,created_by) values (1,'NOTE_TYPE_1','Note Type 1 from DB',now(),'admin',now(),'admin');
insert into note_type (n_type_id, note_type, description,updated_date_time,updated_by,created_date_time,created_by) values (2,'NOTE_TYPE_2','Note Type 2 from DB',now(),'admin',now(),'admin');
insert into note_type (n_type_id, note_type, description,updated_date_time,updated_by,created_date_time,created_by) values (3,'NOTE_TYPE_3','Note Type 3 from DB',current_timestamp,'admin',current_timestamp,'admin');
