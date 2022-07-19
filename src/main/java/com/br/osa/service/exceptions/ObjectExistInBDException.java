package com.br.osa.service.exceptions;

public class ObjectExistInBDException  extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ObjectExistInBDException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectExistInBDException(String message) {
		super(message);
	}

}
