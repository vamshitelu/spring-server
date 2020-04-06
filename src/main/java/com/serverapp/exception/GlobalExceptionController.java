package com.serverapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.serverapp.model.CustomError;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(value= UnAuthorizedException.class)
	public ResponseEntity<Object> exception(UnAuthorizedException ex){
		CustomError error = new CustomError(ex.getMessage(), ex.getClass());
		
		return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value= DataNotFoundException.class)
	public ResponseEntity<Object> dataNotFoundException(DataNotFoundException ex){
		CustomError error = new CustomError(ex.getMessage(), ex.getClass());
		return new ResponseEntity<>(error,HttpStatus.OK);
	}
}
