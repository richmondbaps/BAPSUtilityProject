package com.baps.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
 
/**
 * This class used the Controller Advice of Spring framework for Exception handling.
 * 
 * 
 * @author Mayuresh Trivedi
 *
 */

@ControllerAdvice
public class ExceptionControllerAdvice {
 
	/**
	 * This is the main Exception Handler method to handle the exceptions.
	 * 
	 * @param ex Exception raised at Runtime.
	 * 
	 * @return Spring ResponseEntity with Custom Error Response.
	 * 
	 */
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("Please contact your administrator");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
}