package com.app.pets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pets.entity.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

}
