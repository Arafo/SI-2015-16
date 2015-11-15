package com.capa.persistencia.exceptions;

public class InvalidUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUserException(String message) {
		super(message);
	}

	public InvalidUserException(String message, Throwable throwable) {
		super(message, throwable);	
	}
}