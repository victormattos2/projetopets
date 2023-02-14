package com.app.pets.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.pets.entity.Movimentacao;
import com.app.pets.entity.Produto;
import com.app.pets.repository.MovimentacaoRepository;
import com.app.pets.repository.ProdutoRepository;

@RestController
@RequestMapping("/movimentacao")
@CrossOrigin(origins = "*")
public class MovimentacaoController {
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	

	@PostMapping
	public ResponseEntity<Movimentacao> criar(@Valid @RequestBody Movimentacao movimentacao, HttpServletResponse response){
		

		
		Produto produto = produtoRepository.findById(movimentacao.getProduto().getId_produto()).get();
		
		Double quantidadeAnterior = produto.getQuantidade() != null ? produto.getQuantidade() : 0 ;
		Double quantidadePosterior = quantidadeAnterior;
		if(movimentacao.getTipo_movimentacao().equals("ENTRADA")) {
			quantidadePosterior += movimentacao.getQuantidade_mov();
		}else if(movimentacao.getTipo_movimentacao().equals("SAIDA")) {
			quantidadePosterior -= movimentacao.getQuantidade_mov();
		}
		produto.setQuantidade(quantidadePosterior);
		movimentacao.setQuantidade_anterior(quantidadeAnterior);
		movimentacao.setQuantidade_posterior(quantidadePosterior);
		
		produtoRepository.save(produto);
		Movimentacao movimentacaoSalvo = movimentacaoRepository.save(movimentacao);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idmovimentacao}")
				.buildAndExpand(movimentacaoSalvo.getId_movimentacao()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(movimentacaoSalvo);
	}
	
	@GetMapping("/{idmovimentacao}")
	public ResponseEntity <Movimentacao> buscarPeloCodigo(@PathVariable Long idmovimentacao){
		Movimentacao movimentacao = movimentacaoRepository.findById(idmovimentacao).orElse(null);
		return movimentacao != null ? ResponseEntity.ok(movimentacao): ResponseEntity.notFound().build();
		
	}
	
	@GetMapping
	public List<Movimentacao> buscarMovimentacao(){
		List<Movimentacao> movimentacao = movimentacaoRepository.findAll();
		return movimentacao;	
	}
	
	@PutMapping("/{idmovimentacao}")
	public Movimentacao atualizar(@PathVariable Long idmovimentacao, @RequestBody Movimentacao movimentacao) {

		Movimentacao movimentacaoSalvo = this.movimentacaoRepository.findById(idmovimentacao).
				  orElse(null);

		  BeanUtils.copyProperties(movimentacao, movimentacaoSalvo, "idmovimentacao");

		  return this.movimentacaoRepository.save(movimentacaoSalvo);
		}
	
	
	@DeleteMapping("/{idmovimentacao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long idmovimentacao) {
	this.movimentacaoRepository.deleteById(idmovimentacao);
	}
	
}
