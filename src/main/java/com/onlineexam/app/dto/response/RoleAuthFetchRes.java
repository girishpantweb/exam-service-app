package com.onlineexam.app.dto.response;
import java.util.List;
public class RoleAuthFetchRes {
	private RoleAuthFetctDTO parent;
	private List<RoleAuthFetchRes> children;
	public RoleAuthFetctDTO getParent() {
		return parent;
	}
	public void setParent(RoleAuthFetctDTO parent) {
		this.parent = parent;
	}
	public List<RoleAuthFetchRes> getChildren() {
		return children;
	}
	public void setChildren(List<RoleAuthFetchRes> children) {
		this.children = children;
	}
	
}
