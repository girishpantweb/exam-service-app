package com.onlineexam.app.service.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.onlineexam.app.dto.request.UserMasterCreateDTO;
import com.onlineexam.app.dto.request.UserMasterModifyDTO;
import com.onlineexam.app.dto.request.student.StudentDeleteDTO;
import com.onlineexam.app.dto.request.student.StudentModifyDTO;
import com.onlineexam.app.dto.request.student.StudentsCreateDTO;
import com.onlineexam.app.dto.response.master.ClassDTO;
import com.onlineexam.app.dto.response.master.CourseDTO;
import com.onlineexam.app.dto.response.master.DivisionDTO;
import com.onlineexam.app.dto.response.student.StudentDTO;
import com.onlineexam.app.service.Dao.IStudentServiceDao;
import com.onlineexam.app.utils.CommonUtility;

@Repository("studentServiceDaoImpl")
public class IStudentServiceDaoImpl implements IStudentServiceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private Environment env;
	@Autowired
	private PasswordEncoder passwordEncoder;

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
				DivisionDTO divisionDTO = new DivisionDTO();
				divisionDTO.setDivisionId(rs.getLong("division_id"));
				divisionDTO.setDivisionName(rs.getString("division_name"));
				ClassDTO classDTO = new ClassDTO();
				classDTO.setClassId(rs.getLong("class_id"));
				classDTO.setClassName(rs.getString("class_name"));
				studentDTO.setDivisionDTO(divisionDTO);
				studentDTO.setClassDTO(classDTO);
				studentDTO.setCourseDTO(courseDTO);
				studentDTO.setStudentId(rs.getLong("student_id"));
				studentDTO.setStudentName(rs.getString("student_name"));
				studentDTO.setStudentEmail(rs.getString("student_email"));
				studentDTO.setDob(rs.getString("dob"));
				studentDTO.setGaurdianName(rs.getString("gaurdian_name"));
				studentDTO.setAddress(rs.getString("address"));
				studentDTO.setContactNumber(rs.getString("contact_number"));
				studentDTO.setLoginId(rs.getLong("login_id"));
				studentDTO.setActiveStatus(rs.getInt("active_status"));
				studentDTO.setUserName(rs.getString("user_name"));
				return studentDTO;
			}
		});
	}

	@Override
	public StudentDTO getAllStudentByUserId(long userId) {
		String query = env.getProperty("fetchStudentMasterQueryByUserId");
		return jdbcTemplate.queryForObject(query, new Object[] { userId }, new int[] { Types.BIGINT },
				new BeanPropertyRowMapper<StudentDTO>(StudentDTO.class));
	}

	@Override
	public List<StudentDTO> getAllStudentByStudentId(long studentId) {
		String query = env.getProperty("fetchStudentMasterQueryByStudentId");
		return jdbcTemplate.query(query, new Object[] { studentId }, new int[] { Types.BIGINT },
				new RowMapper<StudentDTO>() {
					@Override
					public StudentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						StudentDTO studentDTO = new StudentDTO();
						CourseDTO courseDTO = new CourseDTO();
						courseDTO.setCourseId(rs.getLong("course_id"));
						courseDTO.setCourseName(rs.getString("course_name"));
						DivisionDTO divisionDTO = new DivisionDTO();
						divisionDTO.setDivisionId(rs.getLong("division_id"));
						divisionDTO.setDivisionName(rs.getString("division_name"));
						ClassDTO classDTO = new ClassDTO();
						classDTO.setClassId(rs.getLong("class_id"));
						classDTO.setClassName(rs.getString("class_name"));
						studentDTO.setDivisionDTO(divisionDTO);
						studentDTO.setClassDTO(classDTO);
						studentDTO.setCourseDTO(courseDTO);
						studentDTO.setStudentId(rs.getLong("student_id"));
						studentDTO.setStudentName(rs.getString("student_name"));
						studentDTO.setStudentEmail(rs.getString("student_email"));
						studentDTO.setDob(rs.getString("dob"));
						studentDTO.setGaurdianName(rs.getString("gaurdian_name"));
						studentDTO.setAddress(rs.getString("address"));
						studentDTO.setContactNumber(rs.getString("contact_number"));
						studentDTO.setLoginId(rs.getLong("login_id"));
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
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int saveStudent(StudentsCreateDTO studentsCreateDTO) throws SQLException {
		String query = env.getProperty("createUserQuery");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		UserMasterCreateDTO userMasterCreateDTO = studentsCreateDTO.getUserMasterCreateDTO();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, userMasterCreateDTO.getUserName());
				ps.setLong(2, userMasterCreateDTO.getMobileNumber());
				ps.setString(3, userMasterCreateDTO.getEmail());
				ps.setLong(4, userMasterCreateDTO.getRoleId());
				ps.setLong(5, userMasterCreateDTO.getCreatedBy());
				ps.setLong(6, userMasterCreateDTO.getCreatedBy());
				ps.setString(7, passwordEncoder.encode(userMasterCreateDTO.getPassword()));
				ps.setString(8, CommonUtility.getCurrentDateTime());
				ps.setString(9, CommonUtility.getCurrentDateTime());
				return ps;
			}
		}, keyHolder);
		long userId = keyHolder.getKey().longValue();
		String saveRoleMasterQuery = env.getProperty("saveStudentQuery");
		return jdbcTemplate.update(saveRoleMasterQuery, new Object[] { userId, studentsCreateDTO.getStudentName(),
				studentsCreateDTO.getStudentEmail(), studentsCreateDTO.getCourseId(), studentsCreateDTO.getDivisionId(),
				studentsCreateDTO.getClassId(), studentsCreateDTO.getGaurdianName(), studentsCreateDTO.getDob(),
				studentsCreateDTO.getAddress(), studentsCreateDTO.getContactNumber(), studentsCreateDTO.getUserId() });
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int updateStudent(StudentModifyDTO studentModifyDTO) throws SQLException {
		String query = env.getProperty("updateUserQuery");
		UserMasterModifyDTO userMasterModifyDTO = studentModifyDTO.getUserMasterModifyDTO();
		jdbcTemplate.update(query, userMasterModifyDTO.getUserName(), userMasterModifyDTO.getMobileNumber(),
				userMasterModifyDTO.getEmail(), userMasterModifyDTO.getRoleId(), userMasterModifyDTO.getUpdatedBy(),
				CommonUtility.getCurrentDateTime(), userMasterModifyDTO.getUserId());
		String updateRoleMasterQuery = env.getProperty("updateStudentQuery");
		return jdbcTemplate.update(updateRoleMasterQuery, studentModifyDTO.getStudentName(),
				studentModifyDTO.getStudentEmail(), studentModifyDTO.getCourseId(), studentModifyDTO.getDivisionId(),
				studentModifyDTO.getClassId(), studentModifyDTO.getGaurdianName(), studentModifyDTO.getDob(),
				studentModifyDTO.getAddress(), studentModifyDTO.getContactNumber(), studentModifyDTO.getUserId(),
				CommonUtility.getCurrentDateTime(), studentModifyDTO.getStudentId());
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int deleteStudent(StudentDeleteDTO studentDeleteDTO) throws SQLException {
		String updateRoleMasterQuery = env.getProperty("deleteStudentQuery");
		jdbcTemplate.update(updateRoleMasterQuery, studentDeleteDTO.getActiveStatus(), studentDeleteDTO.getUserId(),
				CommonUtility.getCurrentDateTime(), studentDeleteDTO.getStudentId());
		String query = env.getProperty("updateUserStatusQuery");
		return jdbcTemplate.update(query, studentDeleteDTO.getActiveStatus(), studentDeleteDTO.getUserId(),
				CommonUtility.getCurrentDateTime(), studentDeleteDTO.getLoginId());
	}

}
