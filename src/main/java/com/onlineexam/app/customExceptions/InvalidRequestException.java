package com.onlineexam.app.customExceptions;

public class InvalidRequestException extends UnsupportedOperationException {

	private static final long serialVersionUID = 1L;

	public InvalidRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
