package com.app.pets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pets.entity.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

	
}
