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