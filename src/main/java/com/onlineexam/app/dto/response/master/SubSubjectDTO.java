package com.onlineexam.app.dto.response.master;

public class SubSubjectDTO {

	private long sno;
	private long subSubjectId;
	private SubjectDTO subjectDTO;
	private String subSubjectCode;
	private String subSubjectName;
	private String userName;
	private int activeStatus;

	public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}

	public long getSubSubjectId() {
		return subSubjectId;
	}

	public void setSubSubjectId(long subSubjectId) {
		this.subSubjectId = subSubjectId;
	}

	public SubjectDTO getSubjectDTO() {
		return subjectDTO;
	}

	public void setSubjectDTO(SubjectDTO subjectDTO) {
		this.subjectDTO = subjectDTO;
	}

	public String getSubSubjectCode() {
		return subSubjectCode;
	}

	public void setSubSubjectCode(String subSubjectCode) {
		this.subSubjectCode = subSubjectCode;
	}

	public String getSubSubjectName() {
		return subSubjectName;
	}

	public void setSubSubjectName(String subSubjectName) {
		this.subSubjectName = subSubjectName;
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
		builder.append("SubSubjectDTO [sno=");
		builder.append(sno);
		builder.append(", subSubjectId=");
		builder.append(subSubjectId);
		builder.append(", subjectDTO=");
		builder.append(subjectDTO);
		builder.append(", subSubjectCode=");
		builder.append(subSubjectCode);
		builder.append(", subSubjectName=");
		builder.append(subSubjectName);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append("]");
		return builder.toString();
	}

}
