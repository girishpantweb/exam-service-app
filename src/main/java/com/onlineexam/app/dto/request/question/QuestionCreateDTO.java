package com.onlineexam.app.dto.request.question;

import java.sql.Blob;
import java.time.LocalDateTime;

public class QuestionCreateDTO {

	private long subjectId;
	private long courseId;
	private long divisionId;
	private long classId;
	private long subSubjectId;
	private long topicId;
	private String question;
	private Blob uploads;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private int answerKey;
	private String description;
	private int difficultyLevel;
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

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Blob getUploads() {
		return uploads;
	}

	public void setUploads(Blob uploads) {
		this.uploads = uploads;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public int getAnswerKey() {
		return answerKey;
	}

	public void setAnswerKey(int answerKey) {
		this.answerKey = answerKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
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
		builder.append("QuestionCreateDTO [subjectId=");
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
		builder.append(", question=");
		builder.append(question);
		builder.append(", uploads=");
		builder.append(uploads);
		builder.append(", option1=");
		builder.append(option1);
		builder.append(", option2=");
		builder.append(option2);
		builder.append(", option3=");
		builder.append(option3);
		builder.append(", option4=");
		builder.append(option4);
		builder.append(", answerKey=");
		builder.append(answerKey);
		builder.append(", description=");
		builder.append(description);
		builder.append(", difficultyLevel=");
		builder.append(difficultyLevel);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}
}
