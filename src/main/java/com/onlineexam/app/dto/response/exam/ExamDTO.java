package com.onlineexam.app.dto.response.exam;

import com.onlineexam.app.dto.response.master.ClassDTO;
import com.onlineexam.app.dto.response.master.CourseDTO;
import com.onlineexam.app.dto.response.master.DivisionDTO;
import com.onlineexam.app.dto.response.master.SubjectDTO;
import com.onlineexam.app.dto.response.question.QuestionPaperDTO;

public class ExamDTO {

	private long sno;
	private long examId;
	private String examCode;
	private String examYear;
	private CourseDTO courseDTO;
	private DivisionDTO divisionDTO;
	private ClassDTO classDTO;
	private SubjectDTO subjectDTO;
	private QuestionPaperDTO questionPaperDTO;
	private String examDate;
	private String examTime;
	private String userName;
	private int activeStatus;

	public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}

	public long getExamId() {
		return examId;
	}

	public void setExamId(long examId) {
		this.examId = examId;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getExamYear() {
		return examYear;
	}

	public void setExamYear(String examYear) {
		this.examYear = examYear;
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

	public QuestionPaperDTO getQuestionPaperDTO() {
		return questionPaperDTO;
	}

	public void setQuestionPaperDTO(QuestionPaperDTO questionPaperDTO) {
		this.questionPaperDTO = questionPaperDTO;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
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
		builder.append("ExamDTO [sno=");
		builder.append(sno);
		builder.append(", examId=");
		builder.append(examId);
		builder.append(", examCode=");
		builder.append(examCode);
		builder.append(", examYear=");
		builder.append(examYear);
		builder.append(", courseDTO=");
		builder.append(courseDTO);
		builder.append(", divisionDTO=");
		builder.append(divisionDTO);
		builder.append(", classDTO=");
		builder.append(classDTO);
		builder.append(", subjectDTO=");
		builder.append(subjectDTO);
		builder.append(", questionPaperDTO=");
		builder.append(questionPaperDTO);
		builder.append(", examDate=");
		builder.append(examDate);
		builder.append(", examTime=");
		builder.append(examTime);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append("]");
		return builder.toString();
	}

}
