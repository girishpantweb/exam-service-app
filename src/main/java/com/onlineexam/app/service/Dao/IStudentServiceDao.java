package com.onlineexam.app.service.Dao;

import java.sql.SQLException;
import java.util.List;
import com.onlineexam.app.dto.request.student.StudentDeleteDTO;
import com.onlineexam.app.dto.request.student.StudentModifyDTO;
import com.onlineexam.app.dto.request.student.StudentsCreateDTO;
import com.onlineexam.app.dto.response.student.StudentDTO;

public interface IStudentServiceDao {

	List<StudentDTO> getAllStudent(int pageIndex, int totalRecords);

	StudentDTO getAllStudentByUserId(long userId);

	List<StudentDTO> getAllStudentByStudentId(long studentId);

	Integer getTotalStudentCount();

	Integer findStudentCountByRoleName(String studentName) throws SQLException;

	int saveStudent(StudentsCreateDTO studentsCreateDTO) throws SQLException;

	int updateStudent(StudentModifyDTO studentModifyDTO) throws SQLException;

	int deleteStudent(StudentDeleteDTO studentDeleteDTO) throws SQLException;

}
