package com.onlineexam.app.service;

import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.request.UserMasterCreateDTO;
import com.onlineexam.app.dto.request.UserMasterModifyDTO;
import com.onlineexam.app.dto.request.UserMasterUpdateStatusDTO;

public interface IUserMasterService {
	//ServiceResponseDTO registerUser(UserMasterDTO userMasterDTO);
	ServiceResponseDTO registerUser(UserMasterCreateDTO userMasterCreateDTO);
	ServiceResponseDTO upadteUser(UserMasterModifyDTO userMasterCreateDTO);
	ServiceResponseDTO updateUserStatus(UserMasterUpdateStatusDTO userMasterUpdateStatusDTO);
	ServiceResponseDTO getAllUsers(int pageIndex, int totalNumberOfRecords);

}