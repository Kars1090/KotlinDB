CREATE DATABASE KotlinDB;
USE KotlinDB;

CREATE TABLE Address (
	street			VARCHAR(255)		NOT NULL,
    number			INT					NOT NULL,
    addition		CHAR(1)				NULL,
    postalCode		CHAR(6)				NOT NULL,
    city			VARCHAR(255)		NOT NULL,
    CONSTRAINT PK_Address PRIMARY KEY Address(postalCode, number)
);
    

CREATE TABLE Person (
   id				MEDIUMINT			NOT NULL AUTO_INCREMENT,
   firstName		VARCHAR(255)		NOT NULL,
   lastName			VARCHAR(255)		NOT NULL,
   email			VARCHAR(255)		NOT NULL,
   phone			VARCHAR(12)			NULL,
   postalCode		CHAR(6)				NULL,
   number			int					NULL,
   addition			CHAR(1)				NULL,
   CONSTRAINT PK_Person PRIMARY KEY Person(id),
   CONSTRAINT FK_Person_Adress FOREIGN KEY Person(postalCode, number, addition) REFERENCES Address(postalCode, number, addition)
);
