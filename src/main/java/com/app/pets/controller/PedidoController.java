package com.app.pets.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.pets.entity.Movimentacao;
import com.app.pets.entity.Pedido;
import com.app.pets.entity.Produto;
import com.app.pets.entity.dto.VendaDTO;
import com.app.pets.entity.dto.ItemVendaDTO;
import com.app.pets.entity.report.GeradorRelatorio;
import com.app.pets.repository.MovimentacaoRepository;
import com.app.pets.repository.PedidoRepository;
import com.app.pets.repository.ProdutoRepository;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private GeradorRelatorio geradorRelatorio;



	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido adicionar(@RequestBody Pedido pedido) {

		System.out.printf("pedido:" + pedido.toString());
		pedido.atualizaItemPedido();
		pedido.atualizaPagamentoPedido();
		
		pedido.getItempedidos().forEach(item ->{
			Movimentacao movimentacao = new Movimentacao();
			Produto produto = produtoRepository.getOne(item.getProduto().getId_produto());
			movimentacao.setProduto(produto);
			movimentacao.setQuantidade_mov(item.getQuantidade());
			movimentacao.setQuantidade_anterior(produto.getQuantidade() != null ? produto.getQuantidade() : 0);
			Double quantidadePosterior = movimentacao.getQuantidade_anterior() - item.getQuantidade();
			movimentacao.setQuantidade_posterior(quantidadePosterior);
			movimentacao.setTipo_movimentacao("SAIDA");
			movimentacao.setData_movimentacao(new Date());
			movimentacaoRepository.save(movimentacao);
			produto.setQuantidade(quantidadePosterior);
			
		});

		return pedidoRepository.save(pedido);

	}

	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido atualizar(@RequestBody Pedido pedido) {

		// System.out.printf("pedido:"+pedido);

		return pedidoRepository.save(pedido);

	}

	@GetMapping
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}
	
	@GetMapping("/lista-order-desc")
	public List<VendaDTO> listarOrderDesc() {
		return pedidoRepository.listaPedidoDesc();
	}
	
	@GetMapping("/item-pedido")
	public List<ItemVendaDTO> listaItemPedido() {
		return pedidoRepository.listaItem(1l);
	}

	@PostMapping("/alterar-situacao")
	public void UpdatePedidoSituacao(@RequestParam("id") String pedido) {
		System.out.println("pedido" + pedido);
		pedidoRepository.updateMalaItem(pedido);

	}

	@GetMapping("/{id_pedido}")
	public Optional<Pedido> buscarPeloCodigo(@PathVariable Long id_pedido) {
		Optional<Pedido> pedido = pedidoRepository.findById(id_pedido);
		return pedido;
	}
	
	@GetMapping("/registro-pdf")
	public ResponseEntity<byte[]> registroPDF() throws JRException {

		

		byte[] relatorio = geradorRelatorio.ImprimiRelatorio(); 
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);

	}

}
