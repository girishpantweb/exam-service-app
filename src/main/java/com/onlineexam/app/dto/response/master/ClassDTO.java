package com.onlineexam.app.dto.response.master;

public class ClassDTO {

	private long sno;
	private long classId;
	private String className;
	private String classDescription;
	private String userName;
	private int activeStatus;

	public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassDescription() {
		return classDescription;
	}

	public void setClassDescription(String classDescription) {
		this.classDescription = classDescription;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClassDTO [sno=");
		builder.append(sno);
		builder.append(", classId=");
		builder.append(classId);
		builder.append(", className=");
		builder.append(className);
		builder.append(", classDescription=");
		builder.append(classDescription);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append("]");
		return builder.toString();
	}

}