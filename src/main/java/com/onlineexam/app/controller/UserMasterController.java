package com.onlineexam.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineexam.app.constants.URLConstants;
import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.request.UserMasterCreateDTO;
import com.onlineexam.app.dto.request.UserMasterModifyDTO;
import com.onlineexam.app.dto.request.UserMasterUpdateStatusDTO;
import com.onlineexam.app.service.IUserMasterService;

@RestController
@RequestMapping(value = URLConstants.REGISTRATION_SERVICE)
public class UserMasterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserMasterController.class);

	@Autowired
	private IUserMasterService iUserMasterService;

	@PostMapping(value = URLConstants.REGISTER_USER)
	public ServiceResponseDTO registerUser(@RequestBody UserMasterCreateDTO userMasterCreateDTO) {
		LOGGER.info("Executing  registerUser() method of UserMasterController");
		return iUserMasterService.registerUser(userMasterCreateDTO);
	}
	
	@PutMapping(value = URLConstants.UPDATE_USER)
	public ServiceResponseDTO updateUser(@RequestBody UserMasterModifyDTO userMasterModifyDTO) {
		LOGGER.info("Executing  updateUser() method of UserMasterController");
		return iUserMasterService.upadteUser(userMasterModifyDTO);
	}
	@DeleteMapping(value = URLConstants.DELETE_USER)
	public ServiceResponseDTO updateUserStatus(@RequestBody UserMasterUpdateStatusDTO userMasterUpdateStatusDTO) {
		LOGGER.info("Executing  updateUserStatus() method of UserMasterController");
		return iUserMasterService.updateUserStatus(userMasterUpdateStatusDTO);
	}
	
	@GetMapping(value = URLConstants.FETCH_ALL_USERS)
	public ServiceResponseDTO fetchAllUsers(@PathVariable int pageIndex, @PathVariable int totalRecords) {
		LOGGER.info("Executing  validateLogin() method of MenuController");
		return iUserMasterService.getAllUsers(pageIndex, totalRecords);
	}
}
