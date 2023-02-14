package com.app.pets.entity.report;

import java.math.BigDecimal;

import com.app.pets.entity.Cliente;
import com.app.pets.entity.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ItemPedidoReport {
	
	public Long pedido;
	
	public Long codigo;

	public String produto; 
	
	public Long quantidade;
	
	public BigDecimal valor;
	
	public BigDecimal valor_total;
	
	

	


	

}
