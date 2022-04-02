package com.onlineexam.app.service.Dao;

import java.util.List;
import java.util.Map;

import com.onlineexam.app.dto.request.UserMasterCreateDTO;
import com.onlineexam.app.dto.request.UserMasterModifyDTO;
import com.onlineexam.app.dto.request.UserMasterUpdateStatusDTO;
import com.onlineexam.app.dto.response.UserMasterFetchDTO;

public interface IUserMasterServiceDAO {
	String getPasswordByMobileOrEmail(String email,String mobile);
	String getPasswordByuserId(long userId);
	int createUser(UserMasterCreateDTO userMasterCreateDTO);
	int updateUser(UserMasterModifyDTO userMasterCreateDTO);
	int updateUserStatus(UserMasterUpdateStatusDTO userMasterUpdateStatusDTO);
	Map<String,Object> getUserByMobileAndEmail(long mobileNumber,String email,long userId,boolean isForUpdateUser);
	List<UserMasterFetchDTO> fetchUserDetails(int pageNo,int tolatRecord);
	Long countAllUser();
	
}
