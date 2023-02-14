CREATE TABLE condicaopagamento (
	id_pagamento	BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY  ,
	nome 			VARCHAR(100)				,
	modo			VARCHAR(100)				,	
	situacao		INTEGER
);	