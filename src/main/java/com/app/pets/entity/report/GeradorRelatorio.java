package com.app.pets.entity.report;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pets.entity.Cliente;
import com.app.pets.entity.Pets;
import com.app.pets.entity.Produto;
import com.app.pets.entity.dto.ItemVendaDTO;
import com.app.pets.entity.dto.VendaDTO;
import com.app.pets.repository.PedidoRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author victo
 *
 */
/**
 * @author victo
 *
 */
@Service
public class GeradorRelatorio {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
		public static ArrayList<PedidoReport> getListaPedido() {
			ArrayList<PedidoReport> lista = new ArrayList<PedidoReport>();
			ArrayList<ItemPedidoReport> itens = new ArrayList<ItemPedidoReport>();
		 
		 PedidoReport pedido = new PedidoReport();
		
		 
		 
		 pedido.setPedido(1l);
		 pedido.setCliente("VICTOR RAMOS");
		 pedido.setData_emissao("2022-11-29");
		 pedido.setData_vencimento("2022-12-30");
		 pedido.setValor(BigDecimal.valueOf(75.00));
		 
		 
		 
		 ItemPedidoReport item = new ItemPedidoReport();
		 
		 item.setPedido(1l);
		 item.setCodigo(40l);
		 item.setProduto("PRODUTO A");
		 item.setQuantidade(2l);
		 item.setValor(BigDecimal.valueOf(20.00));
		 item.setValor_total(BigDecimal.valueOf(40.00));
		 
		 itens.add(item);
		 
		 item = new ItemPedidoReport();
		 item.setPedido(1l);
		 item.setCodigo(20l);
		 item.setProduto("PRODUTO B");
		 item.setQuantidade(1l);
		 item.setValor(BigDecimal.valueOf(35.00));
		 item.setValor_total(BigDecimal.valueOf(35.00));
		 
		 itens.add(item);
		 
		 pedido.setItens(itens);
		 lista.add(pedido);
		 // pedido 2 
		 
		 pedido = new PedidoReport();
		 itens = new ArrayList<>();
		 
		 
		 pedido.setPedido(2l);
		 pedido.setCliente(" JOAO PAULO");
		 pedido.setData_emissao("2022-11-29");
		 pedido.setData_vencimento("2022-12-30");
		 pedido.setValor(BigDecimal.valueOf(75.00));
		 
		 
		 
		 item = new ItemPedidoReport();
		 
		 item.setPedido(2l);
		 item.setCodigo(40l);
		 item.setProduto("PRODUTO C");
		 item.setQuantidade(2l);
		 item.setValor(BigDecimal.valueOf(15.00));
		 item.setValor_total(BigDecimal.valueOf(30.00));
		 
		 itens.add(item);
		 
		 item = new ItemPedidoReport();
		 item.setPedido(2l);
		 item.setCodigo(20l);
		 item.setProduto("PRODUTO D");
		 item.setQuantidade(1l);
		 item.setValor(BigDecimal.valueOf(45.00));
		 item.setValor_total(BigDecimal.valueOf(45.00));
		 
		 itens.add(item);
		 
		 pedido.setItens(itens);
		 
		
		 
		 lista.add(pedido);
		 System.out.println("lista:"+lista.toString());
		return lista;
		
		
	}
		
	public byte[]  ImprimiRelatorioMock() throws JRException {
			
			List<PedidoReport> lista =  getListaPedido();
			
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("DT_INICIO", Date.valueOf(LocalDate.now()));
			parametros.put("DT_FIM", Date.valueOf(LocalDate.now().plusMonths(3)));
			parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
			
			InputStream inputStream = this.getClass().getResourceAsStream(
					"/report/relatorio_pedido_novo.jasper");
			  
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
					new JRBeanCollectionDataSource(lista));
			
			return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	public byte[]  ImprimiRelatorio() throws JRException {
		
		List<PedidoReport> lista =  converterPedidoDTO();
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(LocalDate.now()));
		parametros.put("DT_FIM", Date.valueOf(LocalDate.now().plusMonths(3)));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/report/relatorio_pedido_novo.jasper");
		  
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(lista));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
}
	
	public List<PedidoReport> converterPedidoDTO() {
		 List<VendaDTO> listaVenda = pedidoRepository.listaPedidoDesc();
		 List<PedidoReport> listaPedido = new ArrayList<PedidoReport>();
		 
		 for(VendaDTO dto : listaVenda) {
			 PedidoReport report = new PedidoReport();
			 	
			 report.setPedido(dto.getPedido());
			 report.setCliente(dto.getCliente());
			 report.setData_emissao(dto.getData_emissao());
			 report.setData_vencimento(dto.getData_vencimento());
			 report.setValor(dto.getValor_total());
			 report.setItens(converterItemDTO(dto.getPedido()));
			 
			 listaPedido.add(report);
			 
			 
		 }
		 return listaPedido;
		
	}
	
	public List< ItemPedidoReport> converterItemDTO(Long pedido) {
		
		 List<ItemVendaDTO> listaItemVenda = pedidoRepository.listaItem(pedido);
		 List< ItemPedidoReport> listaItemPedido = new ArrayList< ItemPedidoReport>();
		 
		 for(ItemVendaDTO dto : listaItemVenda) {
			 ItemPedidoReport report = new ItemPedidoReport();
			 	
			 report.setPedido(dto.getPedido() );
			 report.setCodigo(dto.getCodigo());
			 report.setProduto(dto.getProduto());
			 report.setQuantidade(dto.getQuantidade());
			 report.setValor(dto.getValor());
			 report.setValor_total(dto.getValor_total());
			 
			 listaItemPedido.add(report);
			
			 
			 
		 }
		 
		 return listaItemPedido;
		
	}
	
	
	public byte[]  ImprimiRelatorioProduto(List<Produto> lista ) throws JRException {
		
		
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(LocalDate.now()));
		parametros.put("DT_FIM", Date.valueOf(LocalDate.now().plusMonths(3)));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/report/relatorio_produtos.jasper");
		  
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(lista));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
}
	
public byte[]  ImprimiRelatorioClientes(List<Cliente> lista ) throws JRException {
		
		
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(LocalDate.now()));
		parametros.put("DT_FIM", Date.valueOf(LocalDate.now().plusMonths(3)));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/report/relatorio_clientes.jasper");
		  
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(lista));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
}

public byte[]  ImprimiRelatorioPets(List<Pets> lista ) throws JRException {
	
	
	
	Map<String, Object> parametros = new HashMap<>();
	parametros.put("DT_INICIO", Date.valueOf(LocalDate.now()));
	parametros.put("DT_FIM", Date.valueOf(LocalDate.now().plusMonths(3)));
	parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
	
	InputStream inputStream = this.getClass().getResourceAsStream(
			"/report/relatorio_pets.jasper");
	  
	
	JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
			new JRBeanCollectionDataSource(lista));
	
	return JasperExportManager.exportReportToPdf(jasperPrint);
}
	
	
	public static void main(String[] args) {
		getListaPedido();
	}
	

}
