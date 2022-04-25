package com.onlineexam.app.dto.request.question;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionPaperModifyDTO {

	private long questionPaperId;
	private String questionYear;
	private String questionPaperCode;
	private long classId;
	private long courseId;
	private long divisionId;
	private long subjectId;
	private int noOfSet;
	private List<QuestionSubSubjectModifyDTO> questionSubSubjectModifyDTO;
	private long userId;
	private LocalDateTime dateTime;

	public long getQuestionPaperId() {
		return questionPaperId;
	}

	public void setQuestionPaperId(long questionPaperId) {
		this.questionPaperId = questionPaperId;
	}

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

	public int getNoOfSet() {
		return noOfSet;
	}

	public void setNoOfSet(int noOfSet) {
		this.noOfSet = noOfSet;
	}

	public List<QuestionSubSubjectModifyDTO> getQuestionSubSubjectModifyDTO() {
		return questionSubSubjectModifyDTO;
	}

	public void setQuestionSubSubjectModifyDTO(List<QuestionSubSubjectModifyDTO> questionSubSubjectModifyDTO) {
		this.questionSubSubjectModifyDTO = questionSubSubjectModifyDTO;
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
		builder.append("QuestionPaperModifyDTO [questionPaperId=");
		builder.append(questionPaperId);
		builder.append(", questionYear=");
		builder.append(questionYear);
		builder.append(", questionPaperCode=");
		builder.append(questionPaperCode);
		builder.append(", classId=");
		builder.append(classId);
		builder.append(", courseId=");
		builder.append(courseId);
		builder.append(", divisionId=");
		builder.append(divisionId);
		builder.append(", subjectId=");
		builder.append(subjectId);
		builder.append(", noOfSet=");
		builder.append(noOfSet);
		builder.append(", questionSubSubjectModifyDTO=");
		builder.append(questionSubSubjectModifyDTO);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}
}
