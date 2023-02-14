package com.app.pets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.app.pets.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Query("select a from Produto a where a.nome like %?1%")
	List<Produto> findByNomeContainingOrderByNome(String nome);
	
	
	@Query("select a from Produto a where a.nome like %?1% and a.quantidade > 0")
	List<Produto> findByprodutomaiorquezero(String nome);
	

}
