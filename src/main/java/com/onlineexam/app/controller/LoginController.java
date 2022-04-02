package com.onlineexam.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.onlineexam.app.constants.URLConstants;
import com.onlineexam.app.dto.ChangePasswordDTO;
import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.UserMasterDTO;
import com.onlineexam.app.service.ILoginService;

@RestController
@RequestMapping(value = URLConstants.LOGIN_SERVICE)
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private ILoginService iLoginService;

	@CrossOrigin
	@PostMapping(value = URLConstants.LOGIN_VALIDATION)
	public ServiceResponseDTO validateLogin(@RequestBody UserMasterDTO userMasterDTO) {
		LOGGER.info("Executing  validateLogin() method of LoginController");
		return iLoginService.validateLogin(userMasterDTO);
	}

	@PutMapping(value = URLConstants.CHANGE_PASSWORD)
	public ServiceResponseDTO changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
		LOGGER.info("Executing  changePassword() method of LoginController");
		return iLoginService.changePassword(changePasswordDTO);
	}

}
