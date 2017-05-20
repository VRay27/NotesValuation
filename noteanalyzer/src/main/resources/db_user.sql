CREATE TABLE User (
    USER_ID int NOT NULL AUTO_INCREMENT,
    user_name varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    display_name varchar(100),
    first_name varchar(50),
    last_name varchar(50),
    email_id varchar(100) NOT NULL,
    CONTACT_NUMBER varchar(20),
    ADDRESS varchar(255),
    Street varchar(50) ,
    city varchar(50),
    zip_code varchar(20),
    state varchar(20),
    country varchar(20),
    reset_token varchar(255),
    reset_token_creation_time timestamp,
    created_by varchar(50),
    updated_by varchar(50),
    CREATED_DATE_TIME timestamp,
    UPDATED_DATE_TIME timestamp,
    PRIMARY KEY (USER_ID)
);