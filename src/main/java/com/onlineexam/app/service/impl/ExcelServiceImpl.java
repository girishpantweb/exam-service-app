package com.onlineexam.app.service.impl;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.onlineexam.app.constants.ResponseKeyValue;
import com.onlineexam.app.constants.StatusMaster;
import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.request.question.QuestionCreateDTO;
import com.onlineexam.app.pojo.CustomReponseStatus;
import com.onlineexam.app.service.IExcelService;
import com.onlineexam.app.service.Dao.IQuestionServiceDao;
import com.onlineexam.app.utils.ExcelUploadHelper;

@Service
public class ExcelServiceImpl implements IExcelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelServiceImpl.class);

	@Autowired
	IQuestionServiceDao questionServiceDao;

	@Autowired
	ExcelUploadHelper excelUploadHelper;

	@Override
	public ServiceResponseDTO saveExcel(MultipartFile file, String fileType, long userId) {
		LOGGER.info("Executing  saveExcel() method of ExcelServiceImpl");
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		boolean uploadStatus = false;
		if (fileType.equals("question_master")) {
			List<Object> questionsData;
			try {
				questionsData = excelUploadHelper.getExcelDataAsList(file.getInputStream(), fileType,
						QuestionCreateDTO.class, userId);
				if (questionsData != null && !questionsData.isEmpty()) {
					int count[] = questionServiceDao.saveAllQuestions(questionsData);
					if (count.length > 0) {
						uploadStatus = true;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (uploadStatus) {
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		} else {
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;

	}

}
