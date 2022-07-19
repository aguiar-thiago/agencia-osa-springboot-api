package com.br.osa.controller.exceptions;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.osa.service.exceptions.ObjectExistInBDException;
import com.br.osa.service.exceptions.ObjectNotFounException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ObjectNotFounException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFounException e, ServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(ObjectExistInBDException.class)
	public ResponseEntity<StandardError> objectExistInDatabase(ObjectExistInBDException e, ServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.CONFLICT.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

}
