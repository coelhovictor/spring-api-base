package br.com.coelhovictor.springapibase.controllers.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.UnexpectedTypeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.coelhovictor.springapibase.services.exceptions.ConflictException;
import br.com.coelhovictor.springapibase.services.exceptions.DataIntegrityException;
import br.com.coelhovictor.springapibase.services.exceptions.NotFoundException;

@ControllerAdvice
public class ResponseExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> notFound(NotFoundException e, 
			HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ErrorResponse error = error(httpStatus, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException e, 
			HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationErrorResponse error = validationError(httpStatus, 
				e.getClass().getSimpleName(), request.getRequestURI());
		
		for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			error.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.status(httpStatus).body(error);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<ErrorResponse> dataIntegrity(DataIntegrityException e, 
			HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = error(httpStatus, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(error);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> messageNotReadable(HttpMessageNotReadableException e, 
			HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = error(httpStatus, e.getClass().getSimpleName(), 
				request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(error);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> methodArgument(MethodArgumentTypeMismatchException e, 
			HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = new ErrorResponse(new Date(System.currentTimeMillis()), 
				httpStatus.value(), httpStatus.getReasonPhrase(), 
				e.getClass().getSimpleName(), request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(error);
	}
	
	@ExceptionHandler(UnexpectedTypeException.class)
	public ResponseEntity<ErrorResponse> unexpectedType(UnexpectedTypeException e, 
			HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = error(httpStatus, e.getClass().getSimpleName(), 
				request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(error);
	}
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ErrorResponse> unexpectedType(ConflictException e, 
			HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.CONFLICT;
		ErrorResponse error = new ErrorResponse(new Date(System.currentTimeMillis()), 
				httpStatus.value(), httpStatus.getReasonPhrase(), e.getMessage(), 
				request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(error);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> unexpectedType(HttpRequestMethodNotSupportedException e, 
			HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = error(httpStatus, e.getClass().getSimpleName(), 
				request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(error);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> unexpectedType(AccessDeniedException e, 
			HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.FORBIDDEN;
		return ResponseEntity.status(httpStatus).build();
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exception(Exception e, 
			HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponse error = error(httpStatus, e.getClass().getSimpleName(), 
				request.getRequestURI());
		e.printStackTrace();
		return ResponseEntity.status(httpStatus).body(error);
	}
	
	private ErrorResponse error(HttpStatus httpStatus, String message, String path) {
		return new ErrorResponse(new Date(System.currentTimeMillis()), 
				httpStatus.value(), httpStatus.getReasonPhrase(), message, 
				path);
	}
	
	private ValidationErrorResponse validationError(HttpStatus httpStatus, String message, 
			String path) {
		return new ValidationErrorResponse(
				new Date(System.currentTimeMillis()), httpStatus.value(), 
				httpStatus.getReasonPhrase(), message, path);
	}
	
}
