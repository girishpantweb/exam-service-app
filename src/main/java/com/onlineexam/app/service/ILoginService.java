package com.onlineexam.app.service;

import com.onlineexam.app.dto.ChangePasswordDTO;
import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.UserMasterDTO;


public interface ILoginService {


	ServiceResponseDTO validateLogin(UserMasterDTO userMasterDTO);
	ServiceResponseDTO changePassword(ChangePasswordDTO changePasswordDTO);

}