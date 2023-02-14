package com.app.pets.controller;

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

import com.app.pets.entity.Cliente;
import com.app.pets.entity.report.GeradorRelatorio;
import com.app.pets.repository.ClienteRepository;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
	
	//marcar os pontos de injeção
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private GeradorRelatorio geradorRelatorio;
	
	

	@PostMapping
	public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente, HttpServletResponse response){
		cliente.getEnderecos().forEach(end -> {
			end.setCliente(cliente);
		});
		Cliente clienteSalvo = clienteRepository.save(cliente);
		
		/*URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idcliente}")
				.buildAndExpand(clienteSalvo.getId_cliente()).toUri();
		response.setHeader("Location", uri.toASCIIString());*/
		
		return ResponseEntity.status(HttpStatus.CREATED).body (clienteSalvo);
	}
	
	@GetMapping("/{idcliente}")
	public ResponseEntity <Cliente> buscarPeloCodigo(@PathVariable Long idcliente){
		Cliente cliente = clienteRepository.findById(idcliente).orElse(null);
		return cliente != null ? ResponseEntity.ok(cliente): ResponseEntity.notFound().build();
		
	}
	
	@GetMapping
	public List<Cliente> buscarClientes(){
		List<Cliente> cliente = clienteRepository.findAll();
		return cliente;	
	}
	
	@PutMapping("/{idcliente}")
	public Cliente atualizar(@PathVariable Long idcliente, @RequestBody Cliente cliente) {

		  Cliente clienteSalvo = this.clienteRepository.findById(idcliente).
				  orElse(null);

		  BeanUtils.copyProperties(cliente, clienteSalvo, "idcliente");

		  return this.clienteRepository.save(clienteSalvo);
		}
	
	
	@DeleteMapping("/{idcliente}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("idcliente") Long idcliente) {
	this.clienteRepository.deleteById(idcliente);
	}
	
	
	@PostMapping("filtrar")
	public List<Cliente> filtrar(@RequestBody String filtro){
		 return this.clienteRepository.findByNomeContainingOrderByNome(filtro);
		
	}
	
	@GetMapping("/relatorio-pdf")
	public ResponseEntity<byte[]> registroPDF() throws JRException {

		List<Cliente> cliente = clienteRepository.findAll();

		byte[] relatorio = geradorRelatorio.ImprimiRelatorioClientes(cliente); 
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);

	}
}
