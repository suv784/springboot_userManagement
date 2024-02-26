package com.org.UserManage.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler { 
	@ExceptionHandler(UnexpectedTypeException.class)
	public ResponseEntity<Object>handleUnexpectedTypeException(UnexpectedTypeException ute){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ute.getMessage());
	}
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object>handleNoSuchElementException(NoSuchElementException nse){ 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nse.getMessage());
		
	}
	@ExceptionHandler(NoNameFoundException.class)
	public ResponseEntity<Object>handleNoNameFoundException(NoNameFoundException nnf){ 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nnf.getCause());
	}
	@ExceptionHandler(SalaryNotFoundException.class)
	public ResponseEntity<Object>handleSalaryNotFoundException(SalaryNotFoundException snf){
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(snf.getCause());
	}
	@ExceptionHandler(NoSuchGenderFoundException.class)
	public ResponseEntity<Object>handleNoSuchGenderFoundException(NoSuchGenderFoundException nsgf){
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(nsgf.getCause());
	} 
	

}
