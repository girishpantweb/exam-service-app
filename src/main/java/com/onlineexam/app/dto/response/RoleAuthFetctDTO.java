package com.onlineexam.app.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RoleAuthFetctDTO {

	private int menuId;
	@JsonIgnore
	private int menuParentId;
	private int roleAuthId;
	private String parent;
	private int editRight;
	private int viewRight;
	private int createRight;
	private int deleteRight;
	@JsonIgnore
	private List<RoleAuthFetctDTO> subMenus;
	
	
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getMenuParentId() {
		return menuParentId;
	}
	public void setMenuParentId(int menuParentId) {
		this.menuParentId = menuParentId;
	}
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
	public List<RoleAuthFetctDTO> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<RoleAuthFetctDTO> subMenus) {
		this.subMenus = subMenus;
	}
	
	
}
