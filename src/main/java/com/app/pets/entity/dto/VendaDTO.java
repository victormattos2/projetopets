package com.app.pets.entity.dto;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;

public interface VendaDTO {
	
	public Long getPedido();
	
	public String getCliente();
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public String getData_emissao();
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public String getData_vencimento();
	
	public BigDecimal  getValor_total();
	
	public BigDecimal  getQuantidade();



}


