package com.onlineexam.app.dto.response.master;

public class SubjectDTO {

	private long sno;
	private long subjectId;
	private String subjectCode;
	private CourseDTO courseDTO;
	private DivisionDTO divisionDTO;
	private ClassDTO classDTO;
	private String subjectName;
	private String rules;
	private long totalMarks;
	private long passMarks;
	private long examDuration;
	private String userName;
	private int activeStatus;

	public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public CourseDTO getCourseDTO() {
		return courseDTO;
	}

	public void setCourseDTO(CourseDTO courseDTO) {
		this.courseDTO = courseDTO;
	}

	public DivisionDTO getDivisionDTO() {
		return divisionDTO;
	}

	public void setDivisionDTO(DivisionDTO divisionDTO) {
		this.divisionDTO = divisionDTO;
	}

	public ClassDTO getClassDTO() {
		return classDTO;
	}

	public void setClassDTO(ClassDTO classDTO) {
		this.classDTO = classDTO;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public long getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(long totalMarks) {
		this.totalMarks = totalMarks;
	}

	public long getPassMarks() {
		return passMarks;
	}

	public void setPassMarks(long passMarks) {
		this.passMarks = passMarks;
	}

	public long getExamDuration() {
		return examDuration;
	}

	public void setExamDuration(long examDuration) {
		this.examDuration = examDuration;
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
		builder.append("SubjectDTO [sno=");
		builder.append(sno);
		builder.append(", subjectId=");
		builder.append(subjectId);
		builder.append(", subjectCode=");
		builder.append(subjectCode);
		builder.append(", courseDTO=");
		builder.append(courseDTO);
		builder.append(", divisionDTO=");
		builder.append(divisionDTO);
		builder.append(", classDTO=");
		builder.append(classDTO);
		builder.append(", subjectName=");
		builder.append(subjectName);
		builder.append(", rules=");
		builder.append(rules);
		builder.append(", totalMarks=");
		builder.append(totalMarks);
		builder.append(", passMarks=");
		builder.append(passMarks);
		builder.append(", examDuration=");
		builder.append(examDuration);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append("]");
		return builder.toString();
	}
}
