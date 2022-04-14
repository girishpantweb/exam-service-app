package com.onlineexam.app.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.onlineexam.app.constants.ResponseKeyValue;
import com.onlineexam.app.constants.StatusMaster;
import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.request.question.ManageSubSetCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionModifyDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperModifyDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperSetCreateDTO;
import com.onlineexam.app.dto.request.question.SubSetCreateDTO;
import com.onlineexam.app.dto.request.question.SubSetDeleteDTO;
import com.onlineexam.app.dto.request.question.SubSetModifyDTO;
import com.onlineexam.app.dto.response.question.QuestionDTO;
import com.onlineexam.app.pojo.CustomReponseStatus;
import com.onlineexam.app.service.IQuestionService;
import com.onlineexam.app.service.Dao.IQuestionPaperServiceDao;
import com.onlineexam.app.service.Dao.IQuestionServiceDao;

@Service("IQuestionServiceImpl")
public class QuestionServiceImpl implements IQuestionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionServiceImpl.class);
	@Autowired
	private IQuestionServiceDao questionServiceDao;
	@Autowired
	private IQuestionPaperServiceDao questionPaperServiceDao;

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

	@Override
	public ServiceResponseDTO getAllQuestionPaper(int pageIndex, int totalNumberOfRecords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponseDTO saveQuestionPaper(QuestionPaperCreateDTO questionPaperCreateDTO) {
		LOGGER.info("Executing  saveQuestionPaper() method of QuestionServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		long questionPaperId = 0;
		int status = 0;
		try {
			questionPaperId = questionPaperServiceDao.saveQuestionPaper(questionPaperCreateDTO);
			if (questionPaperId > 0) {
				status = 1;
				createSetsForQuestion(questionPaperId, questionPaperCreateDTO);
			}
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in saveQuestionPaper {}", sqx.getMessage());
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

	@Async
	private void createSetsForQuestion(long questionPaperId, QuestionPaperCreateDTO questionPaperCreateDTO)
			throws SQLException {
		String subSubjectId = questionPaperCreateDTO.getQuestionSubSubjectCreateDTO().stream()
				.map(n -> String.valueOf(n.getSubSubjectId())).collect(Collectors.joining(","));
		Map<Long, List<QuestionDTO>> questionData = questionServiceDao.getAllQuestionsBySubSubjectId(0, 0,
				subSubjectId);
		long noOfSets = questionPaperCreateDTO.getNoOfSet();
		int i = 1;
		while (i <= noOfSets) {
			final int loop = i;
			List<QuestionPaperSetCreateDTO> finalData = new ArrayList<QuestionPaperSetCreateDTO>();
			questionPaperCreateDTO.getQuestionSubSubjectCreateDTO().forEach(question -> {
				List<QuestionDTO> questionDataList = questionData.get(question.getSubSubjectId());
				if (questionDataList.size() > question.getNoOfQuestions()) {
					questionDataList = questionDataList.subList(0, question.getNoOfQuestions());
				}
				Collections.shuffle(questionDataList,
						new Random(loop + question.getSubSubjectId() + question.getUserId()));
				AtomicInteger counter = new AtomicInteger();
				questionDataList.stream().forEach(d -> {
					QuestionPaperSetCreateDTO questionPaperSetCreateDTO = new QuestionPaperSetCreateDTO();
					questionPaperSetCreateDTO.setQuestionPaperId(questionPaperId);
					questionPaperSetCreateDTO.setQuestionId(d.getQuestionId());
					questionPaperSetCreateDTO.setSetNo(loop);
					questionPaperSetCreateDTO.setSortNo(counter.getAndIncrement());
					questionPaperSetCreateDTO.setUserId(questionPaperCreateDTO.getUserId());
					finalData.add(questionPaperSetCreateDTO);
				});
			});
			int[] batchSize = questionPaperServiceDao.saveQuestionPaperSet(finalData);
			if (batchSize.length > 0) {
				LOGGER.info("Data saved for set " + i);
			} else {
				LOGGER.error("Failed to save Data for set " + i);
			}
			i++;
		}
	}

	@Override
	public ServiceResponseDTO updateQuestionPaper(QuestionPaperModifyDTO questionPaperModifyDTO) {
		LOGGER.info("Executing  updateQuestionPaper() method of QuestionServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (questionPaperModifyDTO.getQuestionPaperId() > 0)
				status = questionPaperServiceDao.updateQuestionPaper(questionPaperModifyDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in updateQuestionPaper {}", sqx.getMessage());
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
	public ServiceResponseDTO deleteQuestionPaper(QuestionPaperDeleteDTO questionPaperDeleteDTO) {
		LOGGER.info("Executing  deleteQuestionPaper() method of QuestionServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (questionPaperDeleteDTO.getQuestionPaperId() > 0)
				status = questionPaperServiceDao.deleteQuestionPaper(questionPaperDeleteDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in deleteQuestionPaper {}", sqx.getMessage());
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
	public ServiceResponseDTO saveQuestionSubSet(SubSetCreateDTO subSetCreateDTO) {
		LOGGER.info("Executing  saveQuestions() method of QuestionServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		try {
			status = questionPaperServiceDao.saveQuestionSubSet(subSetCreateDTO);
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
	public ServiceResponseDTO updateQuestionSubSet(SubSetModifyDTO subSetModifyDTO) {
		LOGGER.info("Executing  updateQuestionSubSet() method of QuestionServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (subSetModifyDTO.getQuesSubsetId() > 0)
				status = questionPaperServiceDao.updateQuestionSubSet(subSetModifyDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in updateQuestionSubSet {}", sqx.getMessage());
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
	public ServiceResponseDTO deleteQuestionSubSet(SubSetDeleteDTO subSetDeleteDTO) {
		LOGGER.info("Executing  deleteQuestionSubSet() method of QuestionServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (subSetDeleteDTO.getQuesSubsetId() > 0)
				status = questionPaperServiceDao.deleteQuestionSubSet(subSetDeleteDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in deleteQuestionSubSet {}", sqx.getMessage());
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
	public ServiceResponseDTO manageQuestionSubSet(ManageSubSetCreateDTO manageSubSetCreateDTO) {
		LOGGER.info("Executing  manageQuestionSubSet() method of QuestionServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		long status = 0;
		boolean isDuplicateData = true;
		try {
			if (manageSubSetCreateDTO.getQuesSubsetId() > 0)
				status = questionPaperServiceDao.manageQuestionSubSet(manageSubSetCreateDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in deleteQuestionSubSet {}", sqx.getMessage());
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
