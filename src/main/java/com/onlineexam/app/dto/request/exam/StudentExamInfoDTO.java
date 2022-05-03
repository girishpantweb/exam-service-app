package com.onlineexam.app.dto.request.exam;

import com.onlineexam.app.dto.response.master.SubjectDTO;

public class StudentExamInfoDTO {

	private long examId;
	private String examYear;
	private long studentId;
	private String examCode;
	private String examDate;
	private String examTime;
	private SubjectDTO subjectDTO;
	private int examDuration;

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

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public SubjectDTO getSubjectDTO() {
		return subjectDTO;
	}

	public void setSubjectDTO(SubjectDTO subjectDTO) {
		this.subjectDTO = subjectDTO;
	}

	public int getExamDuration() {
		return examDuration;
	}

	public void setExamDuration(int examDuration) {
		this.examDuration = examDuration;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StudentExamInfoDTO [examId=");
		builder.append(examId);
		builder.append(", examYear=");
		builder.append(examYear);
		builder.append(", studentId=");
		builder.append(studentId);
		builder.append(", examCode=");
		builder.append(examCode);
		builder.append(", examDate=");
		builder.append(examDate);
		builder.append(", examTime=");
		builder.append(examTime);
		builder.append(", subjectDTO=");
		builder.append(subjectDTO);
		builder.append(", examDuration=");
		builder.append(examDuration);
		builder.append("]");
		return builder.toString();
	}

}
