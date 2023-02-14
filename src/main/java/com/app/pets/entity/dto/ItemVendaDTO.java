package com.app.pets.entity.dto;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;

public interface ItemVendaDTO {
	
		
	public Long getPedido();
	
	public Long getCodigo();
	
	public String getProduto();
	
	public Long  getQuantidade();
	
	public BigDecimal  getValor();
	
	public BigDecimal  getValor_total();
	
	



}


