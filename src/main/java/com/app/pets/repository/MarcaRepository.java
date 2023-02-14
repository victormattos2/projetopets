package com.app.pets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.app.pets.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{
	
	@Query("select a from Marca a where a.nome like %?1%")
	List<Marca> findByNomeContainingOrderByNome(String nome);
	

}
