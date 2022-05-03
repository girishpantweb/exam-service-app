package com.onlineexam.app.dto.request.exam;

public class ExamResultDTO {

	private long studentId;
	private long examId;
	private long questionId;
	private long answerKey;
	private int isAnswered;
	private long userId;

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getExamId() {
		return examId;
	}

	public void setExamId(long examId) {
		this.examId = examId;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getAnswerKey() {
		return answerKey;
	}

	public void setAnswerKey(long answerKey) {
		this.answerKey = answerKey;
	}

	public int getIsAnswered() {
		return isAnswered;
	}

	public void setIsAnswered(int isAnswered) {
		this.isAnswered = isAnswered;
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
		builder.append("ExamResultDTO [studentId=");
		builder.append(studentId);
		builder.append(", examId=");
		builder.append(examId);
		builder.append(", questionId=");
		builder.append(questionId);
		builder.append(", answerKey=");
		builder.append(answerKey);
		builder.append(", isAnswered=");
		builder.append(isAnswered);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}
