package com.onlineexam.app.dto.request.question;

public class QuestionSetDTO {

	private long questionPaperId;
	private long courseId;
	private long divisionId;
	private long classId;
	private long subjectId;
	private long setId;

	public long getQuestionPaperId() {
		return questionPaperId;
	}

	public void setQuestionPaperId(long questionPaperId) {
		this.questionPaperId = questionPaperId;
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

	public long getSetId() {
		return setId;
	}

	public void setSetId(long setId) {
		this.setId = setId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuestionSetDTO [questionPaperId=");
		builder.append(questionPaperId);
		builder.append(", courseId=");
		builder.append(courseId);
		builder.append(", divisionId=");
		builder.append(divisionId);
		builder.append(", classId=");
		builder.append(classId);
		builder.append(", subjectId=");
		builder.append(subjectId);
		builder.append(", setId=");
		builder.append(setId);
		builder.append("]");
		return builder.toString();
	}
}
