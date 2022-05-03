package com.onlineexam.app.dto.request.exam;

import java.util.List;

public class AsignStudentExamQuestionsDTO {

	private long sNo;
	private long questionId;
	private String question;
	private List<OptionsDTO> options;

	public long getsNo() {
		return sNo;
	}

	public void setsNo(long sNo) {
		this.sNo = sNo;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<OptionsDTO> getOptions() {
		return options;
	}

	public void setOptions(List<OptionsDTO> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AsignStudentExamQuestionsDTO [sNo=");
		builder.append(sNo);
		builder.append(", questionId=");
		builder.append(questionId);
		builder.append(", question=");
		builder.append(question);
		builder.append(", options=");
		builder.append(options);
		builder.append("]");
		return builder.toString();
	}

}
