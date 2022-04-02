package com.onlineexam.app.constants;

public enum ExceptionsMaster {

	INVALID_MOBILE(100, "Invalid Mobile Number");

	private int exceptionId;
	private String exceptionMessage;

	private ExceptionsMaster(int exceptionId, String exceptionMessage) {
		this.exceptionId = exceptionId;
		this.exceptionMessage = exceptionMessage;
	}

	public int getExceptionId() {
		return exceptionId;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}
}
