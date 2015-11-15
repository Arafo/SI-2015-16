package com.capa.persistencia.exceptions;

public class InvalidPasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPasswordException(String message) {
		super(message);
	}

	public InvalidPasswordException(String message, Throwable throwable) {
		super(message, throwable);	
	}
}