package com.onlineexam.app.service;

import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.request.question.QuestionCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionModifyDTO;

public interface IQuestionService {

	ServiceResponseDTO getAllQuestions(int pageIndex, int totalNumberOfRecords);

	ServiceResponseDTO saveQuestions(QuestionCreateDTO questionCreateDTO);

	ServiceResponseDTO updateQuestions(QuestionModifyDTO questionModifyDTO);

	ServiceResponseDTO deleteQuestions(QuestionDeleteDTO questionDeleteDTO);

}
