/*package com.app.pets.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.app.pets.data.DetalheUsuarioData;
import com.app.pets.entity.Usuario;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    private final com.app.pets.repository.UsuarioRepository usuarioRepository;

    public DetalheUsuarioServiceImpl(com.app.pets.repository.UsuarioRepository repository) {
        this.usuarioRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
        if (Optional.empty() != null) {
            throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
        }

        return new DetalheUsuarioData(usuario);
    }

}*/