package br.com.coelhovictor.springapibase.services.exceptions;

public class ConflictException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ConflictException(String message) {
		super(message);
	}
	
	public ConflictException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}