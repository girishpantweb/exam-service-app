package com.onlineexam.app.dto;

import com.onlineexam.app.anotations.MobileValidatorConstraint;

public class UserMasterDTO {

	private int userId;
	private String userName;
	@MobileValidatorConstraint
	private String userMobile;
	private String userEmail;
	private String password;
	private int roleId;
	private String isFirstLogin;
	private String userStatus;
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userMobile
	 */
	public String getUserMobile() {
		return userMobile;
	}
	/**
	 * @param userMobile the userMobile to set
	 */
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the isFirstLogin
	 */
	public String getIsFirstLogin() {
		return isFirstLogin;
	}
	/**
	 * @param isFirstLogin the isFirstLogin to set
	 */
	public void setIsFirstLogin(String isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}
	
	
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the userStatus
	 */
	public String getUserStatus() {
		return userStatus;
	}
	/**
	 * @param userStatus the userStatus to set
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserMasterDTO [userName=" + userName + ", userMobile=" + userMobile + ", userEmail=" + userEmail
				+ ", password=" + password + ", roleId=" + roleId + ", isFirstLogin=" + isFirstLogin + "]";
	}
	
	
	

}
