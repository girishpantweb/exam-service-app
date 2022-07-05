package com.onlineexam.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.onlineexam.app.constants.URLConstants;
import com.onlineexam.app.dto.request.ReportDTO;
import com.onlineexam.app.service.IReportService;

@RestController
@RequestMapping(value = URLConstants.REPORT_SERVICE)
public class ReportController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	IReportService reportService;

	@PostMapping(value = URLConstants.DISPLAY_REPORT)
	public String displayReport(@RequestBody ReportDTO reportDTO) {
		LOGGER.info("Executing  displayReport() method of ReportController");
		return reportService.displayReport(reportDTO);
	}

}
