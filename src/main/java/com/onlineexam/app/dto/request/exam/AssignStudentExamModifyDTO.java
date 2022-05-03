package com.onlineexam.app.dto.request.exam;

public class AssignStudentExamModifyDTO {

	private long studentId;
	private long setNo;

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getSetNo() {
		return setNo;
	}

	public void setSetNo(long setNo) {
		this.setNo = setNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AssignStudentExamCreateDTO [studentId=");
		builder.append(studentId);
		builder.append(", setNo=");
		builder.append(setNo);
		builder.append("]");
		return builder.toString();
	}

}
