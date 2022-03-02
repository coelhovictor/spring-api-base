package br.com.coelhovictor.springapibase.controllers.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException e, 
			HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationErrorResponse error = new ValidationErrorResponse(
				new Date(System.currentTimeMillis()), httpStatus.value(), 
				httpStatus.getReasonPhrase(), "", 
				request.getRequestURI());
		
		for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			error.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.status(httpStatus).body(error);
	}
	
}
