package com.usuario.config;


import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/*
 * QUE ES UN TOKEN? UNA CREDENCIAL, INFORMACION
 * CICLO DE VIDA DE UN TOKEN? EMISION, ALMACENAMIENTO, USO, VALIDACION, VENCIMIENTO O REVOCACION, RENOVACION(OPC)
 * DONDE GUARDAS UN TOKEN? EN NAVERGADOR, APLICACIONES, SERVIDORES O BD
 * 
 */

@Component
public class JwtUtils {

	//CLAVE SECRETA DE 32 CARACTERES - 256 BITS
	private static final String SECRET_KEY = "$2a$16$2mrF/TVy21Ja9io5wPzgReDI1vK8pKqnMPW/AQBqT/wSBG6oFPQnK\r\n";
	
	//TIEMPO DE EXPIRACION DE 1 HORA
	private static final long EXPIRATION_TIME = 3600000;
	
	private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	
	//GENERA TOKEN - JWT JSON WEB TOKEN
	public String generarToken(String username, String role) {
		return Jwts
				.builder()
				.setSubject(username)
				.claim("role",role)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
} 
