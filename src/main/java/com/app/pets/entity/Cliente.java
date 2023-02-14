package com.app.pets.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)

@Entity
@Table(name = "cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cliente;
	private String nome;
	private Integer situacao;
	private String email;
	private String cpf;
	private String cnpj;
	private String rg;
	private String telefone;
	private LocalDate data_cadastro;
	private Date data_nascimento;
	private String cli_fisica_juridica;
	private String fantasia;
	private String cli_inscricao;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true )
	private List <Endereco> enderecos;
	
	//@OneToMany(  fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	//@JoinColumn(referencedColumnName = "id_cliente", name = "id_cliente")
	//private List<Endereco> enderecos;
	
   

}
