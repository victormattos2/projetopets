package com.app.pets.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "pagamentopedido")
public class Pagamentopedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_pagamentopedido;
	
	private Integer numero_parcela;
	
	private Double valor_parcela;
	
	private Date data_vencimento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pedido")
	@JsonIgnore
	private Pedido pedido;
	
	
	
	
	


}
