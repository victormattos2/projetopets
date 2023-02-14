CREATE TABLE PAGAMENTOPEDIDO (
 	
 	id_pagamentopedido	BIGINT (20) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
 	id_pedido			BIGINT (20)	,
 	numero_parcela 		INTEGER		,	
 	valor_parcela 		DOUBLE		,
 	data_vencimento		DATE		,
 	
 	FOREIGN KEY (id_pedido) REFERENCES pedido (id_pedido)
 );