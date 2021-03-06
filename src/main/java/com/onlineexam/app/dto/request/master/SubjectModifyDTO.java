package com.onlineexam.app.dto.request.master;

import java.time.LocalDateTime;

public class SubjectModifyDTO {

	private long subjectId;
	private String subjectCode;
	private int courseId;
	private String subjectName;
	private String rules;
	private int totalMarks;
	private int passMarks;
	private int examDuration;
	private long userId;
	private LocalDateTime dateTime;

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public int getPassMarks() {
		return passMarks;
	}

	public void setPassMarks(int passMarks) {
		this.passMarks = passMarks;
	}

	public int getExamDuration() {
		return examDuration;
	}

	public void setExamDuration(int examDuration) {
		this.examDuration = examDuration;
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
		builder.append("SubjectModifyDTO [subjectId=");
		builder.append(subjectId);
		builder.append(", subjectCode=");
		builder.append(subjectCode);
		builder.append(", courseId=");
		builder.append(courseId);
		builder.append(", subjectName=");
		builder.append(subjectName);
		builder.append(", rules=");
		builder.append(rules);
		builder.append(", totalMarks=");
		builder.append(totalMarks);
		builder.append(", passMarks=");
		builder.append(passMarks);
		builder.append(", examDuration=");
		builder.append(examDuration);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}

}
