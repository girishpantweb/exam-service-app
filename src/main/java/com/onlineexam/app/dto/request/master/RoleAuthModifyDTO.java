package com.onlineexam.app.dto.request.master;


import java.time.LocalDateTime;

public class RoleAuthModifyDTO {
	
	private int roleAuthId;
	private int editRight;
	private int viewRight;
	private int createRight;
	private int deleteRight;
	private LocalDateTime modifiedDate;
	private long updatedBy;
	
	public int getRoleAuthId() {
		return roleAuthId;
	}
	public void setRoleAuthId(int roleAuthId) {
		this.roleAuthId = roleAuthId;
	}
	public int getEditRight() {
		return editRight;
	}
	public void setEditRight(int editRight) {
		this.editRight = editRight;
	}
	public int getViewRight() {
		return viewRight;
	}
	public void setViewRight(int viewRight) {
		this.viewRight = viewRight;
	}
	public int getCreateRight() {
		return createRight;
	}
	public void setCreateRight(int createRight) {
		this.createRight = createRight;
	}
	public int getDeleteRight() {
		return deleteRight;
	}
	public void setDeleteRight(int deleteRight) {
		this.deleteRight = deleteRight;
	}
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
}
