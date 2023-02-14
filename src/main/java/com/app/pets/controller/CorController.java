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


import com.app.pets.entity.Cor;
import com.app.pets.repository.CorRepository;

@RestController
@RequestMapping("/cor")
@CrossOrigin(origins = "*")
public class CorController {
	
	@Autowired
	private CorRepository corRepository;
	
	

	@PostMapping
	public ResponseEntity<Cor> criar(@Valid @RequestBody Cor cor, HttpServletResponse response){
		Cor corSalvo = corRepository.save(cor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idcor}")
				.buildAndExpand(corSalvo.getId_cor()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(corSalvo);
	}
	
	@GetMapping("/{idcor}")
	public ResponseEntity <Cor> buscarPeloCodigo(@PathVariable Long idcor){
		Cor cor = corRepository.findById(idcor).orElse(null);
		return cor != null ? ResponseEntity.ok(cor): ResponseEntity.notFound().build();
		
	}
	
	@GetMapping
	public List<Cor> buscarCor(){
		List<Cor> cor = corRepository.findAll();
		return cor;	
	}
	
	@PutMapping("/{idcor}")
	public Cor atualizar(@PathVariable Long idcor, @RequestBody Cor cor) {

		Cor corSalvo = this.corRepository.findById(idcor).
				  orElse(null);

		  BeanUtils.copyProperties(cor, corSalvo, "id_cor");

		  return this.corRepository.save(corSalvo);
		}
	
	
	@DeleteMapping("/{idcor}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id_cor) {
	this.corRepository.deleteById(id_cor);
	}
}
