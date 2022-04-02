package com.onlineexam.app.dto.request.master;

public class SubjectDeleteDTO {

	private long subjectId;
	private int activeStatus;
	private long userId;

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
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
		builder.append("SubjectDeleteDTO [subjectId=");
		builder.append(subjectId);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}
