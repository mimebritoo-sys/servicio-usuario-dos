package com.usuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.dto.UsuarioDTO;
import com.usuario.modelo.Usuario;
import com.usuario.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	@PostMapping("/registrar")
	public ResponseEntity<Usuario> registrarUsuarios(@RequestBody Usuario usuario){
		Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);	
	}
	
	//PARA AUTENTICAR USUARIO
	@PostMapping("/login")
	public ResponseEntity<String> autenticarUsuarioPassword(
		@RequestBody UsuarioDTO usuarioDTO){
		String response =
				usuarioService.autenticarUsuario(usuarioDTO.getUsername(),
						usuarioDTO.getPassword());
		//RETURN STATUS HTTP 200 OK
		return ResponseEntity.ok(response);
	}
}
 