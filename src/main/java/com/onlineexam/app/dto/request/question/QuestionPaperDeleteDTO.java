package com.onlineexam.app.dto.request.question;

public class QuestionPaperDeleteDTO {

	private long questionPaperId;
	private QuestionSubSubjectDeleteDTO questionSubSubjectDeleteDTO;
	private int activeStatus;
	private int userId;

	public long getQuestionPaperId() {
		return questionPaperId;
	}

	public void setQuestionPaperId(long questionPaperId) {
		this.questionPaperId = questionPaperId;
	}

	public QuestionSubSubjectDeleteDTO getQuestionSubSubjectDeleteDTO() {
		return questionSubSubjectDeleteDTO;
	}

	public void setQuestionSubSubjectDeleteDTO(QuestionSubSubjectDeleteDTO questionSubSubjectDeleteDTO) {
		this.questionSubSubjectDeleteDTO = questionSubSubjectDeleteDTO;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuestionPaperDeleteDTO [questionPaperId=");
		builder.append(questionPaperId);
		builder.append(", questionSubSubjectDeleteDTO=");
		builder.append(questionSubSubjectDeleteDTO);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}
