package com.onlineexam.app.dto.request.master;

import java.time.LocalDateTime;

public class CourseCreateDTO {

	private String courseName;
	private String courseDescription;
	private long userId;
	private LocalDateTime dateTime;
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
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
		builder.append("CourseCreateDTO [courseName=");
		builder.append(courseName);
		builder.append(", courseDescription=");
		builder.append(courseDescription);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}

	

}
