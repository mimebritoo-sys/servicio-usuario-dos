package com.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuario.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	//VERIFICAR SI EL USUARIO EXISTE EN BD MYSQL
	boolean existsByUsername(String username);

	
	//PARA AUTENTICAR USUARIO Y PASSWORD
	Optional<Usuario> findByUsername(String username);
}
