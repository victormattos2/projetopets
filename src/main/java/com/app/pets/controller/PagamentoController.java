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


import com.app.pets.entity.Pagamento;
import com.app.pets.repository.PagamentoRepository;

@RestController
@RequestMapping("/pagamento")
@CrossOrigin(origins = "*")
public class PagamentoController {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	

	@PostMapping
	public ResponseEntity<Pagamento> criar(@Valid @RequestBody Pagamento pagamento, HttpServletResponse response){
		Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idpagamento}")
				.buildAndExpand(pagamentoSalvo.getId_pagamento()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(pagamentoSalvo);
	}
	
	@GetMapping("/{idpagamento}")
	public ResponseEntity <Pagamento> buscarPeloCodigo(@PathVariable Long idpagamento){
		Pagamento pagamento = pagamentoRepository.findById(idpagamento).orElse(null);
		return pagamento != null ? ResponseEntity.ok(pagamento): ResponseEntity.notFound().build();
		
	}
	
	@GetMapping
	public List<Pagamento> buscarPagamentos(){
		List<Pagamento> pagamento = pagamentoRepository.findAll();
		return pagamento;	
	}
	
	@PutMapping("/{idpagamento}")
	public Pagamento atualizar(@PathVariable Long idpagamento, @RequestBody Pagamento pagamento) {

		Pagamento pagamentoSalvo = this.pagamentoRepository.findById(idpagamento).
				  orElse(null);

		  BeanUtils.copyProperties(pagamento, pagamentoSalvo, "id_pagamento");

		  return this.pagamentoRepository.save(pagamentoSalvo);
		}
	
	
	@DeleteMapping("/{idpagamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long idpagamento) {
	this.pagamentoRepository.deleteById(idpagamento);
	}
}
