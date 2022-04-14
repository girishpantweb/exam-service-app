package com.onlineexam.app.dto.request.master;

import java.time.LocalDateTime;

public class SubSubjectModifyDTO {

	private long subSubjectId;
	private long courseId;
	private long divisionId;
	private long classId;
	private long subjectId;
	private String subSubjectCode;
	private String subSubjectName;
	private long userId;
	private LocalDateTime dateTime;

	public long getSubSubjectId() {
		return subSubjectId;
	}

	public void setSubSubjectId(long subSubjectId) {
		this.subSubjectId = subSubjectId;
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

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubSubjectCode() {
		return subSubjectCode;
	}

	public void setSubSubjectCode(String subSubjectCode) {
		this.subSubjectCode = subSubjectCode;
	}

	public String getSubSubjectName() {
		return subSubjectName;
	}

	public void setSubSubjectName(String subSubjectName) {
		this.subSubjectName = subSubjectName;
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
		builder.append("SubSubjectModifyDTO [subSubjectId=");
		builder.append(subSubjectId);
		builder.append(", courseId=");
		builder.append(courseId);
		builder.append(", divisionId=");
		builder.append(divisionId);
		builder.append(", classId=");
		builder.append(classId);
		builder.append(", subjectId=");
		builder.append(subjectId);
		builder.append(", subSubjectCode=");
		builder.append(subSubjectCode);
		builder.append(", subSubjectName=");
		builder.append(subSubjectName);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}

}
