package com.onlineexam.app.dto.response;

import com.onlineexam.app.dto.response.master.SubSubjectDTO;
import com.onlineexam.app.dto.response.master.TopicDTO;

public class QuestionPaperSetDTO {

	private long sno;
	private long questionId;
	private long setNo;
	private long sortNo;
	private String question;
	private String optionValue;
	private SubSubjectDTO subjectDTO;
	private TopicDTO topicDTO;
	private int activeStatus;

	public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getSetNo() {
		return setNo;
	}

	public void setSetNo(long setNo) {
		this.setNo = setNo;
	}

	public long getSortNo() {
		return sortNo;
	}

	public void setSortNo(long sortNo) {
		this.sortNo = sortNo;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public SubSubjectDTO getSubjectDTO() {
		return subjectDTO;
	}

	public void setSubjectDTO(SubSubjectDTO subjectDTO) {
		this.subjectDTO = subjectDTO;
	}

	public TopicDTO getTopicDTO() {
		return topicDTO;
	}

	public void setTopicDTO(TopicDTO topicDTO) {
		this.topicDTO = topicDTO;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuestionPaperSetDTO [sno=");
		builder.append(sno);
		builder.append(", questionId=");
		builder.append(questionId);
		builder.append(", setNo=");
		builder.append(setNo);
		builder.append(", sortNo=");
		builder.append(sortNo);
		builder.append(", question=");
		builder.append(question);
		builder.append(", optionValue=");
		builder.append(optionValue);
		builder.append(", subjectDTO=");
		builder.append(subjectDTO);
		builder.append(", topicDTO=");
		builder.append(topicDTO);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append("]");
		return builder.toString();
	}

}
