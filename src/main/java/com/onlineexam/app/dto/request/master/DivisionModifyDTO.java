package com.onlineexam.app.dto.request.master;

import java.time.LocalDateTime;

public class DivisionModifyDTO {

	private long divisionId;
	private String divisionName;
	private String divisionDescription;
	private long courseId;
	private long userId;
	private LocalDateTime dateTime;

	public long getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(long divisionId) {
		this.divisionId = divisionId;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getDivisionDescription() {
		return divisionDescription;
	}

	public void setDivisionDescription(String divisionDescription) {
		this.divisionDescription = divisionDescription;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
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
		builder.append("DivisionModifyDTO [divisionId=");
		builder.append(divisionId);
		builder.append(", divisionName=");
		builder.append(divisionName);
		builder.append(", divisionDescription=");
		builder.append(divisionDescription);
		builder.append(", courseId=");
		builder.append(courseId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}

}
