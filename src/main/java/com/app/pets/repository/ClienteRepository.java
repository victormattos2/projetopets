package com.app.pets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pets.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Query("select a from Cliente a where a.nome like %?1%")
	List<Cliente> findByNomeContainingOrderByNome(String nome);
}
