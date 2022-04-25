package com.onlineexam.app.service;

import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.request.student.StudentDeleteDTO;
import com.onlineexam.app.dto.request.student.StudentModifyDTO;
import com.onlineexam.app.dto.request.student.StudentsCreateDTO;

public interface IStudentService {
	
	ServiceResponseDTO getAllStudents(int pageIndex, int totalNumberOfRecords);
	
	ServiceResponseDTO getAllStudentByStudentId(int pageIndex, int totalNumberOfRecords , long studentId);

	ServiceResponseDTO saveStudents(StudentsCreateDTO studentsCreateDTO);

	ServiceResponseDTO updateStudents(StudentModifyDTO studentModifyDTO);

	ServiceResponseDTO deleteStudents(StudentDeleteDTO studentDeleteDTO);

}
