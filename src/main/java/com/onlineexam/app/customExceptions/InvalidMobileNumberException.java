package com.onlineexam.app.customExceptions;

public class InvalidMobileNumberException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode;

	public InvalidMobileNumberException(int errorCode, String exceptionMessage) {
		super(exceptionMessage);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
