package com.app.pets.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.pets.entity.Cidade;

import com.app.pets.repository.CidadeRepository;

@RestController
@RequestMapping("/cidade")
@CrossOrigin(origins = "*")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;



	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) {

	

		return cidadeRepository.save(cidade);

	}

	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade atualizar(@RequestBody Cidade cidade) {

		

		return cidadeRepository.save(cidade);

	}

	@GetMapping("lista")
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	
	@GetMapping("/{id_cidade}")
	public Optional<Cidade> buscarPeloCodigo(@PathVariable Long id_cidade) {
		Optional<Cidade> cidade = cidadeRepository.findById(id_cidade);
		return cidade;
	}

}
