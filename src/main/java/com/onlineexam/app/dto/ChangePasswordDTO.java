package com.onlineexam.app.dto;

import javax.validation.constraints.NotNull;

public class ChangePasswordDTO {
	
	@NotNull
	private String oldPassword;
	@NotNull
	private String newPassword;
	@NotNull
	private String confirmNewPassword;
	@NotNull
	private long userId;
	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}
	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	/**
	 * @return the confirmNewPassword
	 */
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	/**
	 * @param confirmNewPassword the confirmNewPassword to set
	 */
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChangePasswordDTO [oldPassword=" + oldPassword + ", newPassword=" + newPassword
				+ ", confirmNewPassword=" + confirmNewPassword + ", userId=" + userId + "]";
	}
	
	
	

}
