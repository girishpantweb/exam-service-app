package com.onlineexam.app.beans;

public class RoleMasterBean {

	private int roleId;
	private String roleName;
	private String roleStatus;
	private String updateBy;
	private String createdBy;
	private int slNo;
	
	
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
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
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the roleStatus
	 */
	public String getRoleStatus() {
		return roleStatus;
	}
	/**
	 * @param roleStatus the roleStatus to set
	 */
	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}
	/**
	 * @return the updateBy
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * @param UpdateBy the UpdateBy to set
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoleMasterDTO [roleId=" + roleId + ", roleName=" + roleName + ", roleStatus=" + roleStatus
				+ ", updateBy=" + updateBy + ", createdBy=" + createdBy + "]";
	}
}
