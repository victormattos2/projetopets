CREATE TABLE pets (
 	
 	id_pets		BIGINT (20) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
 	id_cliente   BIGINT (20) ,
 	nome 		VARCHAR(100),
 	raca		VARCHAR(100),
 	cor 		VARCHAR(100),
 	sexo		VARCHAR(100),	
 	tutor		VARCHAR(100),
 	especie		VARCHAR(100),
 	peso		INTEGER		,
 	situacao	INTEGER		,
 	data_cadastro	DATE	,
	data_nascimento	DATE    ,	
	FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
 );