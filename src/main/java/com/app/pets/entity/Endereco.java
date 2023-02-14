package com.app.pets.entity;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
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

@NoArgsConstructor(force = true)
@Embeddable

@Entity
@Table(name="endereco")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_endereco;
	
	@ManyToOne(  fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id_cliente", name="id_cliente")
	@JsonIgnore
	private Cliente cliente ;
	
	private String logradouro;
	
	private String numero;
	
	private String bairro;
	
	private String cep;
	
	private String complemento;
	
	
	

	

	
	

}
