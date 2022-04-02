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
import com.onlineexam.app.dto.request.question.QuestionCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionModifyDTO;
import com.onlineexam.app.service.IQuestionService;

@RestController
@RequestMapping(value = URLConstants.QUESTION_SERVICE)
public class QuestionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	private IQuestionService questionService;

	@GetMapping(value = URLConstants.FETCH_ALL_QUESTIONS)
	public ServiceResponseDTO fetchAllQuestions(@PathVariable int pageIndex, @PathVariable int totalRecords) {
		LOGGER.info("Executing  fetchAllQuestions() method of QuestionController");
		return questionService.getAllQuestions(pageIndex, totalRecords);
	}

	@GetMapping(value = URLConstants.FETCH_ALL_QUESTIONS_BY_FILTER)
	public ServiceResponseDTO fetchAllQuestionsByFilter(@PathVariable int pageIndex, @PathVariable int totalRecords) {
		LOGGER.info("Executing  fetchAllQuestionsByFilter() method of QuestionController");
		return questionService.getAllQuestions(pageIndex, totalRecords);
	}

	@PostMapping(value = URLConstants.SAVES_QUESTIONS)
	public ServiceResponseDTO saveQuestionsMaster(@RequestBody QuestionCreateDTO questionCreateDTO) {
		LOGGER.info("Executing  saveQuestionsMaster() method of QuestionController");
		return questionService.saveQuestions(questionCreateDTO);
	}

	@PutMapping(value = URLConstants.UPDATE_QUESTIONS)
	public ServiceResponseDTO editQuestionsMaster(@RequestBody QuestionModifyDTO questionModifyDTO) {
		LOGGER.info("Executing  editQuestionsMaster() method of QuestionController");
		return questionService.updateQuestions(questionModifyDTO);
	}

	@DeleteMapping(value = URLConstants.DELETE_QUESTIONS)
	public ServiceResponseDTO deleteQuestionsMaster(@RequestBody QuestionDeleteDTO questionDeleteDTO) {
		LOGGER.info("Executing  deleteQuestionsMaster() method of QuestionController");
		return questionService.deleteQuestions(questionDeleteDTO);
	}

}
