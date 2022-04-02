package com.onlineexam.app.dto.request.master;

import java.time.LocalDateTime;

public class RoleCreateDTO {

	private String roleName;
	private long userId;
	private LocalDateTime dateTime;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
		builder.append("RoleCreateDTO [roleName=");
		builder.append(roleName);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}

}
