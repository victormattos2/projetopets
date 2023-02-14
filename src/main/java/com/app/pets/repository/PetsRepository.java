package com.app.pets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pets.entity.Pets;

public interface PetsRepository extends JpaRepository<Pets, Long>{
	
	@Query("select a from Pets a where a.nome like %?1%")
	List<Pets> findByNomeContainingOrderByNome(String nome);

}
