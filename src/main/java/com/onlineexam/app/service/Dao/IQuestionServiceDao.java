package com.onlineexam.app.service.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.onlineexam.app.dto.request.question.QuestionCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionModifyDTO;
import com.onlineexam.app.dto.response.question.QuestionDTO;

public interface IQuestionServiceDao {

	List<QuestionDTO> getAllQuestions(int pageIndex, int totalRecords);

	Map<Long, List<QuestionDTO>> getAllQuestionsBySubSubjectId(int pageIndex, int totalRecords, String subSubjectId);

	Integer getTotalQuestions();

	Integer findQuestionsCountByQuestionsName(String questionName) throws SQLException;

	int saveQuestion(QuestionCreateDTO questionCreateDTO) throws SQLException;

	int[] saveAllQuestions(List<Object> questionList);

	int updateQuestion(QuestionModifyDTO questionModifyDTO) throws SQLException;

	int deleteQuestion(QuestionDeleteDTO questionDeleteDTO) throws SQLException;

}
