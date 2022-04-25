package com.onlineexam.app.service;

import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.request.exam.ExamCreateDTO;
import com.onlineexam.app.dto.request.exam.ExamDeleteDTO;
import com.onlineexam.app.dto.request.exam.ExamModifyDTO;

public interface IExamService {

	ServiceResponseDTO getAllExams(int pageIndex, int totalNumberOfRecords);

	ServiceResponseDTO saveExam(ExamCreateDTO examCreateDTO);

	ServiceResponseDTO updateExam(ExamModifyDTO examModifyDTO);

	ServiceResponseDTO deleteExam(ExamDeleteDTO examDeleteDTO);
	
}
