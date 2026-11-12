package com.usuario.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice //GESTIONAR Y CENTRALIZAR LOS ERRORES/EXCEPTIONS
public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
		public ResponseEntity<String> handleResponseStatusException(
			ResponseStatusException exception){
		
		//DEVUELVE SOLO EL MENSAJE PERSONALIZADO DE LAS EXCEPTIONS
		return ResponseEntity.status(exception.getStatusCode())
				.body(exception.getReason());
	}
		 
}
