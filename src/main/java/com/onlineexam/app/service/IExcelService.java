package com.onlineexam.app.service;

import org.springframework.web.multipart.MultipartFile;
import com.onlineexam.app.dto.ServiceResponseDTO;

public interface IExcelService {

	ServiceResponseDTO saveExcel(MultipartFile file, String fileType , long userId);

}
