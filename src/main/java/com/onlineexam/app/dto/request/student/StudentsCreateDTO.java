package com.onlineexam.app.dto.request.student;

import java.time.LocalDateTime;

import com.onlineexam.app.dto.request.UserMasterCreateDTO;

public class StudentsCreateDTO {

	private String studentName;
	private String studentEmail;
	private long courseId;
	private long divisionId;
	private long classId;
	private String gaurdianName;
	private String dob;
	private String address;
	private String contactNumber;
	private UserMasterCreateDTO userMasterCreateDTO;
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

	public long getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(long divisionId) {
		this.divisionId = divisionId;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public String getGaurdianName() {
		return gaurdianName;
	}

	public void setGaurdianName(String gaurdianName) {
		this.gaurdianName = gaurdianName;
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

	public UserMasterCreateDTO getUserMasterCreateDTO() {
		return userMasterCreateDTO;
	}

	public void setUserMasterCreateDTO(UserMasterCreateDTO userMasterCreateDTO) {
		this.userMasterCreateDTO = userMasterCreateDTO;
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
		builder.append(", divisionId=");
		builder.append(divisionId);
		builder.append(", classId=");
		builder.append(classId);
		builder.append(", gaurdianName=");
		builder.append(gaurdianName);
		builder.append(", dob=");
		builder.append(dob);
		builder.append(", address=");
		builder.append(address);
		builder.append(", contactNumber=");
		builder.append(contactNumber);
		builder.append(", userMasterCreateDTO=");
		builder.append(userMasterCreateDTO);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}
}
