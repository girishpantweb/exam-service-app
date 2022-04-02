package com.onlineexam.app.dto.request.master;

import java.time.LocalDateTime;

public class TopicModifyDTO {

	private long topicId;
	private long subSubjectId;
	private long subjectId;
	private String topicCode;
	private String topicName;
	private long maxNumberofQuestions;
	private long userId;
	private LocalDateTime dateTime;

	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public long getSubSubjectId() {
		return subSubjectId;
	}

	public void setSubSubjectId(long subSubjectId) {
		this.subSubjectId = subSubjectId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getTopicCode() {
		return topicCode;
	}

	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public long getMaxNumberofQuestions() {
		return maxNumberofQuestions;
	}

	public void setMaxNumberofQuestions(long maxNumberofQuestions) {
		this.maxNumberofQuestions = maxNumberofQuestions;
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
		builder.append("TopicModifyDTO [topicId=");
		builder.append(topicId);
		builder.append(", subSubjectId=");
		builder.append(subSubjectId);
		builder.append(", subjectId=");
		builder.append(subjectId);
		builder.append(", topicCode=");
		builder.append(topicCode);
		builder.append(", topicName=");
		builder.append(topicName);
		builder.append(", maxNumberofQuestions=");
		builder.append(maxNumberofQuestions);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}
}
