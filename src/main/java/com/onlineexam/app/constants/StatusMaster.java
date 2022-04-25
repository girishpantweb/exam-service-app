package com.onlineexam.app.constants;

public enum StatusMaster {

	SUCCESS(200, "Success"), FAILED(201, "Failed"), USERNOTREGISTERED(202, "User Not Regsitered"),
	EMAILALREADYREGISTERED(204, "Email Already Registered"), MOBILEALREADYREGISTERED(205, "Mobile Already Registered"),
	MOBILENOTVALID(206, "Mobile Number Not Valid"), EMAILNOTVALID(207, "Email Not Valid"),
	PASSWORDNOTVALID(208, "Password Not Valid"), CHANGEPASSWORD(209, "New & Confirm new password not matched"),
	CHANGEPASSWORDOLD(210, "Old password not valid"),
	INVALIDINPUTREQUESTDTA(211, "Input Request Data Should Not Be Null or empty"),
	ROLEREADYEXIST(212, "Role Already Exist"),
	EMAILORMOBILEINVALID(213, "Email or Mobile Not Valid"),
	INVALIDUSER(214, "Invalid User Name"),
	SUBJECTREADYEXIST(215, "Subject Already Exist"),
	COURSEREADYEXIST(216, "Course Already Exist"),
	DIVISIONREADYEXIST(217, "Division Already Exist"),
	CLASSREADYEXIST(218, "Class Already Exist"),
	STUDENTREADYEXIST(219, "Student Already Exist"),
	SUBSUBJECTREADYEXIST(218, "Sub Subject Already Exist"),
	TOPICREADYEXIST(221, "Topic Already Exist"),
	QUESTIONREADYEXIST(222, "Question Already Exist"),
	EXAMREADYEXIST(222, "Exam Already Exist");

	private long responseCode;
	private String responseMessage;

	StatusMaster(long responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public long getResponseCode() {
		return responseCode;
	}

	void setResponseCode(long responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
