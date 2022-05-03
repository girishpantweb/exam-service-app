package com.onlineexam.app.dto.request.exam;

import com.onlineexam.app.dto.response.master.ClassDTO;
import com.onlineexam.app.dto.response.master.CourseDTO;
import com.onlineexam.app.dto.response.master.DivisionDTO;
import com.onlineexam.app.dto.response.master.SubjectDTO;

public class AssignExamDTO {

	private long sno;
	private long examId;
	private String examYear;
	private String examCode;
	private CourseDTO courseDTO;
	private DivisionDTO divisionDTO;
	private SubjectDTO subjectDTO;
	private ClassDTO classDTO;

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

	public String getExamYear() {
		return examYear;
	}

	public void setExamYear(String examYear) {
		this.examYear = examYear;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
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

	public SubjectDTO getSubjectDTO() {
		return subjectDTO;
	}

	public void setSubjectDTO(SubjectDTO subjectDTO) {
		this.subjectDTO = subjectDTO;
	}

	public ClassDTO getClassDTO() {
		return classDTO;
	}

	public void setClassDTO(ClassDTO classDTO) {
		this.classDTO = classDTO;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AssignExamDTO [sno=");
		builder.append(sno);
		builder.append(", examId=");
		builder.append(examId);
		builder.append(", examYear=");
		builder.append(examYear);
		builder.append(", examCode=");
		builder.append(examCode);
		builder.append(", courseDTO=");
		builder.append(courseDTO);
		builder.append(", divisionDTO=");
		builder.append(divisionDTO);
		builder.append(", subjectDTO=");
		builder.append(subjectDTO);
		builder.append(", classDTO=");
		builder.append(classDTO);
		builder.append("]");
		return builder.toString();
	}
}
