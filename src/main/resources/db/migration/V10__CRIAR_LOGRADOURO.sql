CREATE TABLE ENDERECO (
 	
 	id_endereco	BIGINT (20) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
 	id_cliente	BIGINT (20) NOT NULL,
 	logradouro 	VARCHAR(100),
 	numero 	VARCHAR(30),
 	bairro	VARCHAR(60),
 	cep		VARCHAR(30),
 	complemento	VARCHAR(60),
 	FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
 );