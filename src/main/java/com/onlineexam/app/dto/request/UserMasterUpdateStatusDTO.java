package com.onlineexam.app.dto.request;

public class UserMasterUpdateStatusDTO {
	private int userStatus;
	private long updatedBy;
	private long userId;
	
	public int getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	public long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
