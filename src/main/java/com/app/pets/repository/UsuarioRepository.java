package com.app.pets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pets.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByLogin(String login);
	
	@Query(value = 
			 " select *               "
		    +" FROM usuario u         "
			+" where u.login = ?1 and "		 
			+"       u.senha = ?2     "
			, nativeQuery = true)
	public Usuario validaLogin(String login, String senha);
	
	
	
}
