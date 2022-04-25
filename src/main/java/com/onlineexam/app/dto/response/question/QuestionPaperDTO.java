package com.onlineexam.app.dto.response.question;

import java.util.List;

import com.onlineexam.app.dto.response.master.ClassDTO;
import com.onlineexam.app.dto.response.master.CourseDTO;
import com.onlineexam.app.dto.response.master.DivisionDTO;
import com.onlineexam.app.dto.response.master.SubjectDTO;

public class QuestionPaperDTO {

	private long sno;
	private long questionPaperId;
	private String questionYear;
	private String questionPaperCode;
	private CourseDTO courseDTO;
	private DivisionDTO divisionDTO;
	private ClassDTO classDTO;
	private SubjectDTO subjectDTO;
	private List<QuestionSubSubjectDTO> questionSubSubjectDTO;
	private int noofSet;
	private String userName;
	private int activeStatus;

	public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}

	public long getQuestionPaperId() {
		return questionPaperId;
	}

	public void setQuestionPaperId(long questionPaperId) {
		this.questionPaperId = questionPaperId;
	}

	public String getQuestionYear() {
		return questionYear;
	}

	public void setQuestionYear(String questionYear) {
		this.questionYear = questionYear;
	}

	public String getQuestionPaperCode() {
		return questionPaperCode;
	}

	public void setQuestionPaperCode(String questionPaperCode) {
		this.questionPaperCode = questionPaperCode;
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

	public List<QuestionSubSubjectDTO> getQuestionSubSubjectDTO() {
		return questionSubSubjectDTO;
	}

	public void setQuestionSubSubjectDTO(List<QuestionSubSubjectDTO> questionSubSubjectDTO) {
		this.questionSubSubjectDTO = questionSubSubjectDTO;
	}

	public int getNoofSet() {
		return noofSet;
	}

	public void setNoofSet(int noofSet) {
		this.noofSet = noofSet;
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
		builder.append("QuestionPaperDTO [sno=");
		builder.append(sno);
		builder.append(", questionPaperId=");
		builder.append(questionPaperId);
		builder.append(", questionYear=");
		builder.append(questionYear);
		builder.append(", questionPaperCode=");
		builder.append(questionPaperCode);
		builder.append(", courseDTO=");
		builder.append(courseDTO);
		builder.append(", divisionDTO=");
		builder.append(divisionDTO);
		builder.append(", classDTO=");
		builder.append(classDTO);
		builder.append(", subjectDTO=");
		builder.append(subjectDTO);
		builder.append(", questionSubSubjectDTO=");
		builder.append(questionSubSubjectDTO);
		builder.append(", noofSet=");
		builder.append(noofSet);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append("]");
		return builder.toString();
	}

}
