package com.onlineexam.app.service.Dao;

import java.sql.SQLException;
import java.util.List;
import com.onlineexam.app.dto.request.exam.ExamCreateDTO;
import com.onlineexam.app.dto.request.exam.ExamDeleteDTO;
import com.onlineexam.app.dto.request.exam.ExamModifyDTO;
import com.onlineexam.app.dto.response.exam.ExamDTO;

public interface IExamServiceDao {
	
	List<ExamDTO> getAllExams(int pageIndex, int totalRecords);
	
	Integer getTotalExams();
	
	int saveExam(ExamCreateDTO examCreateDTO) throws SQLException;
	
	int updateExam(ExamModifyDTO examModifyDTO) throws SQLException;

	int deleteExam(ExamDeleteDTO examDeleteDTO) throws SQLException;

}
