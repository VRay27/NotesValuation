CREATE TABLE PROPERTY_TYPE (
  P_TYPE_ID INTEGER NOT NULL AUTO_INCREMENT,
  PROPERTY_TYPE VARCHAR(15) NOT NULL,
  DESCRIPTION VARCHAR(45) NULL,
  UPDATED_DATE_TIME DATE NULL,
  UPDATED_BY  VARCHAR(45) NULL,
  CREATED_DATE_TIME DATE NULL,
  CREATED_BY  VARCHAR(45) NULL,
  PRIMARY KEY (P_TYPE_ID),
  UNIQUE INDEX PROPERTY_TYPE_UNIQUE (PROPERTY_TYPE ASC));
  
insert into PROPERTY_TYPE (p_type_id, property_type, description,updated_date_time,updated_by,created_date_time,created_by) values (1,'TOWNHOUSE','Townhouse Property Type',now(),'admin',now(),'admin');
insert into PROPERTY_TYPE (p_type_id, property_type, description,updated_date_time,updated_by,created_date_time,created_by) values (2,'VILLA','Villa Property Type',now(),'admin',now(),'admin');
insert into PROPERTY_TYPE (p_type_id, property_type, description,updated_date_time,updated_by,created_date_time,created_by) values (3,'APARTMENT','Apartment Property Type',current_timestamp,'admin',current_timestamp,'admin');
insert into PROPERTY_TYPE (p_type_id, property_type, description,updated_date_time,updated_by,created_date_time,created_by) values (4,'CONDOMINIUMS','Condo Property Type',current_timestamp,'admin',current_timestamp,'admin');
insert into PROPERTY_TYPE (p_type_id, property_type, description,updated_date_time,updated_by,created_date_time,created_by) values (5,'TIMESHARE HOME','Timeshare Home Property Type',current_timestamp,'admin',current_timestamp,'admin');
  