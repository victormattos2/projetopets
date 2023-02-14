package com.app.pets.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "programacao")
public class Programacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_programacao;
	

	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate data_lancamento;
	

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm")
	private LocalDateTime data_chegada;
	 
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm")
	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private LocalDateTime data_descarga;
	
	
	@ManyToOne
	@JoinColumn(name = "id_origem")
	private Cidade cidade_origem;
	
	
	@ManyToOne
	@JoinColumn(name = "id_destino")
	private Cidade cidade_destino;
	
	private String observacao;
	
	private String usuario;
	

}
