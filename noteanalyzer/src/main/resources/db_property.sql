
  CREATE TABLE notes.PROPERTY (
  PID INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  PROP_ID VARCHAR(15) NOT NULL UNIQUE,
  AREA_ID VARCHAR(15) NOT NULL,
  PROP_NAME VARCHAR(45) NULL,
  PROPERTY_TYPE VARCHAR(45) NOT NULL,
  PROP_DESC VARCHAR(45) NULL,
  STREET VARCHAR(45) NOT NULL,
  CITY VARCHAR(45) NULL,
  STATE VARCHAR(45) NULL,
  ZIP INT NULL,
  AGE INT NULL,
  SIZE_SF INT NULL,
  SUBDIVIDABLE TINYINT NULL,
  OTH_HIGH_PRIO_DEBT INT NULL,
  OTH_LOW_PRIO_DEBT INT NULL,
  OTH_MONTH_EXPENSE INT NULL,
  UPDATED_DATE_TIME DATE NULL,
  UPDATED_BY  VARCHAR(45) NULL,
  CREATED_DATE_TIME DATE NULL,
  CREATED_BY  VARCHAR(45) NULL,
  INDEX fk_proptype_idx (PROPERTY_TYPE ASC),
  CONSTRAINT fk_propType FOREIGN KEY (PROPERTY_TYPE)
  REFERENCES notes.property_type (PROPERTY_TYPE)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  INDEX fk_areaid_idx (AREA_ID ASC),
  CONSTRAINT fk_areaId FOREIGN KEY (AREA_ID)
  REFERENCES notes.area(AREA_ID)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);
