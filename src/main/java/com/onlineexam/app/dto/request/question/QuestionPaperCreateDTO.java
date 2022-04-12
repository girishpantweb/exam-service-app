package com.onlineexam.app.dto.request.question;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionPaperCreateDTO {

	private String questionYear;
	private long classId;
	private long courseId;
	private long subjectId;
	private int noOfSet;
	private List<QuestionSubSubjectCreateDTO> questionSubSubjectCreateDTO;
	private long userId;
	private LocalDateTime dateTime;

	public String getQuestionYear() {
		return questionYear;
	}

	public void setQuestionYear(String questionYear) {
		this.questionYear = questionYear;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public int getNoOfSet() {
		return noOfSet;
	}

	public void setNoOfSet(int noOfSet) {
		this.noOfSet = noOfSet;
	}

	public List<QuestionSubSubjectCreateDTO> getQuestionSubSubjectCreateDTO() {
		return questionSubSubjectCreateDTO;
	}

	public void setQuestionSubSubjectCreateDTO(List<QuestionSubSubjectCreateDTO> questionSubSubjectCreateDTO) {
		this.questionSubSubjectCreateDTO = questionSubSubjectCreateDTO;
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
		builder.append("QuestionPaperCreateDTO [questionYear=");
		builder.append(questionYear);
		builder.append(", classId=");
		builder.append(classId);
		builder.append(", courseId=");
		builder.append(courseId);
		builder.append(", subjectId=");
		builder.append(subjectId);
		builder.append(", noOfSet=");
		builder.append(noOfSet);
		builder.append(", questionSubSubjectCreateDTO=");
		builder.append(questionSubSubjectCreateDTO);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}

}
