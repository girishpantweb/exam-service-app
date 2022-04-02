package com.onlineexam.app.service;

import com.onlineexam.app.dto.ServiceResponseDTO;

public interface IMenuService {

	ServiceResponseDTO menuMaster();
	ServiceResponseDTO menuUserMaster(long userid);

}