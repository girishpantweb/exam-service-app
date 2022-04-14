package com.onlineexam.app.dto.response.master;

public class TopicDTO {

	private long sno;
	private long topicId;
	private CourseDTO courseDTO;
	private DivisionDTO divisionDTO;
	private ClassDTO classDTO;
	private SubjectDTO subjectDTO;
	private SubSubjectDTO subSubjectDTO;
	private String topicCode;
	private String topicName;
	private int maxNumberofQuestions;
	private String userName;
	private int activeStatus;

	public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}

	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
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

	public SubjectDTO getSubjectDTO() {
		return subjectDTO;
	}

	public void setSubjectDTO(SubjectDTO subjectDTO) {
		this.subjectDTO = subjectDTO;
	}

	public SubSubjectDTO getSubSubjectDTO() {
		return subSubjectDTO;
	}

	public void setSubSubjectDTO(SubSubjectDTO subSubjectDTO) {
		this.subSubjectDTO = subSubjectDTO;
	}

	public String getTopicCode() {
		return topicCode;
	}

	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public int getMaxNumberofQuestions() {
		return maxNumberofQuestions;
	}

	public void setMaxNumberofQuestions(int maxNumberofQuestions) {
		this.maxNumberofQuestions = maxNumberofQuestions;
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
		builder.append("TopicDTO [sno=");
		builder.append(sno);
		builder.append(", topicId=");
		builder.append(topicId);
		builder.append(", courseDTO=");
		builder.append(courseDTO);
		builder.append(", divisionDTO=");
		builder.append(divisionDTO);
		builder.append(", classDTO=");
		builder.append(classDTO);
		builder.append(", subjectDTO=");
		builder.append(subjectDTO);
		builder.append(", subSubjectDTO=");
		builder.append(subSubjectDTO);
		builder.append(", topicCode=");
		builder.append(topicCode);
		builder.append(", topicName=");
		builder.append(topicName);
		builder.append(", maxNumberofQuestions=");
		builder.append(maxNumberofQuestions);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append("]");
		return builder.toString();
	}

}
