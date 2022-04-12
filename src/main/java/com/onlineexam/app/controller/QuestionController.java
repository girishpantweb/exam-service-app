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
import com.onlineexam.app.dto.request.question.QuestionPaperCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperModifyDTO;
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

	@GetMapping(value = URLConstants.FETCH_ALL_QUESTIONS_PAPER)
	public ServiceResponseDTO fetchAllQuestionPaper(@PathVariable int pageIndex, @PathVariable int totalRecords) {
		LOGGER.info("Executing  fetchAllQuestionPaper() method of QuestionController");
		return questionService.getAllQuestionPaper(pageIndex, totalRecords);
	}

	@GetMapping(value = URLConstants.FETCH_ALL_QUESTIONS_PAPER_BY_FILTER)
	public ServiceResponseDTO fetchAllQuestionPaperByFilter(@PathVariable int pageIndex,
			@PathVariable int totalRecords) {
		LOGGER.info("Executing  fetchAllQuestionPaperByFilter() method of QuestionController");
		return questionService.getAllQuestionPaper(pageIndex, totalRecords);
	}

	@PostMapping(value = URLConstants.SAVES_QUESTIONS_PAPER)
	public ServiceResponseDTO saveQuestionsPaperMaster(@RequestBody QuestionPaperCreateDTO questionCreateDTO) {
		LOGGER.info("Executing  saveQuestionsPaperMaster() method of QuestionController");
		return questionService.saveQuestionPaper(questionCreateDTO);
	}

	@PutMapping(value = URLConstants.UPDATE_QUESTIONS_PAPER)
	public ServiceResponseDTO editQuestionsPaperMaster(@RequestBody QuestionPaperModifyDTO questionModifyDTO) {
		LOGGER.info("Executing  editQuestionsPaperMaster() method of QuestionController");
		return questionService.updateQuestionPaper(questionModifyDTO);
	}

	@DeleteMapping(value = URLConstants.DELETE_QUESTIONS_PAPER)
	public ServiceResponseDTO deleteQuestionsPaperMaster(@RequestBody QuestionPaperDeleteDTO questionDeleteDTO) {
		LOGGER.info("Executing  deleteQuestionsPaperMaster() method of QuestionController");
		return questionService.deleteQuestionPaper(questionDeleteDTO);
	}

}
