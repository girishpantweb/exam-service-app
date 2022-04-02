package com.onlineexam.app.service.impl;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onlineexam.app.constants.ResponseKeyValue;
import com.onlineexam.app.constants.StatusMaster;
import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.request.question.QuestionCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionModifyDTO;
import com.onlineexam.app.dto.response.question.QuestionDTO;
import com.onlineexam.app.pojo.CustomReponseStatus;
import com.onlineexam.app.service.IQuestionService;
import com.onlineexam.app.service.Dao.IQuestionServiceDao;

@Service("IQuestionServiceImpl")
public class QuestionServiceImpl implements IQuestionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionServiceImpl.class);
	@Autowired
	private IQuestionServiceDao questionServiceDao;

	@Override
	public ServiceResponseDTO getAllQuestions(int pageIndex, int totalNumberOfRecords) {
		LOGGER.info("Executing  getAllQuestions() method of QuestionServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<QuestionDTO> dataList = null;
		Integer count = 0;
		int slNo[] = { 0 };
		try {
			dataList = questionServiceDao.getAllQuestions(pageIndex, totalNumberOfRecords);
			if (dataList != null && dataList.size() > 0) {
				dataList.forEach(item -> {
					slNo[0]++;
					item.setSno(slNo[0]);
				});
			}
			count = questionServiceDao.getTotalQuestions();
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.QUESTION_DATA_KEY.key(), dataList);
			response.put(ResponseKeyValue.QUESTION_ALL_DATA_COUNT_KEY.key(), count);
		} catch (Exception ex) {
			LOGGER.error("Exception Occur in getAllQuestions {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		} finally {
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO saveQuestions(QuestionCreateDTO questionCreateDTO) {
		LOGGER.info("Executing  saveQuestions() method of QuestionServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		try {
			status = questionServiceDao.saveQuestion(questionCreateDTO);
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in saveQuestions {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		} finally {

		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO updateQuestions(QuestionModifyDTO questionModifyDTO) {
		LOGGER.info("Executing  updateQuestions() method of QuestionServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (questionModifyDTO.getQuestionId() > 0)
				status = questionServiceDao.updateQuestion(questionModifyDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in updateStudents {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.QUESTIONREADYEXIST.getResponseCode(),
						StatusMaster.QUESTIONREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO deleteQuestions(QuestionDeleteDTO questionDeleteDTO) {
		LOGGER.info("Executing  deleteQuestions() method of QuestionServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (questionDeleteDTO.getQuestionId() > 0)
				status = questionServiceDao.deleteQuestion(questionDeleteDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in deleteQuestions {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.QUESTIONREADYEXIST.getResponseCode(),
						StatusMaster.QUESTIONREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

}
