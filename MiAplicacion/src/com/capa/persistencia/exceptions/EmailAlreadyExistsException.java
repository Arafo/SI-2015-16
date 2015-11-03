package com.capa.persistencia.exceptions;

public class EmailAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailAlreadyExistsException(String message) {
		super(message);
	}

	public EmailAlreadyExistsException(String message, Throwable throwable) {
		super(message, throwable);	
	}
}