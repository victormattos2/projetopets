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

import com.app.pets.entity.Produto;
import com.app.pets.entity.report.GeradorRelatorio;
import com.app.pets.repository.ProdutoRepository;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private GeradorRelatorio geradorRelatorio;

	@PostMapping
	public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto, HttpServletResponse response){
		Produto produtoSalvo = produtoRepository.save(produto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idproduto}")
				.buildAndExpand(produtoSalvo.getId_produto()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(produtoSalvo);
	}
	
	@GetMapping("/{idproduto}")
	public ResponseEntity <Produto> buscarPeloCodigo(@PathVariable Long idproduto){
		Produto produto = produtoRepository.findById(idproduto).orElse(null);
		return produto != null ? ResponseEntity.ok(produto): ResponseEntity.notFound().build();
		
	}
	
	@GetMapping
	public List<Produto> buscarProduto(){
		List<Produto> produto = produtoRepository.findAll();
		return produto;	
	}
	
	@PutMapping("/{idproduto}")
	public Produto atualizar(@PathVariable Long idproduto, @RequestBody Produto produto) {

		  Produto produtoSalvo = this.produtoRepository.findById(idproduto).
				  orElse(null);

		  BeanUtils.copyProperties(produto, produtoSalvo, "idproduto");

		  return this.produtoRepository.save(produtoSalvo);
		}
	
	
	@DeleteMapping("/{idproduto}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long idproduto) {
	this.produtoRepository.deleteById(idproduto);
	}
	
	@PostMapping("filtrar")
	public List<Produto> filtrar(@RequestBody String filtro){
		 return this.produtoRepository.findByNomeContainingOrderByNome(filtro);
		
	}
	
	@PostMapping("filtrarmaiorqzero")
	public List<Produto> filtrarmaiorqzero(@RequestBody String filtro){
		 return this.produtoRepository.findByprodutomaiorquezero(filtro);
		
	}
	
	@GetMapping("/relatorio-pdf")
	public ResponseEntity<byte[]> registroPDF() throws JRException {

		List<Produto> produto = produtoRepository.findAll();

		byte[] relatorio = geradorRelatorio.ImprimiRelatorioProduto(produto); 
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);

	}
}
