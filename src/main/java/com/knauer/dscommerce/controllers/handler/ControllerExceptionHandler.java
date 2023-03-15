package com.knauer.dscommerce.controllers.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.knauer.dscommerce.dto.CustomError;
import com.knauer.dscommerce.dto.ValidationError;
import com.knauer.dscommerce.service.exceptions.IntegrityViolationException;
import com.knauer.dscommerce.service.exceptions.ResourceNotFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomError> notFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		CustomError err = new CustomError(Instant.now(), 
				status.value(), 
				e.getMessage(), 
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(IntegrityViolationException.class)
	public ResponseEntity<CustomError> integrityViolation(IntegrityViolationException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		CustomError err = new CustomError(Instant.now(), 
				status.value(), 
				e.getMessage(), 
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		
		ValidationError err = new ValidationError(Instant.now(), 
				status.value(), 
				"Dados inválidos", 
				request.getRequestURI());
		
		e.getBindingResult().getFieldErrors().forEach(er -> {
			err.addFieldMessage(er.getField(), er.getDefaultMessage());
		});
		
		return ResponseEntity.status(status).body(err);
		
	}
}
