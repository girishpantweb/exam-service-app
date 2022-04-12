package com.onlineexam.app.dto.response.question;

import com.onlineexam.app.dto.response.master.SubSubjectDTO;

public class QuestionSubSubjectDTO {

	private SubSubjectDTO subjectDTO;
	private long noOfQuestion;

	public SubSubjectDTO getSubjectDTO() {
		return subjectDTO;
	}

	public void setSubjectDTO(SubSubjectDTO subjectDTO) {
		this.subjectDTO = subjectDTO;
	}

	public long getNoOfQuestion() {
		return noOfQuestion;
	}

	public void setNoOfQuestion(long noOfQuestion) {
		this.noOfQuestion = noOfQuestion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuestionSubSubjectDTO [subjectDTO=");
		builder.append(subjectDTO);
		builder.append(", noOfQuestion=");
		builder.append(noOfQuestion);
		builder.append("]");
		return builder.toString();
	}

}
