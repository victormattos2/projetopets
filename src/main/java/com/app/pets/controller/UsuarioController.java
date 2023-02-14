package com.app.pets.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.pets.entity.Usuario;
import com.app.pets.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	//private final PasswordEncoder encoder;
		
	//public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
	//	this.usuarioRepository =usuarioRepository;
	//	this.encoder = encoder;
	//}
	
	@PostMapping
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response){
		//usuario.setSenha(encoder.encode(usuario.getSenha()));
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body (usuarioSalvo);
	}
	
	/*@GetMapping("/validarSenha")
	public ResponseEntity<Boolean> validarSenha(@RequestParam String login,
												@RequestParam String senha) {
		
		Optional<Usuario> optUsuario = usuarioRepository.findByLogin(login);
		if (Optional.empty() != null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		
		Usuario usuario = optUsuario.get();
		boolean valid = encoder.matches(senha, usuario.getSenha());
		
		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status).body(valid);
	}*/
	
	@GetMapping("/{idusuario}")
	public ResponseEntity <Usuario> buscarPeloCodigo(@PathVariable Long idusuario){
		Usuario usuario = usuarioRepository.findById(idusuario).orElse(null);
		return usuario != null ? ResponseEntity.ok(usuario): ResponseEntity.notFound().build();
		
	}
	
	@GetMapping
	public List<Usuario> buscarUsuarios(){
		List<Usuario> usuario = usuarioRepository.findAll();
		return usuario;	
	}
	
	@PutMapping("/{idusuario}")
	public Usuario atualizar(@PathVariable Long idusuario, @RequestBody Usuario usuario) {

		  Usuario usuarioSalvo = this.usuarioRepository.findById(idusuario).
				  orElse(null);

		  BeanUtils.copyProperties(usuario, usuarioSalvo, "idusuario");

		  return this.usuarioRepository.save(usuarioSalvo);
		}
	
	
	@DeleteMapping("/{idusuario}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("idusuario") Long idusuario) {
	this.usuarioRepository.deleteById(idusuario);
	}
	
	
	 @GetMapping("/validar")
	    public ResponseEntity validar(@PathParam("login") String login, @PathParam("senha") String senha ) {
	    	System.out.println("login:"+login+" - senha:"+senha);
	    	Usuario usuario =    usuarioRepository.validaLogin(login, senha);
	    	if(usuario != null) {
	            return new ResponseEntity(usuario, HttpStatus.OK);
	        }

	        return new ResponseEntity("usuario nao encontrado", HttpStatus.NOT_FOUND);
	        
	    }
	    
	
}
