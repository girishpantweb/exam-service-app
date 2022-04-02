package com.onlineexam.app.dto.request.question;

import java.sql.Blob;
import java.time.LocalDateTime;

public class QuestionModifyDTO {

	private long questionId;
	private long subjectId;
	private long subSubjectId;
	private long topicId;
	private String question;
	private Blob uploads;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answerKey;
	private String description;
	private int difficultyLevel;
	private int userId;
	private LocalDateTime dateTime;

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
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

	public String getAnswerKey() {
		return answerKey;
	}

	public void setAnswerKey(String answerKey) {
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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
		builder.append("QuestionModifyDTO [questionId=");
		builder.append(questionId);
		builder.append(", subjectId=");
		builder.append(subjectId);
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
