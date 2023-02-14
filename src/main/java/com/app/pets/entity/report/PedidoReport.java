package com.app.pets.entity.report;

import java.math.BigDecimal;
import java.util.List;

import com.app.pets.entity.Cliente;
import com.app.pets.entity.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PedidoReport {
	
	public Long  pedido;

	public String cliente; 

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public String data_emissao;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public String data_vencimento;

	
	public BigDecimal valor;
	
	public List<ItemPedidoReport> itens;

	

}
