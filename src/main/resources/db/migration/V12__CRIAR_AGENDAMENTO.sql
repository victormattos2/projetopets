CREATE TABLE AGENDAMENTO (
 	
 	id_agendamento	BIGINT (20) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
 	id_pets		    BIGINT (20) NOT NULL    ,
 	data_hora		TIMESTAMP				,		
	tipo_servico			VARCHAR	(30)	,
	
 	FOREIGN KEY (id_pets) REFERENCES pets (id_pets)
 );