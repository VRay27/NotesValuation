CREATE TABLE NOTE_CONFIGURATION (
 CONFIG_ID INTEGER NOT NULL AUTO_INCREMENT,
 CONFIG_CODE VARCHAR(60) NOT NULL,
 CONFIG_VALUE VARCHAR(3500) NULL,
  UPDATED_DATE_TIME DATE NULL,
  UPDATED_BY  VARCHAR(45) NULL,
  CREATED_DATE_TIME DATE NULL,
  CREATED_BY  VARCHAR(45) NULL,
  PRIMARY KEY (CONFIG_ID),
  UNIQUE INDEX NOTE_CONFIG_UNIQUE (CONFIG_CODE ASC));
  
 
insert into NOTE_CONFIGURATION (CONFIG_ID, CONFIG_CODE, CONFIG_VALUE,updated_date_time,updated_by,created_date_time,created_by) values (1,'FORGOT_PASSWORD_EMAIL_SUBJECT','Reset Password For Notes',now(),'admin',now(),'admin');
insert into NOTE_CONFIGURATION (CONFIG_ID, CONFIG_CODE, CONFIG_VALUE,updated_date_time,updated_by,created_date_time,created_by) values (2,'FORGOT_PASSWORD_EMAIL_CONTENT_BODY','<p>Please use below link to reset your password for Note Analyzer.This token will expire in next 24 hours.</p>',now(),'admin',now(),'admin');
insert into NOTE_CONFIGURATION (CONFIG_ID, CONFIG_CODE, CONFIG_VALUE,updated_date_time,updated_by,created_date_time,created_by) values (3,'CREATE_USER_EMAIL_SUBJECT','THis is subject text testing',current_timestamp,'admin',current_timestamp,'admin');
insert into NOTE_CONFIGURATION (CONFIG_ID, CONFIG_CODE, CONFIG_VALUE,updated_date_time,updated_by,created_date_time,created_by) values (4,'CREATE_USER_EMAIL_CONTENT_BODY','<p><b>Content Need to be given by Ray,Vlad and Yakov</b></p>',current_timestamp,'admin',current_timestamp,'admin');
