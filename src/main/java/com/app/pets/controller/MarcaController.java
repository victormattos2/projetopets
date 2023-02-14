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

import com.app.pets.entity.Marca;
import com.app.pets.repository.MarcaRepository;

@RestController
@RequestMapping("/marca")
@CrossOrigin(origins = "*")
public class MarcaController {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	

	@PostMapping
	public ResponseEntity<Marca> criar(@Valid @RequestBody Marca marca, HttpServletResponse response){
		Marca marcaSalvo = marcaRepository.save(marca);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idmarca}")
				.buildAndExpand(marcaSalvo.getId_marca()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(marcaSalvo);
	}
	
	@GetMapping("/{idmarca}")
	public ResponseEntity <Marca> buscarPeloCodigo(@PathVariable Long idmarca){
		Marca marca = marcaRepository.findById(idmarca).orElse(null);
		return marca != null ? ResponseEntity.ok(marca): ResponseEntity.notFound().build();
		
	}
	
	@GetMapping
	public List<Marca> buscarMarcas(){
		List<Marca> marca = marcaRepository.findAll();
		return marca;	
	}
	
	@PutMapping("/{idmarca}")
	public Marca atualizar(@PathVariable Long idmarca, @RequestBody Marca marca) {

		Marca marcaSalvo = this.marcaRepository.findById(idmarca).
				  orElse(null);

		  BeanUtils.copyProperties(marca, marcaSalvo, "id_marca");

		  return this.marcaRepository.save(marcaSalvo);
		}
	
	
	@DeleteMapping("/{idmarca}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id_marca) {
	this.marcaRepository.deleteById(id_marca);
	}
	
	@PostMapping("filtrar")
	public List<Marca> filtrar(@RequestBody String filtro){
		 return this.marcaRepository.findByNomeContainingOrderByNome(filtro);
		
	}
	
}
