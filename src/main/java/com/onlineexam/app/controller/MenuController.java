package com.onlineexam.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineexam.app.constants.URLConstants;
import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.service.IMenuService;

@RestController
@RequestMapping(value = URLConstants.MENU_SERVICE)
public class MenuController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private IMenuService iMenuService;
	

	@GetMapping(value = URLConstants.MENU_DETAILS)
	public ServiceResponseDTO menuMaster() {
		LOGGER.info("Executing  menuMaster() method of MenuController");
		return iMenuService.menuMaster();
	}

	@GetMapping(value = URLConstants.MENU_DETAILS_FOR_USER)
	public ServiceResponseDTO menuUserMaster(@RequestParam("roleId") long roleId) {
		LOGGER.info("Executing  menuMaster() method of MenuController");
		return iMenuService.menuUserMaster(roleId);
	}

}