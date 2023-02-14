package com.app.pets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.pets.entity.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}

	