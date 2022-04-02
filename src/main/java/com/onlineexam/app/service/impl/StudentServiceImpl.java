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
import com.onlineexam.app.dto.request.student.StudentDeleteDTO;
import com.onlineexam.app.dto.request.student.StudentModifyDTO;
import com.onlineexam.app.dto.request.student.StudentsCreateDTO;
import com.onlineexam.app.dto.response.student.StudentDTO;
import com.onlineexam.app.pojo.CustomReponseStatus;
import com.onlineexam.app.service.IStudentService;
import com.onlineexam.app.service.Dao.IStudentServiceDao;

@Service("IStudentServiceImpl")
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private IStudentServiceDao isStudentServiceDao;
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Override
	public ServiceResponseDTO getAllStudents(int pageIndex, int totalRecords) {
		LOGGER.info("Executing  getAllStudents() method of ServiceResponseDTO");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<StudentDTO> dataList = null;
		Integer count = 0;
		int slNo[] = { 0 };
		try {
			dataList = isStudentServiceDao.getAllStudent(pageIndex, totalRecords);
			if (dataList != null && dataList.size() > 0) {
				dataList.forEach(item -> {
					slNo[0]++;
					item.setSno(slNo[0]);
				});
			}
			count = isStudentServiceDao.getTotalStudentCount();
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.STUDENT_DATA_KEY.key(), dataList);
			response.put(ResponseKeyValue.STUDENT_ALL_DATA_COUNT_KEY.key(), count);
		} catch (Exception ex) {
			LOGGER.error("Exception Occur in getAllRole {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		} finally {
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO saveStudents(StudentsCreateDTO studentsCreateDTO) {
		LOGGER.info("Executing  saveStudents() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		try {
			status = isStudentServiceDao.saveStudent(studentsCreateDTO);
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in saveStudents {}", sqx.getMessage());
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
	public ServiceResponseDTO updateStudents(StudentModifyDTO studentModifyDTO) {
		LOGGER.info("Executing  updateStudents() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (studentModifyDTO.getStudentId() > 0)
				status = isStudentServiceDao.updateStudent(studentModifyDTO);
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
				customReponseStatus = new CustomReponseStatus(StatusMaster.STUDENTREADYEXIST.getResponseCode(),
						StatusMaster.STUDENTREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO deleteStudents(StudentDeleteDTO studentDeleteDTO) {
		LOGGER.info("Executing  deleteStudents() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (studentDeleteDTO.getStudentId() > 0)
				status = isStudentServiceDao.deleteStudent(studentDeleteDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in deleteStudents {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.STUDENTREADYEXIST.getResponseCode(),
						StatusMaster.STUDENTREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

}
