package com.onlineexam.app.dto.request.question;

public class QuestionPaperSetCreateDTO {

	private long questionPaperId;
	private long setNo;
	private long questionId;
	private long sortNo;
	private long userId;

	public long getQuestionPaperId() {
		return questionPaperId;
	}

	public void setQuestionPaperId(long questionPaperId) {
		this.questionPaperId = questionPaperId;
	}

	public long getSetNo() {
		return setNo;
	}

	public void setSetNo(long setNo) {
		this.setNo = setNo;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getSortNo() {
		return sortNo;
	}

	public void setSortNo(long sortNo) {
		this.sortNo = sortNo;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuestionPaperSetCreateDTO [questionPaperId=");
		builder.append(questionPaperId);
		builder.append(", setNo=");
		builder.append(setNo);
		builder.append(", questionId=");
		builder.append(questionId);
		builder.append(", sortNo=");
		builder.append(sortNo);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}
}
