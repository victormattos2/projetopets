CREATE TABLE usuario (
	
	id_usuario BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	login				VARCHAR(100),
	senha				VARCHAR(100),
	tipo				VARCHAR(100),
	situacao			VARCHAR(100)
);