package com.onlineexam.app.dto.request.student;

public class StudentDeleteDTO {

	private long studentId;
	private int activeStatus;
	private int userId;

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
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
		builder.append("StudentDeleteDTO [studentId=");
		builder.append(studentId);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}
