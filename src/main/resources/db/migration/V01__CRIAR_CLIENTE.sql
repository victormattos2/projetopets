CREATE TABLE cliente (

	id_cliente	BIGINT (20) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	nome		VARCHAR(60) NOT NULL,
	situacao    INTEGER		,
	email   	VARCHAR(60)	,
	cpf			VARCHAR(30) ,
	cnpj		VARCHAR(30)	,
	rg			VARCHAR(30)	,
	telefone 	VARCHAR(30)	,
	data_cadastro	DATE	,
	data_nascimento	DATE	,
	numero		VARCHAR(30)	,
	complemento	VARCHAR(30) ,
	bairro		VARCHAR(30) ,
	cep			VARCHAR(30) ,
	cidade		VARCHAR(30) ,
	estado		VARCHAR(30) ,
	cli_fisica_juridica VARCHAR(30),
	fantasia   VARCHAR(60)	,
	cli_inscricao VARCHAR(30)	
);


CREATE TABLE pais (
	id_pais BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(60), 
	sigla VARCHAR(10)	
);

CREATE TABLE estado (
	id_estado BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	id_pais BIGINT(20) NOT NULL,
	nome VARCHAR(100),
	uf VARCHAR(2),
	codigoibge VARCHAR(10),	
	FOREIGN KEY (id_pais) REFERENCES pais (id_pais)
);


CREATE TABLE cidade (
	id_cidade BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_estado BIGINT(20) NOT NULL,
	id_pais   BIGINT(20) NOT NULL,
	nome  VARCHAR(100),
	codigoibge VARCHAR(10),
   FOREIGN KEY (id_estado) REFERENCES estado (id_estado),	
	FOREIGN KEY (id_pais) REFERENCES pais (id_pais)
);
	