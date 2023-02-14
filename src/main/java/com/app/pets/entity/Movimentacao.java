package com.app.pets.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)

@Entity
@Table(name = "movimentacao")
public class Movimentacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_movimentacao;
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	private Date data_movimentacao;

	private Double quantidade_mov;

	private Double quantidade_anterior;
	
	private Double quantidade_posterior;
	
	private String tipo_movimentacao;

	
}
