package com.onlineexam.app.dto.request.question;

public class QuestionDeleteDTO {

	private long questionId;
	private int activeStatus;
	private int userId;

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuestionDeleteDTO [questionId=");
		builder.append(questionId);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}
