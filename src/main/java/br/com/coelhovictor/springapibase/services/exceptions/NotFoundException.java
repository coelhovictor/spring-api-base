package br.com.coelhovictor.springapibase.services.exceptions;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotFoundException(String objectName, Object id) {
		super(objectName + " '" + id + "' not found");
	}
	
	public NotFoundException(String objectName, Object id, 
			Throwable throwable) {
		super(objectName + " '" + id + "' not found", throwable);
	}
	
}