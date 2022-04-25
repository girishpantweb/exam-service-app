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
import com.onlineexam.app.dto.request.exam.ExamCreateDTO;
import com.onlineexam.app.dto.request.exam.ExamDeleteDTO;
import com.onlineexam.app.dto.request.exam.ExamModifyDTO;
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

}
