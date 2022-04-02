package com.onlineexam.app.controller;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.onlineexam.app.constants.URLConstants;
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
import com.onlineexam.app.service.IMasterService;

@RestController
@RequestMapping(value = URLConstants.MASTER_SERVICE)
public class MasterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MasterController.class);

	@Autowired
	private IMasterService iMasterService;

	@GetMapping(value = URLConstants.FETCH_ALL_ROLES)
	public ServiceResponseDTO fetchAllRoles(@PathVariable int pageIndex, @PathVariable int totalRecords) {
		LOGGER.info("Executing  fetchAllRoles() method of MasterController");
		return iMasterService.getAllRole(pageIndex, totalRecords);
	}

	@PostMapping(value = URLConstants.SAVES_ROLES)
	public ServiceResponseDTO saveRoleMaster(@RequestBody RoleCreateDTO roleCreateDTO) {
		LOGGER.info("Executing  saveRoleMaster() method of MasterController");
		return iMasterService.saveRoles(roleCreateDTO);
	}

	@PutMapping(value = URLConstants.UPDATE_ROLES)
	public ServiceResponseDTO editRoleMaster(@RequestBody RoleModifyDTO roleModifyDTO) {
		LOGGER.info("Executing  editRoleMaster() method of MasterController");
		return iMasterService.updateRoles(roleModifyDTO);
	}

	@DeleteMapping(value = URLConstants.DELETE_ROLES)
	public ServiceResponseDTO deleteRoleMaster(@RequestBody RoleDeleteDTO roleDeleteDTO) {
		LOGGER.info("Executing  deleteRoleMaster() method of MasterController");
		return iMasterService.deleteRoles(roleDeleteDTO);
	}

	@GetMapping(value = URLConstants.FETCH_ROLEAUTH_BY_ROLE)
	public ServiceResponseDTO fetchAllRoles(@PathVariable int roleId) {
		LOGGER.info("Executing  fetchAllRoles() method of MasterController");
		return iMasterService.fetchRoleAuthByRoleId(roleId);
	}

	@PostMapping(value = URLConstants.CREATE_ROLEAUTH)
	public ServiceResponseDTO createRoleAuth(@RequestBody List<RoleAuthCreateDTO> roleAuthCreateDTOList) {
		LOGGER.info("Executing  editRoleMaster() method of MasterController");
		return iMasterService.createRoleAuth(roleAuthCreateDTOList);
	}

	@PutMapping(value = URLConstants.UPDATE_ROLEAUTH)
	public ServiceResponseDTO updateRoleAuth(@RequestBody List<RoleAuthModifyDTO> roleAuthModifyDTO) {
		LOGGER.info("Executing  updateRoleAuth() method of MasterController");
		return iMasterService.updateRoleAuth(roleAuthModifyDTO);
	}

	@GetMapping(value = URLConstants.FETCH_ALL_SUBJECTS)
	public ServiceResponseDTO fetchAllSubjects(@PathVariable int pageIndex, @PathVariable int totalRecords) {
		LOGGER.info("Executing  fetchAllSubjects() method of MasterController");
		return iMasterService.getAllSubjects(pageIndex, totalRecords);
	}

	@PostMapping(value = URLConstants.SAVES_SUBJECTS)
	public ServiceResponseDTO saveSubjectMaster(@RequestBody SubjectCreateDTO subjectCreateDTO) {
		LOGGER.info("Executing  saveSubjectMaster() method of MasterController");
		return iMasterService.saveSubjects(subjectCreateDTO);
	}

	@PutMapping(value = URLConstants.UPDATE_SUBJECTS)
	public ServiceResponseDTO editSubjectMaster(@RequestBody SubjectModifyDTO subjectModifyDTO) {
		LOGGER.info("Executing  editSubjectMaster() method of MasterController");
		return iMasterService.updateSubjects(subjectModifyDTO);
	}

	@DeleteMapping(value = URLConstants.DELETE_SUBJECTS)
	public ServiceResponseDTO deleteSubjectMaster(@RequestBody SubjectDeleteDTO subjectDeleteDTO) {
		LOGGER.info("Executing  deleteSubjectMaster() method of MasterController");
		return iMasterService.deleteSubjects(subjectDeleteDTO);
	}

	@GetMapping(value = URLConstants.FETCH_ALL_COURSES)
	public ServiceResponseDTO fetchAllCourses(@PathVariable int pageIndex, @PathVariable int totalRecords) {
		LOGGER.info("Executing  fetchAllCourses() method of MasterController");
		return iMasterService.getAllCourses(pageIndex, totalRecords);
	}

	@PostMapping(value = URLConstants.SAVES_COURSES)
	public ServiceResponseDTO saveCoursesMaster(@RequestBody CourseCreateDTO courseCreateDTO) {
		LOGGER.info("Executing  saveCoursesMaster() method of MasterController");
		return iMasterService.saveCourses(courseCreateDTO);
	}

	@PutMapping(value = URLConstants.UPDATE_COURSES)
	public ServiceResponseDTO editCoursesMaster(@RequestBody CourseModifyDTO courseModifyDTO) {
		LOGGER.info("Executing  editCoursesMaster() method of MasterController");
		return iMasterService.updateCourses(courseModifyDTO);
	}

	@DeleteMapping(value = URLConstants.DELETE_COURSES)
	public ServiceResponseDTO deleteCoursesMaster(@RequestBody CourseDeleteDTO courseDeleteDTO) {
		LOGGER.info("Executing  deleteCoursesMaster() method of MasterController");
		return iMasterService.deleteCourses(courseDeleteDTO);
	}

	@GetMapping(value = URLConstants.FETCH_ALL_DIVISIONS)
	public ServiceResponseDTO fetchAllDivisions(@PathVariable int pageIndex, @PathVariable int totalRecords) {
		LOGGER.info("Executing  fetchAllDivisions() method of MasterController");
		return iMasterService.getAllDivisions(pageIndex, totalRecords);
	}

	@PostMapping(value = URLConstants.SAVES_DIVISIONS)
	public ServiceResponseDTO saveDivisionsMaster(@RequestBody DivisionCreateDTO divisionCreateDTO) {
		LOGGER.info("Executing  saveDivisionsMaster() method of MasterController");
		return iMasterService.saveDivisions(divisionCreateDTO);
	}

	@PutMapping(value = URLConstants.UPDATE_DIVISIONS)
	public ServiceResponseDTO editDivisionsMaster(@RequestBody DivisionModifyDTO divisionModifyDTO) {
		LOGGER.info("Executing  editDivisionsMaster() method of MasterController");
		return iMasterService.updateDivisions(divisionModifyDTO);
	}

	@DeleteMapping(value = URLConstants.DELETE_DIVISIONS)
	public ServiceResponseDTO deleteDivisionsMaster(@RequestBody DivisionDeleteDTO divisionDeleteDTO) {
		LOGGER.info("Executing  deleteDivisionsMaster() method of MasterController");
		return iMasterService.deleteDivisions(divisionDeleteDTO);
	}

	@GetMapping(value = URLConstants.FETCH_ALL_CLASSES)
	public ServiceResponseDTO fetchAllClasses(@PathVariable int pageIndex, @PathVariable int totalRecords) {
		LOGGER.info("Executing  fetchAllClasses() method of MasterController");
		return iMasterService.getAllClasses(pageIndex, totalRecords);
	}

	@PostMapping(value = URLConstants.SAVES_CLASSES)
	public ServiceResponseDTO saveClassesMaster(@RequestBody ClassCreateDTO classCreateDTO) {
		LOGGER.info("Executing  saveClassesMaster() method of MasterController");
		return iMasterService.saveClasses(classCreateDTO);
	}

	@PutMapping(value = URLConstants.UPDATE_CLASSES)
	public ServiceResponseDTO editClassesMaster(@RequestBody ClassModifyDTO classModifyDTO) {
		LOGGER.info("Executing  editClassesMaster() method of MasterController");
		return iMasterService.updateClasses(classModifyDTO);
	}

	@DeleteMapping(value = URLConstants.DELETE_CLASSES)
	public ServiceResponseDTO deleteClassesMaster(@RequestBody ClassDeleteDTO classDeleteDTO) {
		LOGGER.info("Executing  deleteClassesMaster() method of MasterController");
		return iMasterService.deleteClasses(classDeleteDTO);
	}

	@GetMapping(value = URLConstants.FETCH_ALL_SUB_SUBJECTS)
	public ServiceResponseDTO fetchAllSubSubjects(@PathVariable int pageIndex, @PathVariable int totalRecords) {
		LOGGER.info("Executing  fetchAllSubjects() method of MasterController");
		return iMasterService.getAllSubSubjects(pageIndex, totalRecords);
	}

	@PostMapping(value = URLConstants.FETCH_ALL_SUB_SUBJECTS_BY_FILTER)
	public ServiceResponseDTO fetchAllSubSubjectsByFilters(@RequestBody Map<String, String> filters) {
		LOGGER.info("Executing  fetchAllSubSubjectsByFilters() method of MasterController");
		int pageIndex = Integer.valueOf(filters.get("pageIndex"));
		int totalRecords = Integer.valueOf(filters.get("totalRecords"));
		return iMasterService.getAllSubSubjectsByFilter(filters, pageIndex, totalRecords);
	}

	@PostMapping(value = URLConstants.SAVES_SUB_SUBJECTS)
	public ServiceResponseDTO saveSubSubjectMaster(@RequestBody SubSubjectCreateDTO subSubjectCreateDTO) {
		LOGGER.info("Executing  saveSubSubjectMaster() method of MasterController");
		return iMasterService.saveSubSubjects(subSubjectCreateDTO);
	}

	@PutMapping(value = URLConstants.UPDATE_SUB_SUBJECTS)
	public ServiceResponseDTO editSubSubjectMaster(@RequestBody SubSubjectModifyDTO subSubjectModifyDTO) {
		LOGGER.info("Executing  editSubSubjectMaster() method of MasterController");
		return iMasterService.updateSubSubjects(subSubjectModifyDTO);
	}

	@DeleteMapping(value = URLConstants.DELETE_SUB_SUBJECTS)
	public ServiceResponseDTO deleteSubSubjectMaster(@RequestBody SubSubjectDeleteDTO subSubjectDeleteDTO) {
		LOGGER.info("Executing  deleteSubSubjectMaster() method of MasterController");
		return iMasterService.deleteSubSubjects(subSubjectDeleteDTO);
	}

	@GetMapping(value = URLConstants.FETCH_ALL_TOPICS)
	public ServiceResponseDTO fetchAllTopics(@PathVariable int pageIndex, @PathVariable int totalRecords) {
		LOGGER.info("Executing  fetchAllTopics() method of MasterController");
		return iMasterService.getAllTopics(pageIndex, totalRecords);
	}

	@PostMapping(value = URLConstants.FETCH_ALL_TOPICS_BY_FILTER)
	public ServiceResponseDTO fetchAllTopicsByFilter(@RequestBody Map<String, String> filters) {
		LOGGER.info("Executing  fetchAllTopicsByFilter() method of MasterController");
		int pageIndex = Integer.valueOf(filters.get("pageIndex"));
		int totalRecords = Integer.valueOf(filters.get("totalRecords"));
		return iMasterService.getAllTopicsByFilter(filters, pageIndex, totalRecords);
	}

	@PostMapping(value = URLConstants.SAVES_TOPICS)
	public ServiceResponseDTO saveTopicMaster(@RequestBody TopicCreateDTO topicCreateDTO) {
		LOGGER.info("Executing  saveTopicMaster() method of MasterController");
		return iMasterService.saveTopics(topicCreateDTO);
	}

	@PutMapping(value = URLConstants.UPDATE_TOPICS)
	public ServiceResponseDTO editTopicMaster(@RequestBody TopicModifyDTO topicModifyDTO) {
		LOGGER.info("Executing  editTopicMaster() method of MasterController");
		return iMasterService.updateTopics(topicModifyDTO);
	}

	@DeleteMapping(value = URLConstants.DELETE_TOPICS)
	public ServiceResponseDTO deleteTopicMaster(@RequestBody TopicDeleteDTO topicDeleteDTO) {
		LOGGER.info("Executing  deleteTopicMaster() method of MasterController");
		return iMasterService.deleteTopics(topicDeleteDTO);
	}

}