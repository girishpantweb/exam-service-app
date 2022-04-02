package com.onlineexam.app.pojo;

public class CustomReponseStatus {

	private long responseCode;
	private String responseMessage;

	public CustomReponseStatus(long responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public long getResponseCode() {
		return responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}
}
