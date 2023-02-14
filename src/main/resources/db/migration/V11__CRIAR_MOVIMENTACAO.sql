CREATE TABLE MOVIMENTACAO (
 	
 	id_movimentacao	BIGINT (20) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
 	id_produto	BIGINT (20) NOT NULL		,
 	data_movimentacao		DATETIME		,
	quantidade_mov			DOUBLE			,
	quantidade_anterior		DOUBLE			,
	quantidade_posterior	DOUBLE			,
	tipo_movimentacao		VARCHAR(30)     ,
 	
 	FOREIGN KEY (id_produto) REFERENCES produto (id_produto)
 );