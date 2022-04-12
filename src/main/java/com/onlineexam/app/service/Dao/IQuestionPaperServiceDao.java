package com.onlineexam.app.service.Dao;

import java.sql.SQLException;
import java.util.List;
import com.onlineexam.app.dto.request.question.QuestionPaperCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperModifyDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperSetCreateDTO;
import com.onlineexam.app.dto.response.question.QuestionDTO;

public interface IQuestionPaperServiceDao {

	//List<QuestionDTO> getAllQuestionPaper(int pageIndex, int totalRecords);

	Integer getTotalQuestionPaper();

	long saveQuestionPaper(QuestionPaperCreateDTO questionPaperCreateDTO) throws SQLException;

	int[] saveAllQuestionPaper(List<Object> questionList);

	int updateQuestionPaper(QuestionPaperModifyDTO questionPaperModifyDTO) throws SQLException;

	int deleteQuestionPaper(QuestionPaperDeleteDTO questionPaperDeleteDTO) throws SQLException;

	int[] saveQuestionPaperSet(List<QuestionPaperSetCreateDTO> questionPaperSetCreateDTO) throws SQLException;

}
