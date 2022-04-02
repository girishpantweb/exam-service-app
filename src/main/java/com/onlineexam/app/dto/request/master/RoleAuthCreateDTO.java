package com.onlineexam.app.dto.request.master;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RoleAuthCreateDTO {
	
	
	private int editRight;
	private int viewRight;
	private int createRight;
	private int deleteRight;
	@JsonIgnore
	private LocalDateTime createdDate;
	private int createdBy;
	private long menuId;
	private long roleId;
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
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
}
