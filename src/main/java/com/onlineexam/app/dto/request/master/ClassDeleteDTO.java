package com.onlineexam.app.dto.request.master;

public class ClassDeleteDTO {

	private long classId;
	private int activeStatus;
	private long userId;

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClassDeleteDTO [classId=");
		builder.append(classId);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}
