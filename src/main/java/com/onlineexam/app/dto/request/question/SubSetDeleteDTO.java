package com.onlineexam.app.dto.request.question;

public class SubSetDeleteDTO {

	private long quesSubsetId;
	private int activeStatus;
	private int userId;

	public long getQuesSubsetId() {
		return quesSubsetId;
	}

	public void setQuesSubsetId(long quesSubsetId) {
		this.quesSubsetId = quesSubsetId;
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
		builder.append("SubSetDeleteDTO [quesSubsetId=");
		builder.append(quesSubsetId);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}
