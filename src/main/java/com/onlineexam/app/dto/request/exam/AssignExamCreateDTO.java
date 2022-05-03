package com.onlineexam.app.dto.request.exam;

import java.time.LocalDateTime;
import java.util.List;

public class AssignExamCreateDTO {

	private long examId;
	private String examYear;
	private List<AssignStudentExamCreateDTO> assignStudentExamCreateDTOList;
	private long userId;
	private LocalDateTime dateTime;

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

	public List<AssignStudentExamCreateDTO> getAssignStudentExamCreateDTOList() {
		return assignStudentExamCreateDTOList;
	}

	public void setAssignStudentExamCreateDTOList(List<AssignStudentExamCreateDTO> assignStudentExamCreateDTOList) {
		this.assignStudentExamCreateDTOList = assignStudentExamCreateDTOList;
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
		builder.append("AssignExamCreateDTO [examId=");
		builder.append(examId);
		builder.append(", examYear=");
		builder.append(examYear);
		builder.append(", assignStudentExamCreateDTOList=");
		builder.append(assignStudentExamCreateDTOList);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}

}
