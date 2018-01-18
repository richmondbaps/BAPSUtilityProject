package com.baps.core.exception;

/**
 * 
 * This is a POJO class which is used to send the Error Response as a JSON.
 * 
 * @author Mayuresh Trivedi
 *
 */

public class ErrorResponse {
	
	private int errorCode;
	private String message;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}