package com.onlineexam.app.service.impl;

import org.springframework.stereotype.Service;

import com.onlineexam.app.dto.request.ReportDTO;
import com.onlineexam.app.service.IReportService;

@Service("IReportServiceImpl")
public class ReportServiceImpl implements IReportService {

	@Override
	public String displayReport(ReportDTO reportDTO) {
		return "Welcome to report section";
	}

}
