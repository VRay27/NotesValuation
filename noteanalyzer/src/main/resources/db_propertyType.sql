CREATE TABLE property_type (
  P_TYPE_ID INTEGER NOT NULL AUTO_INCREMENT,
  PROPERTY_TYPE VARCHAR(15) NOT NULL,
  DESCRIPTION VARCHAR(45) NULL,
  UPDATED_DATE_TIME DATE NULL,
  UPDATED_BY  VARCHAR(45) NULL,
  CREATED_DATE_TIME DATE NULL,
  CREATED_BY  VARCHAR(45) NULL,
  PRIMARY KEY (P_TYPE_ID),
  UNIQUE INDEX PROPERTY_TYPE_UNIQUE (PROPERTY_TYPE ASC));
  
insert into property_type (p_type_id, property_type, description,updated_date_time,updated_by,created_date_time,created_by) values (1,'property_type_1','Property Type 1 from DB',now(),'admin',now(),'admin');
insert into property_type (p_type_id, property_type, description,updated_date_time,updated_by,created_date_time,created_by) values (2,'property_type_2','Property Type 2 from DB',now(),'admin',now(),'admin');
insert into property_type (p_type_id, property_type, description,updated_date_time,updated_by,created_date_time,created_by) values (3,'property_type_3','Property Type 3 from DB',current_timestamp,'admin',current_timestamp,'admin');
  