package com.app.pets.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "itempedido")
public class Itempedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_itempedido;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	@JsonIgnore
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	private Double quantidade;
	
	private Double valor_unitario;
	
	private Double valor_total;
	
	private String situacao;
	
	
   
	
	
}
