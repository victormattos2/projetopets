package com.app.pets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pets.entity.Movimentacao;
import com.app.pets.entity.Produto;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{
	
	@Query("select a from Produto a where a.nome like %?1%")
	List<Produto> findByNomeContainingOrderByNome(String nome);
}
