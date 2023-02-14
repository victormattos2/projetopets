package com.app.pets.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.app.pets.entity.Cliente;
import com.app.pets.entity.Pets;
import com.app.pets.entity.report.GeradorRelatorio;
import com.app.pets.repository.PetsRepository;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/pets")
@CrossOrigin(origins = "*")
public class PetsController {
	
	@Autowired
	private PetsRepository petsRepository;
	
	@Autowired
	private GeradorRelatorio geradorRelatorio;
	

	@PostMapping
	public ResponseEntity<Pets> criar(@Valid @RequestBody Pets pets, HttpServletResponse response){
		Pets petsSalvo = petsRepository.save(pets);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idpets}")
				.buildAndExpand(petsSalvo.getId_pets()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(petsSalvo);
	}
	
	@GetMapping("/{idpets}")
	public ResponseEntity <Pets> buscarPeloCodigo(@PathVariable Long idpets){
		Pets pets = petsRepository.findById(idpets).orElse(null);
		return pets != null ? ResponseEntity.ok(pets): ResponseEntity.notFound().build();
		
	}
	
	@GetMapping
	public List<Pets> buscarPets(){
		List<Pets> pets = petsRepository.findAll();
		return pets;
	}
	
	@PutMapping("/{idpets}")
	public Pets atualizar(@PathVariable Long idpets, @RequestBody Pets pets) {

		Pets petsSalvo = this.petsRepository.findById(idpets).
				  orElse(null);

		  BeanUtils.copyProperties(pets, petsSalvo, "idpets");

		  return this.petsRepository.save(petsSalvo);
		}
	 
	
	@DeleteMapping("/{idpets}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long idpets) {
	this.petsRepository.deleteById(idpets);
	}
	
	@PostMapping("filtrar")
	public List<Pets> filtrar(@RequestBody String filtro){
		 return this.petsRepository.findByNomeContainingOrderByNome(filtro);
		
	}
	
	@GetMapping("/relatorio-pdf")
	public ResponseEntity<byte[]> registroPDF() throws JRException {

		List<Pets> pets = petsRepository.findAll();

		byte[] relatorio = geradorRelatorio.ImprimiRelatorioPets(pets); 
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);

	}
}
