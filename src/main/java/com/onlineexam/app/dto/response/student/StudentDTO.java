package com.onlineexam.app.dto.response.student;

import com.onlineexam.app.dto.response.master.CourseDTO;

public class StudentDTO {

	private long sno;
	private long studentId;
	private String studentName;
	private String studentEmail;
	private CourseDTO courseDTO;
	private String dob;
	private String address;
	private String contactNumber;
	private String userName;
	private int activeStatus;

	public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

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

	public CourseDTO getCourseDTO() {
		return courseDTO;
	}

	public void setCourseDTO(CourseDTO courseDTO) {
		this.courseDTO = courseDTO;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StudentDTO [sno=");
		builder.append(sno);
		builder.append(", studentId=");
		builder.append(studentId);
		builder.append(", studentName=");
		builder.append(studentName);
		builder.append(", studentEmail=");
		builder.append(studentEmail);
		builder.append(", courseDTO=");
		builder.append(courseDTO);
		builder.append(", dob=");
		builder.append(dob);
		builder.append(", address=");
		builder.append(address);
		builder.append(", contactNumber=");
		builder.append(contactNumber);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append("]");
		return builder.toString();
	}

}
