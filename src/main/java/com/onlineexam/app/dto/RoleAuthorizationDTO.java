package com.onlineexam.app.dto;

public class RoleAuthorizationDTO {

	private int authorizationId;
	private int roleId;
	private int menuId;
	private int authorizationStatus;
	private int edit_rights;
	private int view_rights;
	private int create_rights;
	private int delete_rights;

	/**
	 * @return the authorizationId
	 */
	public int getAuthorizationId() {
		return authorizationId;
	}

	/**
	 * @param authorizationId the authorizationId to set
	 */
	public void setAuthorizationId(int authorizationId) {
		this.authorizationId = authorizationId;
	}

	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setRoleId(int userId) {
		this.roleId = userId;
	}

	/**
	 * @return the menuId
	 */
	public int getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return the authorizationStatus
	 */
	public int getAuthorizationStatus() {
		return authorizationStatus;
	}

	/**
	 * @param authorizationStatus the authorizationStatus to set
	 */
	public void setAuthorizationStatus(int authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}

	/**
	 * @return the edit_rights
	 */
	public int getEdit_rights() {
		return edit_rights;
	}

	/**
	 * @param edit_rights the edit_rights to set
	 */
	public void setEdit_rights(int edit_rights) {
		this.edit_rights = edit_rights;
	}

	/**
	 * @return the view_rights
	 */
	public int getView_rights() {
		return view_rights;
	}

	/**
	 * @param view_rights the view_rights to set
	 */
	public void setView_rights(int view_rights) {
		this.view_rights = view_rights;
	}

	/**
	 * @return the create_rights
	 */
	public int getCreate_rights() {
		return create_rights;
	}

	/**
	 * @param create_rights the create_rights to set
	 */
	public void setCreate_rights(int create_rights) {
		this.create_rights = create_rights;
	}

	/**
	 * @return the delete_rights
	 */
	public int getDelete_rights() {
		return delete_rights;
	}

	/**
	 * @param delete_rights the delete_rights to set
	 */
	public void setDelete_rights(int delete_rights) {
		this.delete_rights = delete_rights;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoleAuthorizationDTO [authorizationId=" + authorizationId + ", userId=" + roleId + ", menuId=" + menuId
				+ ", authorizationStatus=" + authorizationStatus + ", edit_rights=" + edit_rights + ", view_rights="
				+ view_rights + ", create_rights=" + create_rights + ", delete_rights=" + delete_rights + "]";
	}

}
