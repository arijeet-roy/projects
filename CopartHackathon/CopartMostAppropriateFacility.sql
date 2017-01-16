DROP DATABASE IF EXISTS CopartFacilities;
CREATE DATABASE CopartFacilities;

-- Use the above created database
USE CopartFacilities;
DROP TABLE IF EXISTS CopartLocations;
CREATE TABLE CopartLocations (
  zipcode        varchar(6) not null,
  city			varchar(30),
  state			varchar(30),
  CONSTRAINT zipcode_pk primary key (zipcode)
);

DROP TABLE IF EXISTS CustomerData;
CREATE TABLE CustomerData (
  customer_id        varchar(6) not null,
  customer_name		varchar(30),
  customer_address			varchar(50),
  customer_state			varchar(20),
  zip			varchar(6),
  CONSTRAINT customer_pk primary key (customer_id)
);

INSERT INTO CopartLocations VALUES ('111111','Dallas','Texas');
INSERT INTO CopartLocations VALUES ('222222','San Antonio','Texas');
INSERT INTO CopartLocations VALUES ('333333','Austin','Texas');
INSERT INTO CopartLocations VALUES ('444444','New York','New York');

INSERT INTO CustomerData VALUES ('001','ABC','75+9th+Ave+New+York', 'New York', '111111');
INSERT INTO CustomerData VALUES ('002','DEF','Mccallum Blvd', 'Texas', '222222');
INSERT INTO CustomerData VALUES ('003','XYZ','Waterview Parkway', 'Texas', '333333');

