package com.onlineexam.app.service;

import java.util.List;
import java.util.Map;

import com.onlineexam.app.dto.ServiceResponseDTO;
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

public interface IMasterService {
	ServiceResponseDTO getAllRole(int pageIndex, int totalNumberOfRecords);

	ServiceResponseDTO saveRoles(RoleCreateDTO roleCreateDTO);

	ServiceResponseDTO updateRoles(RoleModifyDTO roleModifyDTO);

	ServiceResponseDTO deleteRoles(RoleDeleteDTO roleDeleteDTO);

	ServiceResponseDTO fetchRoleAuthByRoleId(int roleId);

	ServiceResponseDTO updateRoleAuth(List<RoleAuthModifyDTO> roleAuthModifyDTOList);

	ServiceResponseDTO createRoleAuth(List<RoleAuthCreateDTO> roleAuthCreateDTOList);

	ServiceResponseDTO getAllSubjects(int pageIndex, int totalNumberOfRecords);

	ServiceResponseDTO saveSubjects(SubjectCreateDTO subjectCreateDTO);

	ServiceResponseDTO updateSubjects(SubjectModifyDTO subjectModifyDTO);

	ServiceResponseDTO deleteSubjects(SubjectDeleteDTO subjectDeleteDTO);

	ServiceResponseDTO getAllCourses(int pageIndex, int totalNumberOfRecords);

	ServiceResponseDTO saveCourses(CourseCreateDTO courseCreateDTO);

	ServiceResponseDTO updateCourses(CourseModifyDTO courseModifyDTO);

	ServiceResponseDTO deleteCourses(CourseDeleteDTO courseDeleteDTO);

	ServiceResponseDTO getAllDivisions(int pageIndex, int totalNumberOfRecords);

	ServiceResponseDTO saveDivisions(DivisionCreateDTO divisionCreateDTO);

	ServiceResponseDTO updateDivisions(DivisionModifyDTO divisionModifyDTO);

	ServiceResponseDTO deleteDivisions(DivisionDeleteDTO divisionDeleteDTO);

	ServiceResponseDTO getAllClasses(int pageIndex, int totalNumberOfRecords);

	ServiceResponseDTO saveClasses(ClassCreateDTO classCreateDTO);

	ServiceResponseDTO updateClasses(ClassModifyDTO classModifyDTO);

	ServiceResponseDTO deleteClasses(ClassDeleteDTO classDeleteDTO);

	ServiceResponseDTO getAllSubSubjects(int pageIndex, int totalNumberOfRecords);

	ServiceResponseDTO saveSubSubjects(SubSubjectCreateDTO subjectCreateDTO);

	ServiceResponseDTO updateSubSubjects(SubSubjectModifyDTO subSubjectModifyDTO);

	ServiceResponseDTO deleteSubSubjects(SubSubjectDeleteDTO subSubjectDeleteDTO);

	ServiceResponseDTO getAllTopics(int pageIndex, int totalNumberOfRecords);

	ServiceResponseDTO saveTopics(TopicCreateDTO topicCreateDTO);

	ServiceResponseDTO updateTopics(TopicModifyDTO topicModifyDTO);

	ServiceResponseDTO deleteTopics(TopicDeleteDTO topicDeleteDTO);

	ServiceResponseDTO getAllSubSubjectsByFilter(Map<String, String> filters, int pageIndex, int totalNumberOfRecords);

	ServiceResponseDTO getAllTopicsByFilter(Map<String, String> filters, int pageIndex, int totalNumberOfRecords);
}
