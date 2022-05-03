package com.onlineexam.app.service.Dao;

import java.sql.SQLException;
import java.util.List;

import com.onlineexam.app.dto.request.StudentFilterDTO;
import com.onlineexam.app.dto.request.exam.AsignStudentExamQuestionsDTO;
import com.onlineexam.app.dto.request.exam.AssignExamCreateDTO;
import com.onlineexam.app.dto.request.exam.AssignExamDTO;
import com.onlineexam.app.dto.request.exam.AssignExamDeleteDTO;
import com.onlineexam.app.dto.request.exam.AssignExamModifDTO;
import com.onlineexam.app.dto.request.exam.ExamCreateDTO;
import com.onlineexam.app.dto.request.exam.ExamDeleteDTO;
import com.onlineexam.app.dto.request.exam.ExamModifyDTO;
import com.onlineexam.app.dto.request.exam.ExamResultDTO;
import com.onlineexam.app.dto.request.exam.StudentExamInfoDTO;
import com.onlineexam.app.dto.response.exam.AssignStudentDTO;
import com.onlineexam.app.dto.response.exam.ExamDTO;

public interface IExamServiceDao {

	List<ExamDTO> getAllExams(int pageIndex, int totalRecords);

	List<AssignExamDTO> getAllAssignExams(int pageIndex, int totalRecords);

	List<ExamDTO> getAllExamsByYear(String examYear);

	Integer getTotalExams();

	Integer getTotalAssignExams();

	int saveExam(ExamCreateDTO examCreateDTO) throws SQLException;

	int updateExam(ExamModifyDTO examModifyDTO) throws SQLException;

	int deleteExam(ExamDeleteDTO examDeleteDTO) throws SQLException;

	int saveAssignExam(AssignExamCreateDTO assignExamCreateDTO) throws SQLException;

	int saveExamResult(List<ExamResultDTO> examResultDTOList) throws SQLException;

	int updateAssignExam(AssignExamModifDTO assignExamModifDTO) throws SQLException;

	int deleteAssignExam(AssignExamDeleteDTO assignExamDeleteDTO) throws SQLException;

	List<AssignStudentDTO> getAllAssignStudentDetails(StudentFilterDTO studentFilterDTO) throws SQLException;

	List<AsignStudentExamQuestionsDTO> getAllAssignedStudentQuestions(int examId, String examYear, int studentId)
			throws SQLException;

	List<StudentExamInfoDTO> getStudentExamsInfo(int studentId) throws SQLException;

}
