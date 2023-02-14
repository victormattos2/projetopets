 CREATE TABLE pedido (

	id_pedido		BIGINT (20) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	id_pagamento	BIGINT (20) ,
	cliente			BIGINT(100),
	situacao		VARCHAR(100),
	data_emissao	DATETIME,
	data_vencimento	DATETIME, 
	pedido_desconto	DECIMAL(15,2),
	valor_total		DOUBLE,
	FOREIGN KEY (cliente) REFERENCES cliente (id_cliente)
	
);

 CREATE TABLE itempedido (

	id_itempedido	BIGINT (20) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	id_pedido		BIGINT (20) NOT NULL,
	id_produto		BIGINT (20) NOT NULL,
	quantidade 		DECIMAL(15,2),
	unidade			VARCHAR(10),
	valor_unitario	DOUBLE,	
	valor_total		DOUBLE, 
	situacao		VARCHAR(100),
	sequencia		INTEGER,
	FOREIGN KEY (id_pedido) REFERENCES pedido (id_pedido)
);