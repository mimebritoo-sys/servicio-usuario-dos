package com.usuario.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.usuario.config.JwtUtils;
import com.usuario.modelo.Usuario;
import com.usuario.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	
	//PARA GENERAR TOKEN JWT
	private final JwtUtils jwtUtils;
	
	public Usuario registrarUsuario(Usuario usuario) {
		if (usuarioRepository.existsByUsername(usuario.getUsername())) {
			throw new ResponseStatusException(
					HttpStatus.CONFLICT,
					"❌ EL USUARIO '" + usuario.getUsername()+
					"' YA EXISTE EN SISTEMA"
					);
		}
		
		//ENCRIPTA EL PASSWORD
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}
	
	//autenticar usuario y password
	public String autenticarUsuario(String username, String password) {
		//BUSCAR SI EL USUARIO EXISTE EN LA BASE DE DATOS
		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.UNAUTHORIZED, "❌ USUARIO O CONTRASEÑA INCORRECTOS"
						));
		//VERIFICAR SI LA CONTRASEÑA ENCRIPTADA COINCIDE
		if (!passwordEncoder.matches(password, usuario.getPassword())) {
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "❌ USUARIO O CONTRASEÑA INCORRECTOS"
					);
		}
		return jwtUtils.generarToken(usuario.getUsername(),usuario.getPassword());
	}
}


