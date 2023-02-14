package com.app.pets.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_pedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cliente")
	private Cliente cliente;
	
//	private String numero;
	
	private Date data_emissao;
	
	private Date data_vencimento;
	
//	private BigDecimal quantidade;
	
	private String situacao;
	
//	private Integer	sequencia;
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true )
	private List <Itempedido> itempedidos;
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true )
	private List <Pagamentopedido> pagamentopedido;
	
	public void atualizaItemPedido() {
		this.itempedidos.forEach(item -> {
			item.setPedido(this);
		});
	}
	
	public void atualizaPagamentoPedido() {
		this.pagamentopedido.forEach(pag ->{
			pag.setPedido(this);
		});
	}


}
