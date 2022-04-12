package com.onlineexam.app.dto.request.question;

import java.time.LocalDateTime;

public class QuestionSubSubjectModifyDTO {

	private long questionPaperId;
	private long subSubjectId;
	private int noOfQuestions;
	private long userId;
	private LocalDateTime dateTime;

	public long getQuestionPaperId() {
		return questionPaperId;
	}

	public void setQuestionPaperId(long questionPaperId) {
		this.questionPaperId = questionPaperId;
	}

	public long getSubSubjectId() {
		return subSubjectId;
	}

	public void setSubSubjectId(long subSubjectId) {
		this.subSubjectId = subSubjectId;
	}

	public int getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuestionSubSubjectModifyDTO [questionPaperId=");
		builder.append(questionPaperId);
		builder.append(", subSubjectId=");
		builder.append(subSubjectId);
		builder.append(", noOfQuestions=");
		builder.append(noOfQuestions);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}

}
