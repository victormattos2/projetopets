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

import com.app.pets.entity.Agendamento;
import com.app.pets.repository.AgendamentoRepository;

@RestController
@RequestMapping("/agendamento")
@CrossOrigin(origins = "*")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	

	@PostMapping
	public ResponseEntity<Agendamento> criar(@Valid @RequestBody Agendamento agendamento, HttpServletResponse response){
		Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
		
		//var nova_data LOcalDaTEtIME = sf.parse(Agendamento.data_hora);
	    //Agendaimento.data_Hora = nova_data
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idagendamento}")
				.buildAndExpand(agendamentoSalvo.getId_agendamento()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(agendamentoSalvo);
	}
	
	@GetMapping("/{idagendamento}")
	public ResponseEntity <Agendamento> buscarPeloCodigo(@PathVariable Long idagendamento){
		Agendamento agendamento = agendamentoRepository.findById(idagendamento).orElse(null);
		return agendamento != null ? ResponseEntity.ok(agendamento): ResponseEntity.notFound().build();
		
	}
	
	@GetMapping
	public List<Agendamento> buscarAgendamento(){
		List<Agendamento> agendamento = agendamentoRepository.findAll();
		return agendamento;
	}
	
	@PutMapping("/{idagendamento}")
	public Agendamento atualizar(@PathVariable Long idagendamento, @RequestBody Agendamento agendamento) {

		Agendamento agendamentoSalvo = this.agendamentoRepository.findById(idagendamento).
				  orElse(null);

		  BeanUtils.copyProperties(agendamento, agendamentoSalvo, "idagendamento");

		  return this.agendamentoRepository.save(agendamentoSalvo);
		}
	 
	
	@DeleteMapping("/{idagendamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long idagendamento) {
	this.agendamentoRepository.deleteById(idagendamento);
	}
	
}
