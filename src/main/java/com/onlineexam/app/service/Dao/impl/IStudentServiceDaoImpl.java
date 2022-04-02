package com.onlineexam.app.service.Dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.onlineexam.app.dto.request.student.StudentDeleteDTO;
import com.onlineexam.app.dto.request.student.StudentModifyDTO;
import com.onlineexam.app.dto.request.student.StudentsCreateDTO;
import com.onlineexam.app.dto.response.master.CourseDTO;
import com.onlineexam.app.dto.response.student.StudentDTO;
import com.onlineexam.app.service.Dao.IStudentServiceDao;
import com.onlineexam.app.utils.CommonUtility;

@Repository("studentServiceDaoImpl")
public class IStudentServiceDaoImpl implements IStudentServiceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private Environment env;

	@Override
	public List<StudentDTO> getAllStudent(int pageIndex, int totalRecords) {
		String query = "";
		if (pageIndex == 0 && totalRecords == 0)
			query = env.getProperty("fetchStudentMasterQuery") + " where sm.active_status=1";
		else
			query = env.getProperty("fetchStudentMasterQuery") + "  limit " + (pageIndex * totalRecords) + " , "
					+ totalRecords;

		return jdbcTemplate.query(query, new RowMapper<StudentDTO>() {
			@Override
			public StudentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				StudentDTO studentDTO = new StudentDTO();
				CourseDTO courseDTO = new CourseDTO();
				courseDTO.setCourseId(rs.getLong("course_id"));
				courseDTO.setCourseName(rs.getString("course_name"));
				studentDTO.setCourseDTO(courseDTO);
				studentDTO.setStudentId(rs.getLong("student_id"));
				studentDTO.setStudentName(rs.getString("student_name"));
				studentDTO.setStudentEmail(rs.getString("student_email"));
				studentDTO.setDob(rs.getString("dob"));
				studentDTO.setAddress(rs.getString("address"));
				studentDTO.setContactNumber(rs.getString("contact_number"));
				studentDTO.setActiveStatus(rs.getInt("active_status"));
				studentDTO.setUserName(rs.getString("user_name"));
				return studentDTO;
			}
		});
	}

	@Override
	public Integer getTotalStudentCount() {
		String countQuery = env.getProperty("countStudentMasterQuery");
		return jdbcTemplate.queryForObject(countQuery, Integer.class);
	}

	@Override
	public Integer findStudentCountByRoleName(String studentName) throws SQLException {
		String checkDublicateQuery = env.getProperty("countForStudentDuplicacyQuery");
		return jdbcTemplate.queryForObject(checkDublicateQuery, new Object[] { studentName },
				new int[] { Types.VARCHAR }, Integer.class);

	}

	@Override
	public int saveStudent(StudentsCreateDTO studentsCreateDTO) throws SQLException {
		String saveRoleMasterQuery = env.getProperty("saveStudentQuery");
		return jdbcTemplate.update(saveRoleMasterQuery,
				new Object[] { studentsCreateDTO.getStudentName(), studentsCreateDTO.getStudentEmail(),
						studentsCreateDTO.getCourseId(), studentsCreateDTO.getDob(), studentsCreateDTO.getAddress(),
						studentsCreateDTO.getContactNumber(), studentsCreateDTO.getUserId() });
	}

	@Override
	public int updateStudent(StudentModifyDTO studentModifyDTO) throws SQLException {
		String updateRoleMasterQuery = env.getProperty("updateStudentQuery");
		return jdbcTemplate.update(updateRoleMasterQuery, studentModifyDTO.getStudentName(),
				studentModifyDTO.getStudentEmail(), studentModifyDTO.getCourseId(), studentModifyDTO.getDob(),
				studentModifyDTO.getAddress(), studentModifyDTO.getContactNumber(), CommonUtility.getCurrentDateTime(),
				studentModifyDTO.getStudentId());
	}

	@Override
	public int deleteStudent(StudentDeleteDTO studentDeleteDTO) throws SQLException {
		String updateRoleMasterQuery = env.getProperty("deleteStudentQuery");
		return jdbcTemplate.update(updateRoleMasterQuery, studentDeleteDTO.getActiveStatus(),
				studentDeleteDTO.getUserId(), CommonUtility.getCurrentDateTime(), studentDeleteDTO.getStudentId());
	}

}
