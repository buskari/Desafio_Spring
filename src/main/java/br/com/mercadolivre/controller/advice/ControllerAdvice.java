package br.com.mercadolivre.controller.advice;

import java.security.InvalidParameterException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.mercadolivre.service.ValidatorException;

@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> genericErrorHandler(Exception e) {
		
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getLocalizedMessage());
		e.printStackTrace();
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<ErrorMessage> invalidParameterExceptionHandler(Exception e) {
		
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage());
		
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ValidatorException.class)
	public ResponseEntity<ErrorMessage> validatorExceptionHandler(Exception e) {
		
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage());
		
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> ProductAlreadyExistExceptionHandler(Exception e) {
		
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.CONFLICT.value(), e.getLocalizedMessage());
		
		return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<ErrorMessage> OrderException(Exception e) {
		
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage());
		
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

}
