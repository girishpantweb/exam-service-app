package com.onlineexam.app.dto.request.question;

import java.time.LocalDateTime;
import java.util.List;

public class ManageSubSetCreateDTO {

	private long quesSubsetId;
	private List<Integer> questionId;
	private long userId;
	private LocalDateTime dateTime;

	public long getQuesSubsetId() {
		return quesSubsetId;
	}

	public void setQuesSubsetId(long quesSubsetId) {
		this.quesSubsetId = quesSubsetId;
	}

	public List<Integer> getQuestionId() {
		return questionId;
	}

	public void setQuestionId(List<Integer> questionId) {
		this.questionId = questionId;
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
		builder.append("ManageSubSetCreateDTO [quesSubsetId=");
		builder.append(quesSubsetId);
		builder.append(", questionId=");
		builder.append(questionId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}

}
