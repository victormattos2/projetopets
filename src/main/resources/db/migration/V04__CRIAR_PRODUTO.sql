CREATE TABLE produto (
	
	id_produto	BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_marca	BIGINT(20) NOT NULL	,
	nome		VARCHAR(100)		,
	marca		VARCHAR(100)		,
	unidade		VARCHAR(100)		,
	grupo		VARCHAR(100)		,
	quantidade  DOUBLE				,
	valor		DOUBLE				,
	flagservico INTEGER 			,
	FOREIGN KEY (id_marca) REFERENCES marca (id_marca)
);