package com.onlineexam.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.service.IExcelService;

@CrossOrigin
@RestController
@RequestMapping("/api/excel")
public class ExcelController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelController.class);

	@Autowired
	IExcelService excelService;

	@PostMapping("/upload")
	public ServiceResponseDTO uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("fileType") String fileType, @RequestParam("userId") long userId) {
		LOGGER.info("Executing  fetchAllRoles() method of MasterController");
		return excelService.saveExcel(file, fileType, userId);
	}

}
