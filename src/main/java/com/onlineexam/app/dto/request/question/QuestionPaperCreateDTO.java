package com.onlineexam.app.dto.request.question;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionPaperCreateDTO {

	private String questionYear;
	private String questionPaperCode;
	private long courseId;
	private long divisionId;
	private long subjectId;
	private long classId;
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

	public String getQuestionPaperCode() {
		return questionPaperCode;
	}

	public void setQuestionPaperCode(String questionPaperCode) {
		this.questionPaperCode = questionPaperCode;
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

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
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
		builder.append(", questionPaperCode=");
		builder.append(questionPaperCode);
		builder.append(", courseId=");
		builder.append(courseId);
		builder.append(", divisionId=");
		builder.append(divisionId);
		builder.append(", subjectId=");
		builder.append(subjectId);
		builder.append(", classId=");
		builder.append(classId);
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
