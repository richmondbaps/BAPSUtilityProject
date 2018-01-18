package com.baps.core.exception;

/**
 * 
 * This is the Location Exception class which extends Exception class of Java.
 * It is used to send the Exception at Run time for various validation errors
 * and HTTP errors.
 * 
 * @author Mayuresh Trivedi
 *
 */
public class LocationException  extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private int errorCode;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public LocationException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public LocationException(String errorMessage, int errorCode) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	public LocationException() {
		super();
	}
}
