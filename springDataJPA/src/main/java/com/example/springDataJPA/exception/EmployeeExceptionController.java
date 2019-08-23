package com.example.springDataJPA.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;






@ControllerAdvice
public class EmployeeExceptionController extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = EmployeeNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> exception(EmployeeNotFoundException ex){
		System.out.println("Exception method of @controllerAdvice called");
		ExceptionResponse response = new ExceptionResponse();
		System.out.println("HttpStatus.NOT_FOUND.name()"  + HttpStatus.NOT_FOUND);
	
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setErrorMessage("Employee Not found");
		
		
		return new ResponseEntity<>(response,response.getStatus());
	
		
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		BindingResult bind = ex.getBindingResult();
		
		System.out.println("get all errors   " + bind.getAllErrors());
		System.out.println("get field erros  " + bind.getFieldErrors());
		
		String str =bind.getFieldError().getDefaultMessage();
		
		System.out.println("Default message " + str);
		
		ErrorDetails error = new ErrorDetails(new Date(), "Validation failed", str);
		
		
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
		
		
		
	}
	

	
}
