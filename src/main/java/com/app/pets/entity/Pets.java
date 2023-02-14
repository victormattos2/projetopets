package com.app.pets.entity;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)

@Entity
@Table(name = "pets")
public class Pets {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_pets;
	
	private String nome;

	private String raca;

	private String cor;

	private String sexo;
	
	private String especie;
	
	private Integer peso;
	
	private Integer situacao;
	
	private Date data_cadastro;

	private LocalDate data_nascimento;

	
@ManyToOne
@JoinColumn(name = "id_cliente")
	private Cliente tutor;


}
