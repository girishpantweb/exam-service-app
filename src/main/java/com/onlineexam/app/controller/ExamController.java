package com.onlineexam.app.controller;

import java.util.List;

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
import com.onlineexam.app.dto.request.StudentFilterDTO;
import com.onlineexam.app.dto.request.exam.AssignExamCreateDTO;
import com.onlineexam.app.dto.request.exam.AssignExamDeleteDTO;
import com.onlineexam.app.dto.request.exam.AssignExamModifDTO;
import com.onlineexam.app.dto.request.exam.ExamCreateDTO;
import com.onlineexam.app.dto.request.exam.ExamDeleteDTO;
import com.onlineexam.app.dto.request.exam.ExamModifyDTO;
import com.onlineexam.app.dto.request.exam.ExamResultDTO;
import com.onlineexam.app.service.IExamService;

@RestController
@RequestMapping(value = URLConstants.EXAM_SERVICE)
public class ExamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExamController.class);

	@Autowired
	private IExamService examService;

	@GetMapping(value = URLConstants.FETCH_ALL_EXAMS)
	public ServiceResponseDTO fetchAllExams(@PathVariable int pageIndex, @PathVariable int totalRecords) {
		LOGGER.info("Executing  fetchAllExams() method of ExamController");
		return examService.getAllExams(pageIndex, totalRecords);
	}

	@GetMapping(value = URLConstants.FETCH_EXAMS_BY_YEAR)
	public ServiceResponseDTO fetchAllExamByYear(@PathVariable String examYear) {
		LOGGER.info("Executing  fetchAllExamByYear() method of ExamController");
		return examService.getExamsByYear(examYear);
	}

	@PostMapping(value = URLConstants.SAVES_EXAMS)
	public ServiceResponseDTO saveExamMaster(@RequestBody ExamCreateDTO examCreateDTO) {
		LOGGER.info("Executing  saveExamMaster() method of ExamController");
		return examService.saveExam(examCreateDTO);
	}

	@PutMapping(value = URLConstants.UPDATE_EXAMS)
	public ServiceResponseDTO editExamMaster(@RequestBody ExamModifyDTO examModifyDTO) {
		LOGGER.info("Executing  editExamMaster() method of ExamController");
		return examService.updateExam(examModifyDTO);
	}

	@DeleteMapping(value = URLConstants.DELETE_EXAMS)
	public ServiceResponseDTO deleteExamMaster(@RequestBody ExamDeleteDTO examDeleteDTO) {
		LOGGER.info("Executing  deleteExamMaster() method of ExamController");
		return examService.deleteExam(examDeleteDTO);
	}

	@PostMapping(value = URLConstants.ASSIGN_EXAMS)
	public ServiceResponseDTO saveAssignExam(@RequestBody AssignExamCreateDTO assignExamCreateDTO) {
		LOGGER.info("Executing  assignExam() method of ExamController");
		return examService.assignCreateExam(assignExamCreateDTO);
	}

	@PutMapping(value = URLConstants.UPDATE_ASSIGN_EXAMS)
	public ServiceResponseDTO editAssignExam(@RequestBody AssignExamModifDTO assignExamModifDTO) {
		LOGGER.info("Executing  editAssignExam() method of ExamController");
		return examService.assignUpdateExam(assignExamModifDTO);
	}

	@DeleteMapping(value = URLConstants.DELETE_ASSIGN_EXAMS)
	public ServiceResponseDTO deleteAssignExam(@RequestBody AssignExamDeleteDTO assignExamDeleteDTO) {
		LOGGER.info("Executing  deleteAssignExam() method of ExamController");
		return examService.assignDeleteExam(assignExamDeleteDTO);
	}

	@PostMapping(value = URLConstants.FETCH_ALL_STUDENTS_BY_FILTERS)
	public ServiceResponseDTO fetchAllAssignedStudents(@RequestBody StudentFilterDTO studentFilterDTO) {
		LOGGER.info("Executing  fetchAllAssignedStudents() method of StudentController");
		return examService.getAllAssignStudent(studentFilterDTO);
	}

	@GetMapping(value = URLConstants.FETCH_ASSIGN_EXAMS)
	public ServiceResponseDTO fetchAssignExams(@PathVariable int pageIndex, @PathVariable int totalRecords) {
		LOGGER.info("Executing  fetchAllAssignedStudents() method of StudentController");
		return examService.getAllAssignExams(pageIndex, totalRecords);
	}

	@GetMapping(value = URLConstants.FETCH_STUDENT_QUESTIONS_BY_STUDENT_ID)
	public ServiceResponseDTO fetchStudentAssignedQuestions(@PathVariable int examId, @PathVariable String examYear,
			@PathVariable int studentId) {
		LOGGER.info("Executing  fetchStudentAssignedQuestions() method of StudentController");
		return examService.getAllAssignStudentQuestions(examId, examYear, studentId);
	}

	@PostMapping(value = URLConstants.SAVE_EXAM_RESULT_OF_STUDENT)
	public ServiceResponseDTO saveExamResult(@RequestBody List<ExamResultDTO> examResultDTOList) {
		LOGGER.info("Executing  assignExam() method of ExamController");
		return examService.saveExamResult(examResultDTOList);
	}

	@GetMapping(value = URLConstants.FETCH_STUDENT_EXAMS_BY_STUDENT_ID)
	public ServiceResponseDTO fetchStudentExams(@PathVariable int studentId) {
		LOGGER.info("Executing  fetchStudentAssignedQuestions() method of ExamController");
		return examService.getStudentExams(studentId);
	}

	@GetMapping(value = URLConstants.FETCH_EXAM_RESULT_BY_STUDENT_ID)
	public ServiceResponseDTO fetchExamResultByStudent(@PathVariable int studentId, @PathVariable String examYear) {
		LOGGER.info("Executing  fetchExamResultByStudent() method of ExamController");
		return examService.fetchExamResultByStudent(studentId, examYear);
	}

	@GetMapping(value = URLConstants.FETCH_STUDENT_EXAM_YEAR)
	public ServiceResponseDTO fetchStudentExamYears(@PathVariable int studentId) {
		LOGGER.info("Executing  fetchStudentExamYears() method of ExamController");
		return examService.fetchStudentExamYears(studentId);
	}

}