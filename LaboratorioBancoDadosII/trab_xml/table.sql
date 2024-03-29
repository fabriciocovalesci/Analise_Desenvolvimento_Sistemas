
create database IF NOT EXISTS db_labdb;


USE db_labdb;

CREATE TABLE cliente (
	id INT(11) NOT NULL,
	nome VARCHAR(100) CHARACTER SET latin1 DEFAULT NULL,
	cpf VARCHAR(10) DEFAULT NULL,
	email VARCHAR(100) CHARACTER SET latin1 DEFAULT NULL,
	sexo VARCHAR(10) CHARACTER SET latin1 DEFAULT NULL,
	cidade VARCHAR(50) CHARACTER SET latin1 DEFAULT NULL,
	PRIMARY KEY (id)
)




CREATE TABLE funcionario (
	id INT(11) NOT NULL,
	nome VARCHAR(100) CHARACTER SET latin1 DEFAULT NULL,
	cpf VARCHAR(10) DEFAULT NULL,
	email VARCHAR(100) CHARACTER SET latin1 DEFAULT NULL,
	sexo VARCHAR(10) CHARACTER SET latin1 DEFAULT NULL,
	cidade VARCHAR(50) CHARACTER SET latin1 DEFAULT NULL,
	PRIMARY KEY (id)
)



LOAD XML INFILE "C:/Users/06386477979/clienteWilliam.xml"
INTO TABLE cliente
CHARACTER SET UTF8
ROWS IDENTIFIED BY '<cliente>'

USE db_labdb;

LOAD XML INFILE "C:/Users/06386477979/clienteWilliam.xml"
INTO TABLE funcionario
CHARACTER SET UTF8
ROWS IDENTIFIED BY '<funcionario>'