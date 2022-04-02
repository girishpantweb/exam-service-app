package com.onlineexam.app.dto;

import java.util.List;

public class MenuMasterDTO {

	private int menuId;
	private int menuParentId;
	private String menuName;
	private String menuRouter;
	private String menuIcon;
	private int menuSortId;
	private String menuClass;
	private List<MenuMasterDTO> subMenus;
	private int menuStatus;
	private int slNo;
	

	public int getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(int menuStatus) {
		this.menuStatus = menuStatus;
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

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuRouter() {
		return menuRouter;
	}

	public void setMenuRouter(String menuRouter) {
		this.menuRouter = menuRouter;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public int getMenuSortId() {
		return menuSortId;
	}

	public void setMenuSortId(int menuSortId) {
		this.menuSortId = menuSortId;
	}

	public String getMenuClass() {
		return menuClass;
	}

	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}

	public List<MenuMasterDTO> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<MenuMasterDTO> subMenus) {
		this.subMenus = subMenus;
	}

	public int getSlNo() {
		return slNo;
	}

	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}

	@Override
	public String toString() {
		return "MenuMasterDTO [menuId=" + menuId + ", menuParentId=" + menuParentId + ", menuName=" + menuName
				+ ", menuRouter=" + menuRouter + ", menuIcon=" + menuIcon + ", menuSortId=" + menuSortId
				+ ", menuClass=" + menuClass + ", subMenus=" + subMenus + ", menuStatus=" + menuStatus + ", slNo="
				+ slNo + "]";
	}


}
