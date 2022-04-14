package com.onlineexam.app.service;

import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.request.question.ManageSubSetCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionModifyDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperModifyDTO;
import com.onlineexam.app.dto.request.question.SubSetCreateDTO;
import com.onlineexam.app.dto.request.question.SubSetDeleteDTO;
import com.onlineexam.app.dto.request.question.SubSetModifyDTO;

public interface IQuestionService {

	ServiceResponseDTO getAllQuestions(int pageIndex, int totalNumberOfRecords);

	ServiceResponseDTO saveQuestions(QuestionCreateDTO questionCreateDTO);

	ServiceResponseDTO updateQuestions(QuestionModifyDTO questionModifyDTO);

	ServiceResponseDTO deleteQuestions(QuestionDeleteDTO questionDeleteDTO);

	ServiceResponseDTO getAllQuestionPaper(int pageIndex, int totalNumberOfRecords);

	ServiceResponseDTO saveQuestionPaper(QuestionPaperCreateDTO questionPaperCreateDTO);

	ServiceResponseDTO updateQuestionPaper(QuestionPaperModifyDTO questionPaperModifyDTO);

	ServiceResponseDTO deleteQuestionPaper(QuestionPaperDeleteDTO questionPaperDeleteDTO);

	ServiceResponseDTO saveQuestionSubSet(SubSetCreateDTO subSetCreateDTO);

	ServiceResponseDTO updateQuestionSubSet(SubSetModifyDTO subSetModifyDTO);

	ServiceResponseDTO deleteQuestionSubSet(SubSetDeleteDTO subSetDeleteDTO);
	
	ServiceResponseDTO manageQuestionSubSet(ManageSubSetCreateDTO manageSubSetCreateDTO);

}
