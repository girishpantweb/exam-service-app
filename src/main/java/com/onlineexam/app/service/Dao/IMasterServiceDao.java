package com.onlineexam.app.service.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

public interface IMasterServiceDao {
	
	List<RoleDTO> getAllRole(int pageIndex, int totalRecords);
	
	List<RoleDTO> getAllRoleCacheData();

	Integer getTotalRoleCount();

	Integer findRoleCountByRoleName(String roleName) throws SQLException;

	int saveRole(RoleCreateDTO roleCreateDTO) throws SQLException;

	int updateRole(RoleModifyDTO roleModifyDTO) throws SQLException;

	int deleteRole(RoleDeleteDTO roleDeleteDTO) throws SQLException;

	List<RoleAuthFetctDTO> fetchRoleAuthByRoleId(int roleId);

	int[] updateRoleAuth(List<RoleAuthModifyDTO> roleAuthModifyDTOList);

	int[] createRoleAuth(List<RoleAuthCreateDTO> roleAuthCreateDTODTOList);

	List<SubjectDTO> getAllSubjects(int pageIndex, int totalRecords);
	
	List<SubjectDTO> getAllSubjectsCacheData();

	Integer getTotalSubjectCount();

	Integer findSubjectCountBySubjectName(String subjectName) throws SQLException;

	int saveSubject(SubjectCreateDTO subjectCreateDTO) throws SQLException;

	int updateSubject(SubjectModifyDTO subjectModifyDTO) throws SQLException;

	int deleteSubject(SubjectDeleteDTO subjectDeleteDTO) throws SQLException;

	List<CourseDTO> getAllCourse(int pageIndex, int totalRecords);

	List<CourseDTO> getAllCourseCacheData();

	Integer getTotalCourseCount();

	Integer findCourseCountByCourseName(String courseName) throws SQLException;

	int saveCourse(CourseCreateDTO courseCreateDTO) throws SQLException;

	int updateCourse(CourseModifyDTO courseModifyDTO) throws SQLException;

	int deleteCourse(CourseDeleteDTO courseDeleteDTO) throws SQLException;

	List<DivisionDTO> getAllDivision(int pageIndex, int totalRecords , Map<String, String> filters);

	List<DivisionDTO> getAllDivisionCacheData();

	Integer getTotalDivisionCount();

	Integer findDivisionCountByDivisionName(String divisionName) throws SQLException;

	int saveDivision(DivisionCreateDTO divisionCreateDTO) throws SQLException;

	int updateDivision(DivisionModifyDTO divisionModifyDTO) throws SQLException;

	int deleteDivision(DivisionDeleteDTO divisionDeleteDTO) throws SQLException;

	List<ClassDTO> getAllClass(int pageIndex, int totalRecords , Map<String, String> filters) throws SQLException;

	List<ClassDTO> getAllClassCacheData();

	Integer getTotalClassCount();

	Integer findClassCountByClassName(String className) throws SQLException;

	int saveClass(ClassCreateDTO classCreateDTO) throws SQLException;

	int updateClass(ClassModifyDTO classModifyDTO) throws SQLException;

	int deleteClass(ClassDeleteDTO classDeleteDTO) throws SQLException;

	List<SubSubjectDTO> getAllSubSubjects(int pageIndex, int totalRecords, Map<String, String> filters);

	List<SubSubjectDTO> getAllSubSubjectsCacheData();

	Integer getTotalSubSubjectCount();

	Integer findSubSubjectCountBySubSubjectName(String subSubjectName) throws SQLException;

	int saveSubSubject(SubSubjectCreateDTO subSubjectCreateDTO) throws SQLException;

	int updateSubSubject(SubSubjectModifyDTO subSubjectModifyDTO) throws SQLException;

	int deleteSubSubject(SubSubjectDeleteDTO subSubjectDeleteDTO) throws SQLException;

	List<TopicDTO> getAllTopics(int pageIndex, int totalRecords, Map<String, String> filters);

	List<TopicDTO> getAllTopicsCacheData();

	Integer getTotalTopicCount();

	Integer findTopicCountByTopicName(String topicName) throws SQLException;

	int saveTopic(TopicCreateDTO topicCreateDTO) throws SQLException;

	int updateTopic(TopicModifyDTO topicModifyDTO) throws SQLException;

	int deleteTopic(TopicDeleteDTO topicDeleteDTO) throws SQLException;
}
