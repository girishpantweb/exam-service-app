package com.onlineexam.app.dto;

import com.onlineexam.app.anotations.MobileValidatorConstraint;
import com.onlineexam.app.dto.response.student.StudentDTO;

public class UserMasterDTO {

	private int userId;
	private String userName;
	@MobileValidatorConstraint
	private String userMobile;
	private String userEmail;
	private String password;
	private int roleId;
	private StudentDTO studentDTO;
	private String isFirstLogin;
	private String userStatus;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public StudentDTO getStudentDTO() {
		return studentDTO;
	}
	public void setStudentDTO(StudentDTO studentDTO) {
		this.studentDTO = studentDTO;
	}
	public String getIsFirstLogin() {
		return isFirstLogin;
	}
	public void setIsFirstLogin(String isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserMasterDTO [userId=");
		builder.append(userId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userMobile=");
		builder.append(userMobile);
		builder.append(", userEmail=");
		builder.append(userEmail);
		builder.append(", password=");
		builder.append(password);
		builder.append(", roleId=");
		builder.append(roleId);
		builder.append(", studentDTO=");
		builder.append(studentDTO);
		builder.append(", isFirstLogin=");
		builder.append(isFirstLogin);
		builder.append(", userStatus=");
		builder.append(userStatus);
		builder.append("]");
		return builder.toString();
	}
}
