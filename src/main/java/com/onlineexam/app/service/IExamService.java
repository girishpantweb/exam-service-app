package com.onlineexam.app.service;

import java.util.List;

import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.request.StudentFilterDTO;
import com.onlineexam.app.dto.request.exam.AssignExamCreateDTO;
import com.onlineexam.app.dto.request.exam.AssignExamDeleteDTO;
import com.onlineexam.app.dto.request.exam.AssignExamModifDTO;
import com.onlineexam.app.dto.request.exam.ExamCreateDTO;
import com.onlineexam.app.dto.request.exam.ExamDeleteDTO;
import com.onlineexam.app.dto.request.exam.ExamModifyDTO;
import com.onlineexam.app.dto.request.exam.ExamResultDTO;

public interface IExamService {

	ServiceResponseDTO getAllExams(int pageIndex, int totalNumberOfRecords);

	ServiceResponseDTO getExamsByYear(String examYear);

	ServiceResponseDTO saveExam(ExamCreateDTO examCreateDTO);

	ServiceResponseDTO updateExam(ExamModifyDTO examModifyDTO);

	ServiceResponseDTO deleteExam(ExamDeleteDTO examDeleteDTO);

	ServiceResponseDTO assignCreateExam(AssignExamCreateDTO assignExamCreateDTO);

	ServiceResponseDTO assignUpdateExam(AssignExamModifDTO assignExamModifDTO);

	ServiceResponseDTO assignDeleteExam(AssignExamDeleteDTO assignExamDeleteDTO);

	ServiceResponseDTO getAllAssignStudent(StudentFilterDTO studentFilterDTO);

	ServiceResponseDTO getAllAssignExams(int pageIndex, int totalNumberOfRecords);

	ServiceResponseDTO getAllAssignStudentQuestions(int examId, String examYear, int studentId);

	ServiceResponseDTO saveExamResult(List<ExamResultDTO> examResultDTOList);

	ServiceResponseDTO getStudentExams(int studentId);

	ServiceResponseDTO fetchStudentExamYears(int studentId);

	ServiceResponseDTO fetchExamResultByStudent(int studentId, String examYear);

}
