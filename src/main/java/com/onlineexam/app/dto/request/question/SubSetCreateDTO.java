package com.onlineexam.app.dto.request.question;

import java.time.LocalDateTime;

public class SubSetCreateDTO {

	private long subjectId;
	private long courseId;
	private long divisionId;
	private long classId;
	private long subSubjectId;
	private long topicId;
	private String subsetName;
	private long userId;
	private LocalDateTime dateTime;

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(long divisionId) {
		this.divisionId = divisionId;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public long getSubSubjectId() {
		return subSubjectId;
	}

	public void setSubSubjectId(long subSubjectId) {
		this.subSubjectId = subSubjectId;
	}

	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public String getSubsetName() {
		return subsetName;
	}

	public void setSubsetName(String subsetName) {
		this.subsetName = subsetName;
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
		builder.append("SubSetCreateDTO [subjectId=");
		builder.append(subjectId);
		builder.append(", courseId=");
		builder.append(courseId);
		builder.append(", divisionId=");
		builder.append(divisionId);
		builder.append(", classId=");
		builder.append(classId);
		builder.append(", subSubjectId=");
		builder.append(subSubjectId);
		builder.append(", topicId=");
		builder.append(topicId);
		builder.append(", subsetName=");
		builder.append(subsetName);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}

}
