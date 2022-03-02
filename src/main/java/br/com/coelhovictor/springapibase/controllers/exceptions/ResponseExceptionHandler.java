package br.com.coelhovictor.springapibase.controllers.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.coelhovictor.springapibase.services.exceptions.NotFoundException;

@ControllerAdvice
public class ResponseExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> notFound(NotFoundException e, 
			HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ErrorResponse error = new ErrorResponse(new Date(System.currentTimeMillis()), 
				httpStatus.value(), httpStatus.getReasonPhrase(), e.getMessage(), 
				request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(error);
	}
	
}
