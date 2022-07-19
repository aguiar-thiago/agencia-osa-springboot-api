package com.br.osa.service.exceptions;

public class ObjectNotFounException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFounException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFounException(String message) {
		super(message);
	}

}
