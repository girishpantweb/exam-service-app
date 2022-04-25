package com.onlineexam.app.dto.request.exam;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public class ExamModifyDTO {

	private long examId;
	private String examCode;
	private String examYear;
	private long courseId;
	private long divisionId;
	private long classId;
	private long subjectId;
	private long questionPaperId;
	private Date examDate;
	private Time examTime;
	private long userId;
	private LocalDateTime dateTime;

	public long getExamId() {
		return examId;
	}

	public void setExamId(long examId) {
		this.examId = examId;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getExamYear() {
		return examYear;
	}

	public void setExamYear(String examYear) {
		this.examYear = examYear;
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

	public long getQuestionPaperId() {
		return questionPaperId;
	}

	public void setQuestionPaperId(long questionPaperId) {
		this.questionPaperId = questionPaperId;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Time getExamTime() {
		return examTime;
	}

	public void setExamTime(Time examTime) {
		this.examTime = examTime;
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
		builder.append("ExamModifyDTO [examId=");
		builder.append(examId);
		builder.append(", examCode=");
		builder.append(examCode);
		builder.append(", examYear=");
		builder.append(examYear);
		builder.append(", courseId=");
		builder.append(courseId);
		builder.append(", divisionId=");
		builder.append(divisionId);
		builder.append(", classId=");
		builder.append(classId);
		builder.append(", subjectId=");
		builder.append(subjectId);
		builder.append(", questionPaperId=");
		builder.append(questionPaperId);
		builder.append(", examDate=");
		builder.append(examDate);
		builder.append(", examTime=");
		builder.append(examTime);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}
}
