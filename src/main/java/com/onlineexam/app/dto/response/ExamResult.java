package com.onlineexam.app.dto.response;

import com.onlineexam.app.dto.response.master.SubjectDTO;

public class ExamResult {

	private String correctAnswer;
	private String totalQuestIons;
	private String totalAttemptedQuestions;
	private long examId;
	private String examCode;
	private SubjectDTO subjectDTO;
	private String setNo;

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getTotalQuestIons() {
		return totalQuestIons;
	}

	public void setTotalQuestIons(String totalQuestIons) {
		this.totalQuestIons = totalQuestIons;
	}

	public String getTotalAttemptedQuestions() {
		return totalAttemptedQuestions;
	}

	public void setTotalAttemptedQuestions(String totalAttemptedQuestions) {
		this.totalAttemptedQuestions = totalAttemptedQuestions;
	}

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

	public SubjectDTO getSubjectDTO() {
		return subjectDTO;
	}

	public void setSubjectDTO(SubjectDTO subjectDTO) {
		this.subjectDTO = subjectDTO;
	}

	public String getSetNo() {
		return setNo;
	}

	public void setSetNo(String setNo) {
		this.setNo = setNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExamResult [correctAnswer=");
		builder.append(correctAnswer);
		builder.append(", totalQuestIons=");
		builder.append(totalQuestIons);
		builder.append(", totalAttemptedQuestions=");
		builder.append(totalAttemptedQuestions);
		builder.append(", examId=");
		builder.append(examId);
		builder.append(", examCode=");
		builder.append(examCode);
		builder.append(", subjectDTO=");
		builder.append(subjectDTO);
		builder.append(", setNo=");
		builder.append(setNo);
		builder.append("]");
		return builder.toString();
	}

}
