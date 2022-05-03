package com.onlineexam.app.dto.request.exam;

public class AssignExamDeleteDTO {

	private long examId;
	private String examYear;

	public long getExamId() {
		return examId;
	}

	public void setExamId(long examId) {
		this.examId = examId;
	}

	public String getExamYear() {
		return examYear;
	}

	public void setExamYear(String examYear) {
		this.examYear = examYear;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AssignExamDeleteDTO [examId=");
		builder.append(examId);
		builder.append(", examYear=");
		builder.append(examYear);
		builder.append("]");
		return builder.toString();
	}

}
