package com.onlineexam.app.service.Dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.onlineexam.app.dto.request.master.ClassCreateDTO;
import com.onlineexam.app.dto.request.master.ClassDeleteDTO;
import com.onlineexam.app.dto.request.master.ClassModifyDTO;
import com.onlineexam.app.dto.request.master.CourseCreateDTO;
import com.onlineexam.app.dto.request.master.CourseDeleteDTO;
import com.onlineexam.app.dto.request.master.CourseModifyDTO;
import com.onlineexam.app.dto.request.master.DivisionCreateDTO;
import com.onlineexam.app.dto.request.master.DivisionDeleteDTO;
import com.onlineexam.app.dto.request.master.DivisionModifyDTO;
import com.onlineexam.app.dto.request.master.RoleAuthCreateDTO;
import com.onlineexam.app.dto.request.master.RoleAuthModifyDTO;
import com.onlineexam.app.dto.request.master.RoleCreateDTO;
import com.onlineexam.app.dto.request.master.RoleDeleteDTO;
import com.onlineexam.app.dto.request.master.RoleModifyDTO;
import com.onlineexam.app.dto.request.master.SubSubjectCreateDTO;
import com.onlineexam.app.dto.request.master.SubSubjectDeleteDTO;
import com.onlineexam.app.dto.request.master.SubSubjectModifyDTO;
import com.onlineexam.app.dto.request.master.SubjectCreateDTO;
import com.onlineexam.app.dto.request.master.SubjectDeleteDTO;
import com.onlineexam.app.dto.request.master.SubjectModifyDTO;
import com.onlineexam.app.dto.request.master.TopicCreateDTO;
import com.onlineexam.app.dto.request.master.TopicDeleteDTO;
import com.onlineexam.app.dto.request.master.TopicModifyDTO;
import com.onlineexam.app.dto.response.master.ClassDTO;
import com.onlineexam.app.dto.response.master.CourseDTO;
import com.onlineexam.app.dto.response.master.DivisionDTO;
import com.onlineexam.app.dto.response.RoleAuthFetctDTO;
import com.onlineexam.app.dto.response.master.RoleDTO;
import com.onlineexam.app.dto.response.master.SubSubjectDTO;
import com.onlineexam.app.dto.response.master.SubjectDTO;
import com.onlineexam.app.dto.response.master.TopicDTO;
import com.onlineexam.app.service.Dao.IMasterServiceDao;
import com.onlineexam.app.utils.CommonUtility;

@Repository("masterServiceDaoImpl")
public class IMasterServiceDaoImpl implements IMasterServiceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private Environment env;

	@Override
	public List<RoleDTO> getAllRole(int pageIndex, int totalRecords) {
		String query = "";
		if (pageIndex == 0 && totalRecords == 0)
			query = env.getProperty("fetchRoleMasterQuery") + " where rm.active_status=1";
		else
			query = env.getProperty("fetchRoleMasterQuery") + "  limit " + (pageIndex * totalRecords) + " , "
					+ totalRecords;

		return jdbcTemplate.query(query, new RowMapper<RoleDTO>() {
			@Override
			public RoleDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				RoleDTO roleDTO = new RoleDTO();
				roleDTO.setRoleId(rs.getInt("role_id"));
				roleDTO.setRoleName(rs.getString("role_name"));
				roleDTO.setUserName(rs.getString("user_name"));
				roleDTO.setActiveStatus(rs.getInt("active_status"));
				return roleDTO;
			}
		});
	}

	@Override
	@Cacheable(value = "allRoles")
	public List<RoleDTO> getAllRoleCacheData() {
		String query = "";
		query = env.getProperty("fetchRoleMasterQuery");

		return jdbcTemplate.query(query, new RowMapper<RoleDTO>() {
			@Override
			public RoleDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				RoleDTO roleDTO = new RoleDTO();
				roleDTO.setRoleId(rs.getInt("role_id"));
				roleDTO.setRoleName(rs.getString("role_name"));
				roleDTO.setUserName(rs.getString("user_name"));
				roleDTO.setActiveStatus(rs.getInt("active_status"));
				return roleDTO;
			}
		});
	}

	@Override
	public Integer getTotalRoleCount() {
		String countQuery = env.getProperty("countRoleMasterQuery");
		return jdbcTemplate.queryForObject(countQuery, Integer.class);
	}

	@Override
	public Integer findRoleCountByRoleName(String roleName) throws SQLException {
		String checkDublicateQuery = env.getProperty("countForRoleDuplicacyQuery");
		return jdbcTemplate.queryForObject(checkDublicateQuery, new Object[] { roleName }, new int[] { Types.VARCHAR },
				Integer.class);

	}

	@Override
	@CacheEvict(value = "allRoles", allEntries = true)
	public int saveRole(RoleCreateDTO roleCreateDTO) throws SQLException {
		String saveRoleMasterQuery = env.getProperty("saveRoleQuery");
		return jdbcTemplate.update(saveRoleMasterQuery,
				new Object[] { roleCreateDTO.getRoleName(), roleCreateDTO.getUserId() });
	}

	@Override
	@CacheEvict(value = "allRoles", allEntries = true)
	public int updateRole(RoleModifyDTO roleModifyDTO) throws SQLException {
		String updateRoleMasterQuery = env.getProperty("updateRoleQuery");
		return jdbcTemplate.update(updateRoleMasterQuery, roleModifyDTO.getRoleName(), roleModifyDTO.getUserId(),
				CommonUtility.getCurrentDateTime(), roleModifyDTO.getRoleId());
	}

	@Override
	@CacheEvict(value = "allRoles", allEntries = true)
	public int deleteRole(RoleDeleteDTO roleDeleteDTO) throws SQLException {
		String updateRoleMasterQuery = env.getProperty("deleteRoleQuery");
		return jdbcTemplate.update(updateRoleMasterQuery, roleDeleteDTO.getActiveStatus(), roleDeleteDTO.getUserId(),
				CommonUtility.getCurrentDateTime(), roleDeleteDTO.getRoleId());
	}

	@Override
	public List<RoleAuthFetctDTO> fetchRoleAuthByRoleId(int roleId) {
		String query = env.getProperty("fetchRoleAuthByRoleQuery");
		return jdbcTemplate.query(query, new Object[] { roleId }, new int[] { Types.BIGINT },
				new RowMapper<RoleAuthFetctDTO>() {

					@Override
					public RoleAuthFetctDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						RoleAuthFetctDTO roleAuthFetchDTO = new RoleAuthFetctDTO();
						roleAuthFetchDTO.setMenuId(rs.getInt("menu_id"));
						roleAuthFetchDTO.setParent(rs.getString("menu_name"));
						roleAuthFetchDTO.setMenuParentId(rs.getInt("menu_parent_id"));
						roleAuthFetchDTO.setRoleAuthId(rs.getInt("authorization_id"));
						roleAuthFetchDTO.setEditRight(rs.getInt("edit_rights"));
						roleAuthFetchDTO.setViewRight(rs.getInt("view_rights"));
						roleAuthFetchDTO.setCreateRight(rs.getInt("create_rights"));
						roleAuthFetchDTO.setDeleteRight(rs.getInt("delete_rights"));

						return roleAuthFetchDTO;
					}

				});

	}

	@Override
	public int[] updateRoleAuth(List<RoleAuthModifyDTO> roleAuthModifyDTOList) {
		String query = env.getProperty("roleAuthUpdateQuery");
		return jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, roleAuthModifyDTOList.get(i).getEditRight());
				ps.setInt(2, roleAuthModifyDTOList.get(i).getViewRight());
				ps.setInt(3, roleAuthModifyDTOList.get(i).getCreateRight());
				ps.setInt(4, roleAuthModifyDTOList.get(i).getDeleteRight());
				ps.setLong(5, roleAuthModifyDTOList.get(i).getUpdatedBy());
				ps.setInt(6, roleAuthModifyDTOList.get(i).getRoleAuthId());

			}

			@Override
			public int getBatchSize() {
				return roleAuthModifyDTOList.size();
			}
		});

	}

	@Override
	public int[] createRoleAuth(List<RoleAuthCreateDTO> roleAuthCreateDTODTOList) {
		String query = env.getProperty("roleAuthCreateQuery");
		return jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, roleAuthCreateDTODTOList.get(i).getEditRight());
				ps.setInt(2, roleAuthCreateDTODTOList.get(i).getViewRight());
				ps.setInt(3, roleAuthCreateDTODTOList.get(i).getCreateRight());
				ps.setInt(4, roleAuthCreateDTODTOList.get(i).getDeleteRight());
				ps.setInt(5, roleAuthCreateDTODTOList.get(i).getCreatedBy());
				ps.setInt(6, roleAuthCreateDTODTOList.get(i).getCreatedBy());
				ps.setLong(7, roleAuthCreateDTODTOList.get(i).getMenuId());
				ps.setLong(8, roleAuthCreateDTODTOList.get(i).getRoleId());

			}

			@Override
			public int getBatchSize() {
				return roleAuthCreateDTODTOList.size();
			}
		});

	}

	@Override
	public List<SubjectDTO> getAllSubjects(int pageIndex, int totalRecords) {
		String query = "";
		if (pageIndex == 0 && totalRecords == 0)
			query = env.getProperty("fetchSubjectMasterQuery") + " where sm.active_status=1";
		else
			query = env.getProperty("fetchSubjectMasterQuery") + "  limit " + (pageIndex * totalRecords) + " , "
					+ totalRecords;
		return jdbcTemplate.query(query, new RowMapper<SubjectDTO>() {
			@Override
			public SubjectDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				SubjectDTO subjectDTO = new SubjectDTO();
				CourseDTO courseDTO = new CourseDTO();
				courseDTO.setCourseId(rs.getLong("course_id"));
				courseDTO.setCourseName(rs.getString("course_name"));
				DivisionDTO divisionDTO = new DivisionDTO();
				divisionDTO.setDivisionId(rs.getLong("division_id"));
				divisionDTO.setDivisionName(rs.getString("division_name"));
				divisionDTO.setCourseDTO(courseDTO);
				subjectDTO.setDivisionDTO(divisionDTO);
				ClassDTO classDTO = new ClassDTO();
				classDTO.setClassId(rs.getLong("class_id"));
				classDTO.setClassName(rs.getString("class_name"));
				classDTO.setDivisionDTO(divisionDTO);
				subjectDTO.setClassDTO(classDTO);
				subjectDTO.setCourseDTO(courseDTO);
				subjectDTO.setSubjectId(rs.getInt("subject_id"));
				subjectDTO.setSubjectName(rs.getString("subject_name"));
				subjectDTO.setSubjectCode(rs.getString("subject_code"));
				subjectDTO.setRules(rs.getString("rules"));
				subjectDTO.setTotalMarks(rs.getLong("total_marks"));
				subjectDTO.setPassMarks(rs.getLong("pass_marks"));
				subjectDTO.setExamDuration(rs.getLong("exam_duration"));
				subjectDTO.setUserName(rs.getString("user_name"));
				subjectDTO.setActiveStatus(rs.getInt("active_status"));
				return subjectDTO;
			}
		});
	}

	@Override
	@Cacheable(value = "allSubjects")
	public List<SubjectDTO> getAllSubjectsCacheData() {
		String query = "";
		query = env.getProperty("fetchSubjectMasterQuery");
		return jdbcTemplate.query(query, new RowMapper<SubjectDTO>() {
			@Override
			public SubjectDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				SubjectDTO subjectDTO = new SubjectDTO();
				CourseDTO courseDTO = new CourseDTO();
				courseDTO.setCourseId(rs.getLong("course_id"));
				courseDTO.setCourseName(rs.getString("course_name"));
				subjectDTO.setCourseDTO(courseDTO);
				subjectDTO.setSubjectId(rs.getInt("subject_id"));
				subjectDTO.setSubjectName(rs.getString("subject_name"));
				subjectDTO.setSubjectCode(rs.getString("subject_code"));
				subjectDTO.setRules(rs.getString("rules"));
				subjectDTO.setTotalMarks(rs.getLong("total_marks"));
				subjectDTO.setPassMarks(rs.getLong("pass_marks"));
				subjectDTO.setExamDuration(rs.getLong("exam_duration"));
				subjectDTO.setUserName(rs.getString("user_name"));
				subjectDTO.setActiveStatus(rs.getInt("active_status"));
				return subjectDTO;
			}
		});
	}

	@Override
	public Integer getTotalSubjectCount() {
		String countQuery = env.getProperty("countSubjectMasterQuery");
		return jdbcTemplate.queryForObject(countQuery, Integer.class);
	}

	@Override
	public Integer findSubjectCountBySubjectName(String subjectName) throws SQLException {
		String checkDublicateQuery = env.getProperty("countForSubjectDuplicacyQuery");
		return jdbcTemplate.queryForObject(checkDublicateQuery, new Object[] { subjectName },
				new int[] { Types.VARCHAR }, Integer.class);
	}

	@Override
	@CacheEvict(value = "allSubjects", allEntries = true)
	public int saveSubject(SubjectCreateDTO subjectCreateDTO) throws SQLException {
		String saveSubjectMasterQuery = env.getProperty("saveSubjectQuery");
		return jdbcTemplate.update(saveSubjectMasterQuery, new Object[] { subjectCreateDTO.getSubjectCode(),
				subjectCreateDTO.getCourseId(), subjectCreateDTO.getDivisionId(), subjectCreateDTO.getClassId(),
				subjectCreateDTO.getSubjectName(), subjectCreateDTO.getRules(), subjectCreateDTO.getTotalMarks(),
				subjectCreateDTO.getPassMarks(), subjectCreateDTO.getExamDuration(), subjectCreateDTO.getUserId() });
	}

	@Override
	@CacheEvict(value = "allSubjects", allEntries = true)
	public int updateSubject(SubjectModifyDTO subjectModifyDTO) throws SQLException {
		String updateSubjectMasterQuery = env.getProperty("updateSubjectQuery");
		return jdbcTemplate.update(updateSubjectMasterQuery, subjectModifyDTO.getSubjectCode(),
				subjectModifyDTO.getCourseId(), subjectModifyDTO.getDivisionId(), subjectModifyDTO.getClassId(),
				subjectModifyDTO.getSubjectName(), subjectModifyDTO.getRules(), subjectModifyDTO.getTotalMarks(),
				subjectModifyDTO.getPassMarks(), subjectModifyDTO.getExamDuration(), subjectModifyDTO.getUserId(),
				CommonUtility.getCurrentDateTime(), subjectModifyDTO.getSubjectId());
	}

	@Override
	@CacheEvict(value = "allSubjects", allEntries = true)
	public int deleteSubject(SubjectDeleteDTO subjectDeleteDTO) throws SQLException {
		String updateSubjectMasterQuery = env.getProperty("deleteSubjectQuery");
		return jdbcTemplate.update(updateSubjectMasterQuery, subjectDeleteDTO.getActiveStatus(),
				subjectDeleteDTO.getUserId(), CommonUtility.getCurrentDateTime(), subjectDeleteDTO.getSubjectId());
	}

	@Override
	public List<CourseDTO> getAllCourse(int pageIndex, int totalRecords) {
		String query = "";
		if (pageIndex == 0 && totalRecords == 0)
			query = env.getProperty("fetchCourseMasterQuery") + " where cm.active_status=1";
		else
			query = env.getProperty("fetchCourseMasterQuery") + "  limit " + (pageIndex * totalRecords) + " , "
					+ totalRecords;
		return jdbcTemplate.query(query, new RowMapper<CourseDTO>() {
			@Override
			public CourseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseDTO courseDTO = new CourseDTO();
				courseDTO.setCourseId(rs.getLong("course_id"));
				courseDTO.setCourseName(rs.getString("course_name"));
				courseDTO.setCourseDescription(rs.getString("course_description"));
				courseDTO.setActiveStatus(rs.getInt("active_status"));
				courseDTO.setUserName(rs.getString("user_name"));
				return courseDTO;
			}
		});
	}

	@Override
	@Cacheable(value = "allCourses")
	public List<CourseDTO> getAllCourseCacheData() {
		String query = "";
		query = env.getProperty("fetchCourseMasterQuery");
		return jdbcTemplate.query(query, new RowMapper<CourseDTO>() {
			@Override
			public CourseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseDTO courseDTO = new CourseDTO();
				courseDTO.setCourseId(rs.getLong("course_id"));
				courseDTO.setCourseName(rs.getString("course_name"));
				courseDTO.setCourseDescription(rs.getString("course_description"));
				courseDTO.setActiveStatus(rs.getInt("active_status"));
				courseDTO.setUserName(rs.getString("user_name"));
				return courseDTO;
			}
		});
	}

	@Override
	public Integer getTotalClassCount() {
		String countQuery = env.getProperty("countSubjectMasterQuery");
		return jdbcTemplate.queryForObject(countQuery, Integer.class);
	}

	@Override
	public Integer findCourseCountByCourseName(String courseName) throws SQLException {
		String checkDublicateQuery = env.getProperty("countForSubjectDuplicacyQuery");
		return jdbcTemplate.queryForObject(checkDublicateQuery, new Object[] { courseName },
				new int[] { Types.VARCHAR }, Integer.class);
	}

	@Override
	@CacheEvict(value = "allCourses", allEntries = true)
	public int saveCourse(CourseCreateDTO courseCreateDTO) throws SQLException {
		String saveCourseMasterQuery = env.getProperty("saveCourseQuery");
		return jdbcTemplate.update(saveCourseMasterQuery, new Object[] { courseCreateDTO.getCourseName(),
				courseCreateDTO.getCourseDescription(), courseCreateDTO.getUserId() });
	}

	@Override
	@CacheEvict(value = "allCourses", allEntries = true)
	public int updateCourse(CourseModifyDTO courseModifyDTO) throws SQLException {
		String updateCourseMasterQuery = env.getProperty("updateCourseQuery");
		return jdbcTemplate.update(updateCourseMasterQuery, courseModifyDTO.getCourseName(),
				courseModifyDTO.getCourseDescription(), courseModifyDTO.getUserId(), CommonUtility.getCurrentDateTime(),
				courseModifyDTO.getCourseId());
	}

	@Override
	@CacheEvict(value = "allCourses", allEntries = true)
	public int deleteCourse(CourseDeleteDTO courseDeleteDTO) throws SQLException {
		String updateCourseMasterQuery = env.getProperty("deleteCourseQuery");
		return jdbcTemplate.update(updateCourseMasterQuery, courseDeleteDTO.getActiveStatus(),
				courseDeleteDTO.getUserId(), CommonUtility.getCurrentDateTime(), courseDeleteDTO.getCourseId());
	}

	@Override
	public Integer getTotalCourseCount() {
		String countQuery = env.getProperty("countCourseMasterQuery");
		return jdbcTemplate.queryForObject(countQuery, Integer.class);
	}

	@Override
	public List<DivisionDTO> getAllDivision(int pageIndex, int totalRecords, Map<String, String> filters) {
		StringBuilder query = new StringBuilder();
		if (filters == null) {
			if (pageIndex == 0 && totalRecords == 0)
				query.append(env.getProperty("fetchDivisionMasterQuery")).append(" where dm.active_status=1");
			else
				query.append(env.getProperty("fetchDivisionMasterQuery"))
						.append("  limit " + (pageIndex * totalRecords) + " , ").append(totalRecords);
		} else {
			query.append(env.getProperty("fetchDivisionMasterQuery")).append(" where dm.active_status=1 ");
			filters.forEach((key, value) -> {
				if (key.equals("courseId")) {
					query.append(" and dm.course_id =").append(value);
				}
			});
		}
		return jdbcTemplate.query(query.toString(), new RowMapper<DivisionDTO>() {
			@Override
			public DivisionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				DivisionDTO divisionDTO = new DivisionDTO();
				divisionDTO.setDivisionId(rs.getLong("division_id"));
				divisionDTO.setDivisionName(rs.getString("division_name"));
				divisionDTO.setDivisionDescription(rs.getString("division_description"));
				CourseDTO courseDTO = new CourseDTO();
				courseDTO.setCourseId(rs.getLong("course_id"));
				courseDTO.setCourseName(rs.getString("course_name"));
				divisionDTO.setCourseDTO(courseDTO);
				divisionDTO.setActiveStatus(rs.getInt("active_status"));
				divisionDTO.setUserName(rs.getString("user_name"));
				return divisionDTO;
			}
		});
	}

	@Override
	@Cacheable(value = "allDivisions")
	public List<DivisionDTO> getAllDivisionCacheData() {
		String query = "";
		query = env.getProperty("fetchDivisionMasterQuery");
		return jdbcTemplate.query(query, new RowMapper<DivisionDTO>() {
			@Override
			public DivisionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				DivisionDTO divisionDTO = new DivisionDTO();
				divisionDTO.setDivisionId(rs.getLong("division_id"));
				divisionDTO.setDivisionName(rs.getString("division_name"));
				divisionDTO.setDivisionDescription(rs.getString("division_description"));
				CourseDTO courseDTO = new CourseDTO();
				courseDTO.setCourseId(rs.getLong("course_id"));
				courseDTO.setCourseName(rs.getString("course_name"));
				divisionDTO.setCourseDTO(courseDTO);
				divisionDTO.setActiveStatus(rs.getInt("active_status"));
				divisionDTO.setUserName(rs.getString("user_name"));
				return divisionDTO;
			}
		});
	}

	@Override
	public Integer getTotalDivisionCount() {
		String countQuery = env.getProperty("countDivisionMasterQuery");
		return jdbcTemplate.queryForObject(countQuery, Integer.class);
	}

	@Override
	public Integer findDivisionCountByDivisionName(String divisionName) throws SQLException {
		String checkDublicateQuery = env.getProperty("countForDivisionDuplicacyQuery");
		return jdbcTemplate.queryForObject(checkDublicateQuery, new Object[] { divisionName },
				new int[] { Types.VARCHAR }, Integer.class);
	}

	@Override
	@CacheEvict(value = "allDivisions", allEntries = true)
	public int saveDivision(DivisionCreateDTO divisionCreateDTO) throws SQLException {
		String saveCourseMasterQuery = env.getProperty("saveDivisionQuery");
		return jdbcTemplate.update(saveCourseMasterQuery,
				new Object[] { divisionCreateDTO.getDivisionName(), divisionCreateDTO.getDivisionDescription(),
						divisionCreateDTO.getCourseId(), divisionCreateDTO.getUserId() });
	}

	@Override
	@CacheEvict(value = "allDivisions", allEntries = true)
	public int updateDivision(DivisionModifyDTO divisionModifyDTO) throws SQLException {
		String updateCourseMasterQuery = env.getProperty("updateDivisionQuery");
		return jdbcTemplate.update(updateCourseMasterQuery, divisionModifyDTO.getDivisionName(),
				divisionModifyDTO.getDivisionDescription(), divisionModifyDTO.getCourseId(),
				divisionModifyDTO.getUserId(), CommonUtility.getCurrentDateTime(), divisionModifyDTO.getDivisionId());
	}

	@Override
	@CacheEvict(value = "allDivisions", allEntries = true)
	public int deleteDivision(DivisionDeleteDTO divisionDeleteDTO) throws SQLException {
		String updateCourseMasterQuery = env.getProperty("deleteDivisionQuery");
		return jdbcTemplate.update(updateCourseMasterQuery, divisionDeleteDTO.getActiveStatus(),
				divisionDeleteDTO.getUserId(), CommonUtility.getCurrentDateTime(), divisionDeleteDTO.getDivisionId());
	}

	@Override
	public List<ClassDTO> getAllClass(int pageIndex, int totalRecords, Map<String, String> filters)
			throws SQLException {
		StringBuilder query = new StringBuilder();
		if (filters == null) {
			if (pageIndex == 0 && totalRecords == 0)
				query.append(env.getProperty("fetchClassMasterQuery")).append(" where cm.active_status=1");
			else
				query.append(env.getProperty("fetchClassMasterQuery"))
						.append("  limit " + (pageIndex * totalRecords) + " , ").append(totalRecords);
		} else {
			query.append(env.getProperty("fetchClassMasterQuery")).append(" where cm.active_status=1 ");
			filters.forEach((key, value) -> {
				if (key.equals("divisionId")) {
					query.append(" and cm.division_id =").append(value);
				}
			});
		}
		return jdbcTemplate.query(query.toString(), new RowMapper<ClassDTO>() {
			@Override
			public ClassDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClassDTO classDTO = new ClassDTO();
				classDTO.setClassId(rs.getLong("class_id"));
				classDTO.setClassName(rs.getString("class_name"));
				classDTO.setClassDescription(rs.getString("class_description"));
				CourseDTO courseDTO = new CourseDTO();
				courseDTO.setCourseId(rs.getLong("course_id"));
				courseDTO.setCourseName(rs.getString("course_name"));
				DivisionDTO divisionDTO = new DivisionDTO();
				divisionDTO.setDivisionId(rs.getLong("division_id"));
				divisionDTO.setDivisionName(rs.getString("division_name"));
				divisionDTO.setCourseDTO(courseDTO);
				classDTO.setCourseDTO(courseDTO);
				classDTO.setDivisionDTO(divisionDTO);
				classDTO.setActiveStatus(rs.getInt("active_status"));
				classDTO.setUserName(rs.getString("user_name"));
				return classDTO;
			}
		});
	}

	@Override
	@Cacheable(value = "allClasses")
	public List<ClassDTO> getAllClassCacheData() {
		String query = "";
		query = env.getProperty("fetchClassMasterQuery");
		return jdbcTemplate.query(query, new RowMapper<ClassDTO>() {
			@Override
			public ClassDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClassDTO classDTO = new ClassDTO();
				classDTO.setClassId(rs.getLong("class_id"));
				classDTO.setClassName(rs.getString("class_name"));
				classDTO.setClassDescription(rs.getString("class_description"));
				CourseDTO courseDTO = new CourseDTO();
				courseDTO.setCourseId(rs.getLong("course_id"));
				courseDTO.setCourseName(rs.getString("course_name"));
				DivisionDTO divisionDTO = new DivisionDTO();
				divisionDTO.setDivisionId(rs.getLong("division_id"));
				divisionDTO.setDivisionName(rs.getString("division_name"));
				divisionDTO.setCourseDTO(courseDTO);
				classDTO.setCourseDTO(courseDTO);
				classDTO.setDivisionDTO(divisionDTO);
				classDTO.setActiveStatus(rs.getInt("active_status"));
				classDTO.setUserName(rs.getString("user_name"));
				return classDTO;
			}
		});
	}

	@Override
	public Integer findClassCountByClassName(String className) throws SQLException {
		String checkDublicateQuery = env.getProperty("countForClassDuplicacyQuery");
		return jdbcTemplate.queryForObject(checkDublicateQuery, new Object[] { className }, new int[] { Types.VARCHAR },
				Integer.class);
	}

	@Override
	@CacheEvict(value = "allClasses", allEntries = true)
	public int saveClass(ClassCreateDTO classCreateDTO) throws SQLException {
		String saveClassMasterQuery = env.getProperty("saveClassQuery");
		return jdbcTemplate.update(saveClassMasterQuery,
				new Object[] { classCreateDTO.getClassName(), classCreateDTO.getClassDescription(),
						classCreateDTO.getCourseId(), classCreateDTO.getDivisionId(), classCreateDTO.getUserId() });
	}

	@Override
	@CacheEvict(value = "allClasses", allEntries = true)
	public int updateClass(ClassModifyDTO classModifyDTO) throws SQLException {
		String updateClassMasterQuery = env.getProperty("updateClassQuery");
		return jdbcTemplate.update(updateClassMasterQuery, classModifyDTO.getClassName(),
				classModifyDTO.getClassDescription(), classModifyDTO.getCourseId(), classModifyDTO.getDivisionId(),
				classModifyDTO.getUserId(), CommonUtility.getCurrentDateTime(), classModifyDTO.getClassId());
	}

	@Override
	@CacheEvict(value = "allClasses", allEntries = true)
	public int deleteClass(ClassDeleteDTO classDeleteDTO) throws SQLException {
		String updateClassMasterQuery = env.getProperty("deleteClassQuery");
		return jdbcTemplate.update(updateClassMasterQuery, classDeleteDTO.getActiveStatus(), classDeleteDTO.getUserId(),
				CommonUtility.getCurrentDateTime(), classDeleteDTO.getClassId());
	}

	@Override
	public List<SubSubjectDTO> getAllSubSubjects(int pageIndex, int totalRecords, Map<String, String> filters) {
		StringBuilder query = new StringBuilder();
		if (filters == null) {
			if (pageIndex == 0 && totalRecords == 0)
				query.append(env.getProperty("fetchSubSubjectMasterQuery")).append(" where ssm.active_status=1");
			else
				query.append(env.getProperty("fetchSubSubjectMasterQuery"))
						.append("  limit " + (pageIndex * totalRecords) + " , ").append(totalRecords);
		} else {
			query.append(env.getProperty("fetchSubSubjectMasterQuery")).append(" where ssm.active_status=1 ");
			filters.forEach((key, value) -> {
				if (key.equals("subjectId")) {
					query.append(" and ssm.subject_id =").append(value);
				}
			});
		}
		return jdbcTemplate.query(query.toString(), new RowMapper<SubSubjectDTO>() {
			@Override
			public SubSubjectDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				SubSubjectDTO subSubjectDTO = new SubSubjectDTO();
				SubjectDTO subjectDTO = new SubjectDTO();
				subjectDTO.setSubjectId(rs.getLong("subject_id"));
				subjectDTO.setSubjectName(rs.getString("subject_name"));
				subjectDTO.setSubjectCode(rs.getString("subject_code"));
				subSubjectDTO.setSubjectDTO(subjectDTO);
				subSubjectDTO.setSubSubjectId(rs.getLong("sub_subject_id"));
				subSubjectDTO.setSubSubjectCode(rs.getString("sub_subject_code"));
				subSubjectDTO.setSubSubjectName(rs.getString("sub_subject_name"));
				subSubjectDTO.setUserName(rs.getString("user_name"));
				subSubjectDTO.setActiveStatus(rs.getInt("active_status"));
				return subSubjectDTO;
			}
		});
	}

	@Override
	@Cacheable(value = "allSubSubjects")
	public List<SubSubjectDTO> getAllSubSubjectsCacheData() {
		StringBuilder query = new StringBuilder();
		query.append(env.getProperty("fetchSubSubjectMasterQuery"));
		return jdbcTemplate.query(query.toString(), new RowMapper<SubSubjectDTO>() {
			@Override
			public SubSubjectDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				SubSubjectDTO subSubjectDTO = new SubSubjectDTO();
				SubjectDTO subjectDTO = new SubjectDTO();
				subjectDTO.setSubjectId(rs.getLong("subject_id"));
				subjectDTO.setSubjectName(rs.getString("subject_name"));
				subjectDTO.setSubjectCode(rs.getString("subject_code"));
				subSubjectDTO.setSubjectDTO(subjectDTO);
				subSubjectDTO.setSubSubjectId(rs.getLong("sub_subject_id"));
				subSubjectDTO.setSubSubjectCode(rs.getString("sub_subject_code"));
				subSubjectDTO.setSubSubjectName(rs.getString("sub_subject_name"));
				subSubjectDTO.setUserName(rs.getString("user_name"));
				subSubjectDTO.setActiveStatus(rs.getInt("active_status"));
				return subSubjectDTO;
			}
		});
	}

	@Override
	public Integer getTotalSubSubjectCount() {
		String countQuery = env.getProperty("countSubSubjectMasterQuery");
		return jdbcTemplate.queryForObject(countQuery, Integer.class);
	}

	@Override
	public Integer findSubSubjectCountBySubSubjectName(String subSubjectName) throws SQLException {
		String checkDublicateQuery = env.getProperty("countForSubSubjectDuplicacyQuery");
		return jdbcTemplate.queryForObject(checkDublicateQuery, new Object[] { subSubjectName },
				new int[] { Types.VARCHAR }, Integer.class);
	}

	@Override
	@CacheEvict(value = "allSubSubjects", allEntries = true)
	public int saveSubSubject(SubSubjectCreateDTO subSubjectCreateDTO) throws SQLException {
		String saveSubSubjectMasterQuery = env.getProperty("saveSubSubjectQuery");
		return jdbcTemplate.update(saveSubSubjectMasterQuery,
				new Object[] { subSubjectCreateDTO.getSubjectId(), subSubjectCreateDTO.getSubSubjectCode(),
						subSubjectCreateDTO.getSubSubjectName(), subSubjectCreateDTO.getUserId() });
	}

	@Override
	@CacheEvict(value = "allSubSubjects", allEntries = true)
	public int updateSubSubject(SubSubjectModifyDTO subSubjectModifyDTO) throws SQLException {
		String updateSubSubjectMasterQuery = env.getProperty("updateSubSubjectQuery");
		return jdbcTemplate.update(updateSubSubjectMasterQuery, subSubjectModifyDTO.getSubjectId(),
				subSubjectModifyDTO.getSubSubjectCode(), subSubjectModifyDTO.getSubSubjectName(),
				subSubjectModifyDTO.getUserId(), CommonUtility.getCurrentDateTime(),
				subSubjectModifyDTO.getSubSubjectId());
	}

	@Override
	@CacheEvict(value = "allSubSubjects", allEntries = true)
	public int deleteSubSubject(SubSubjectDeleteDTO subSubjectDeleteDTO) throws SQLException {
		String deleteSubSubjectMasterQuery = env.getProperty("deleteSubSubjectQuery");
		return jdbcTemplate.update(deleteSubSubjectMasterQuery, subSubjectDeleteDTO.getActiveStatus(),
				subSubjectDeleteDTO.getUserId(), CommonUtility.getCurrentDateTime(),
				subSubjectDeleteDTO.getSubSubjectId());
	}

	@Override
	@Cacheable(value = "allTopics")
	public List<TopicDTO> getAllTopicsCacheData() {
		StringBuilder query = new StringBuilder();
		query.append(env.getProperty("fetchTopicMasterQuery"));
		return jdbcTemplate.query(query.toString(), new RowMapper<TopicDTO>() {
			@Override
			public TopicDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				TopicDTO topicDTO = new TopicDTO();
				SubSubjectDTO subSubjectDTO = new SubSubjectDTO();
				subSubjectDTO.setSubSubjectId(rs.getLong("sub_subject_id"));
				subSubjectDTO.setSubSubjectCode(rs.getString("sub_subject_code"));
				subSubjectDTO.setSubSubjectName(rs.getString("sub_subject_name"));
				SubjectDTO subjectDTO = new SubjectDTO();
				subjectDTO.setSubjectId(rs.getLong("subject_id"));
				subjectDTO.setSubjectName(rs.getString("subject_name"));
				subjectDTO.setSubjectCode(rs.getString("subject_code"));
				subSubjectDTO.setSubjectDTO(subjectDTO);
				topicDTO.setSubSubjectDTO(subSubjectDTO);
				topicDTO.setSubjectDTO(subjectDTO);
				topicDTO.setTopicId(rs.getLong("topic_id"));
				topicDTO.setTopicCode(rs.getString("topic_code"));
				topicDTO.setTopicName(rs.getString("topic_name"));
				topicDTO.setMaxNumberofQuestions(rs.getInt("max_no_questions"));
				topicDTO.setUserName(rs.getString("user_name"));
				topicDTO.setActiveStatus(rs.getInt("active_status"));
				return topicDTO;
			}
		});
	}

	@Override
	public List<TopicDTO> getAllTopics(int pageIndex, int totalRecords, Map<String, String> filters) {
		StringBuilder query = new StringBuilder();
		if (filters == null) {
			if (pageIndex == 0 && totalRecords == 0)
				query.append(env.getProperty("fetchTopicMasterQuery")).append(" where tm.active_status=1");
			else
				query.append(env.getProperty("fetchTopicMasterQuery"))
						.append("  limit " + (pageIndex * totalRecords) + " , ").append(totalRecords);
		} else {
			query.append(env.getProperty("fetchTopicMasterQuery")).append(" where tm.active_status=1 ");
			filters.forEach((key, value) -> {
				if (key.equals("subjectId")) {
					query.append(" AND tm.subject_id =").append(value);
				}
			});
		}
		return jdbcTemplate.query(query.toString(), new RowMapper<TopicDTO>() {
			@Override
			public TopicDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				TopicDTO topicDTO = new TopicDTO();
				SubSubjectDTO subSubjectDTO = new SubSubjectDTO();
				subSubjectDTO.setSubSubjectId(rs.getLong("sub_subject_id"));
				subSubjectDTO.setSubSubjectCode(rs.getString("sub_subject_code"));
				subSubjectDTO.setSubSubjectName(rs.getString("sub_subject_name"));
				SubjectDTO subjectDTO = new SubjectDTO();
				subjectDTO.setSubjectId(rs.getLong("subject_id"));
				subjectDTO.setSubjectName(rs.getString("subject_name"));
				subjectDTO.setSubjectCode(rs.getString("subject_code"));
				subSubjectDTO.setSubjectDTO(subjectDTO);
				topicDTO.setSubSubjectDTO(subSubjectDTO);
				topicDTO.setSubjectDTO(subjectDTO);
				topicDTO.setTopicId(rs.getLong("topic_id"));
				topicDTO.setTopicCode(rs.getString("topic_code"));
				topicDTO.setTopicName(rs.getString("topic_name"));
				topicDTO.setMaxNumberofQuestions(rs.getInt("max_no_questions"));
				topicDTO.setUserName(rs.getString("user_name"));
				topicDTO.setActiveStatus(rs.getInt("active_status"));
				return topicDTO;
			}
		});
	}

	@Override
	public Integer getTotalTopicCount() {
		String countQuery = env.getProperty("countTopicMasterQuery");
		return jdbcTemplate.queryForObject(countQuery, Integer.class);
	}

	@Override
	public Integer findTopicCountByTopicName(String topicName) throws SQLException {
		String checkDublicateQuery = env.getProperty("countForTopicDuplicacyQuery");
		return jdbcTemplate.queryForObject(checkDublicateQuery, new Object[] { topicName }, new int[] { Types.VARCHAR },
				Integer.class);
	}

	@Override
	@CacheEvict(value = "allTopics", allEntries = true)
	public int saveTopic(TopicCreateDTO topicCreateDTO) throws SQLException {
		String saveTopicMasterQuery = env.getProperty("saveTopicQuery");
		return jdbcTemplate.update(saveTopicMasterQuery,
				new Object[] { topicCreateDTO.getSubjectId(), topicCreateDTO.getSubSubjectId(),
						topicCreateDTO.getTopicCode(), topicCreateDTO.getTopicName(),
						topicCreateDTO.getMaxNumberofQuestions(), topicCreateDTO.getUserId() });
	}

	@Override
	@CacheEvict(value = "allTopics", allEntries = true)
	public int updateTopic(TopicModifyDTO topicModifyDTO) throws SQLException {
		String updateTopicMasterQuery = env.getProperty("updateTopicQuery");
		return jdbcTemplate.update(updateTopicMasterQuery, topicModifyDTO.getTopicCode(), topicModifyDTO.getTopicName(),
				topicModifyDTO.getMaxNumberofQuestions(), topicModifyDTO.getUserId(),
				CommonUtility.getCurrentDateTime(), topicModifyDTO.getTopicId());
	}

	@Override
	@CacheEvict(value = "allTopics", allEntries = true)
	public int deleteTopic(TopicDeleteDTO topicDeleteDTO) throws SQLException {
		String deleteTopicMasterQuery = env.getProperty("deleteTopicQuery");
		return jdbcTemplate.update(deleteTopicMasterQuery, topicDeleteDTO.getActiveStatus(), topicDeleteDTO.getUserId(),
				CommonUtility.getCurrentDateTime(), topicDeleteDTO.getTopicId());
	}
}