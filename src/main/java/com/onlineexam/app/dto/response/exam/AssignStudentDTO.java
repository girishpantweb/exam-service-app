package com.onlineexam.app.dto.response.exam;

import com.onlineexam.app.dto.response.master.ClassDTO;
import com.onlineexam.app.dto.response.master.CourseDTO;
import com.onlineexam.app.dto.response.master.DivisionDTO;

public class AssignStudentDTO {

	private long sno;
	private long studentId;
	private String studentName;
	private CourseDTO courseDTO;
	private DivisionDTO divisionDTO;
	private ClassDTO classDTO;
	private long setNo;

	public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public long getSetNo() {
		return setNo;
	}

	public void setSetNo(long setNo) {
		this.setNo = setNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AssignStudentDTO [sno=");
		builder.append(sno);
		builder.append(", studentId=");
		builder.append(studentId);
		builder.append(", studentName=");
		builder.append(studentName);
		builder.append(", courseDTO=");
		builder.append(courseDTO);
		builder.append(", divisionDTO=");
		builder.append(divisionDTO);
		builder.append(", classDTO=");
		builder.append(classDTO);
		builder.append(", setNo=");
		builder.append(setNo);
		builder.append("]");
		return builder.toString();
	}
}
