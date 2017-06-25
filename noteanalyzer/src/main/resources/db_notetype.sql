CREATE TABLE NOTE_TYPE (
 N_TYPE_ID INTEGER NOT NULL AUTO_INCREMENT,
 NOTE_TYPE VARCHAR(15) NOT NULL,
 DESCRIPTION VARCHAR(45) NULL,
  UPDATED_DATE_TIME DATE NULL,
  UPDATED_BY  VARCHAR(45) NULL,
  CREATED_DATE_TIME DATE NULL,
  CREATED_BY  VARCHAR(45) NULL,
  PRIMARY KEY (N_TYPE_ID),
  UNIQUE INDEX NOTE_TYPE_UNIQUE (NOTE_TYPE ASC));
  
insert into NOTE_TYPE (n_type_id, note_type, description,updated_date_time,updated_by,created_date_time,created_by) values (1,'PERFORMING',' Performing Note',now(),'admin',now(),'admin');
insert into NOTE_TYPE (n_type_id, note_type, description,updated_date_time,updated_by,created_date_time,created_by) values (2,'SUB-PERFORMING','Sub Performing Note',now(),'admin',now(),'admin');
insert into NOTE_TYPE (n_type_id, note_type, description,updated_date_time,updated_by,created_date_time,created_by) values (3,'NON-PERFORMING','Non Performaing Note',current_timestamp,'admin',current_timestamp,'admin');
