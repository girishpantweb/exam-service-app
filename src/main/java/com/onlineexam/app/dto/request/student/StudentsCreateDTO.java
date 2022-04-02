package com.onlineexam.app.dto.request.student;

import java.time.LocalDateTime;

public class StudentsCreateDTO {

	private String studentName;
	private String studentEmail;
	private long courseId;
	private String dob;
	private String address;
	private String contactNumber;
	private int userId;
	private LocalDateTime dateTime;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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
		builder.append("StudentsCreateDTO [studentName=");
		builder.append(studentName);
		builder.append(", studentEmail=");
		builder.append(studentEmail);
		builder.append(", courseId=");
		builder.append(courseId);
		builder.append(", dob=");
		builder.append(dob);
		builder.append(", address=");
		builder.append(address);
		builder.append(", contactNumber=");
		builder.append(contactNumber);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}
}
