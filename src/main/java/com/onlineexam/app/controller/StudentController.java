package com.onlineexam.app.controller;

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
import com.onlineexam.app.dto.request.student.StudentDeleteDTO;
import com.onlineexam.app.dto.request.student.StudentModifyDTO;
import com.onlineexam.app.dto.request.student.StudentsCreateDTO;
import com.onlineexam.app.service.IStudentService;

@RestController
@RequestMapping(value = URLConstants.STUDENT_SERVICE)
public class StudentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private IStudentService iStudentService;

	@GetMapping(value = URLConstants.FETCH_ALL_STUDENTS)
	public ServiceResponseDTO fetchAllStudents(@PathVariable int pageIndex, @PathVariable int totalRecords) {
		LOGGER.info("Executing  fetchAllClasses() method of StudentController");
		return iStudentService.getAllStudents(pageIndex, totalRecords);
	}

	@GetMapping(value = URLConstants.FETCH_STUDENTS_BY_STUDENT_ID)
	public ServiceResponseDTO fetchStudentByStudentId(@PathVariable int pageIndex, @PathVariable int totalRecords,
			@PathVariable long studentId) {
		LOGGER.info("Executing  fetchAllClasses() method of StudentController");
		return iStudentService.getAllStudentByStudentId(pageIndex, totalRecords, studentId);
	}

	@PostMapping(value = URLConstants.SAVES_STUDENTS)
	public ServiceResponseDTO saveClassesMaster(@RequestBody StudentsCreateDTO studentsCreateDTO) {
		LOGGER.info("Executing  saveClassesMaster() method of StudentController");
		return iStudentService.saveStudents(studentsCreateDTO);
	}

	@PutMapping(value = URLConstants.UPDATE_STUDENTS)
	public ServiceResponseDTO editClassesMaster(@RequestBody StudentModifyDTO studentModifyDTO) {
		LOGGER.info("Executing  editClassesMaster() method of StudentController");
		return iStudentService.updateStudents(studentModifyDTO);
	}

	@DeleteMapping(value = URLConstants.DELETE_STUDENTS)
	public ServiceResponseDTO deleteClassesMaster(@RequestBody StudentDeleteDTO studentDeleteDTO) {
		LOGGER.info("Executing  deleteClassesMaster() method of StudentController");
		return iStudentService.deleteStudents(studentDeleteDTO);
	}

}
