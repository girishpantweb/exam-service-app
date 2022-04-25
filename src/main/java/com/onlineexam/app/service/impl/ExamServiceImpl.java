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
import com.onlineexam.app.dto.request.exam.ExamCreateDTO;
import com.onlineexam.app.dto.request.exam.ExamDeleteDTO;
import com.onlineexam.app.dto.request.exam.ExamModifyDTO;
import com.onlineexam.app.dto.response.exam.ExamDTO;
import com.onlineexam.app.pojo.CustomReponseStatus;
import com.onlineexam.app.service.IExamService;
import com.onlineexam.app.service.Dao.IExamServiceDao;

@Service("IExamServiceImpl")
public class ExamServiceImpl implements IExamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExamServiceImpl.class);

	@Autowired
	private IExamServiceDao examServiceDao;

	@Override
	public ServiceResponseDTO getAllExams(int pageIndex, int totalNumberOfRecords) {
		LOGGER.info("Executing  getAllExams() method of ExamServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<ExamDTO> dataList = null;
		Integer count = 0;
		int slNo[] = { 0 };
		try {
			dataList = examServiceDao.getAllExams(pageIndex, totalNumberOfRecords);
			if (dataList != null && dataList.size() > 0) {
				dataList.forEach(item -> {
					slNo[0]++;
					item.setSno(slNo[0]);
				});
			}
			count = examServiceDao.getTotalExams();
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.EXAM_DATA_KEY.key(), dataList);
			response.put(ResponseKeyValue.EXAM_ALL_DATA_COUNT_KEY.key(), count);
		} catch (Exception ex) {
			LOGGER.error("Exception Occur in getAllExams {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		} finally {
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO saveExam(ExamCreateDTO examCreateDTO) {
		LOGGER.info("Executing  saveExam() method of ExamServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		try {
			status = examServiceDao.saveExam(examCreateDTO);
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in saveExam {}", sqx.getMessage());
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
	public ServiceResponseDTO updateExam(ExamModifyDTO examModifyDTO) {
		LOGGER.info("Executing  updateExam() method of ExamServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (examModifyDTO.getExamId() > 0)
				status = examServiceDao.updateExam(examModifyDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in updateExam {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.EXAMREADYEXIST.getResponseCode(),
						StatusMaster.EXAMREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO deleteExam(ExamDeleteDTO examDeleteDTO) {
		LOGGER.info("Executing  deleteExam() method of ExamServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (examDeleteDTO.getExamId() > 0)
				status = examServiceDao.deleteExam(examDeleteDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in deleteExam {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.EXAMREADYEXIST.getResponseCode(),
						StatusMaster.EXAMREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

}
