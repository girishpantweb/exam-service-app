/**
 * 
 */
package com.onlineexam.app.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlineexam.app.constants.ResponseKeyValue;
import com.onlineexam.app.constants.StatusMaster;
import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.request.UserMasterCreateDTO;
import com.onlineexam.app.dto.request.UserMasterModifyDTO;
import com.onlineexam.app.dto.request.UserMasterUpdateStatusDTO;
import com.onlineexam.app.dto.response.UserMasterFetchDTO;
import com.onlineexam.app.pojo.CustomReponseStatus;
import com.onlineexam.app.service.IUserMasterService;
import com.onlineexam.app.service.Dao.IUserMasterServiceDAO;

/**
 * @author Anupam.Bisen
 *
 */
@Service
@PropertySource("classpath:SQL.properties")
public class IUserMasterServiceImpl implements IUserMasterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(IUserMasterServiceImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value( "${user.default.pasword}" )
	private String defaultUserPassword;
	
	//@Autowired
	//private Environment env;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IUserMasterServiceDAO iUserMasterServiceDAO;

	
	@Override
	public ServiceResponseDTO registerUser(UserMasterCreateDTO userMasterCreateDTO) {
		LOGGER.info("Executing  registerUser() method of IUserMasterServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		Map<String,Object> userMoobileAndEmailDetail=null;
		long mobileNumber=0;
		String email="";
		try {
			if (StringUtils.isNotBlank(userMasterCreateDTO.getEmail()) && userMasterCreateDTO.getMobileNumber() != 0) {
				userMoobileAndEmailDetail = iUserMasterServiceDAO
						.getUserByMobileAndEmail(userMasterCreateDTO.getMobileNumber(),userMasterCreateDTO.getEmail(),0l,false);
				
				if(userMoobileAndEmailDetail!=null)	{
				mobileNumber=userMoobileAndEmailDetail.get("user_mobile")!=null?(Long)userMoobileAndEmailDetail.get("user_mobile"):0;
				email=userMoobileAndEmailDetail.get("user_email")!=null?(String)userMoobileAndEmailDetail.get("user_email"):"";
				}
				
				if (mobileNumber==userMasterCreateDTO.getMobileNumber()) {
					customReponseStatus = new CustomReponseStatus(StatusMaster.MOBILEALREADYREGISTERED.getResponseCode(),
							StatusMaster.MOBILEALREADYREGISTERED.getResponseMessage());
				} else if (email.equalsIgnoreCase(userMasterCreateDTO.getEmail().trim())) {
					customReponseStatus = new CustomReponseStatus(StatusMaster.EMAILALREADYREGISTERED.getResponseCode(),
							StatusMaster.EMAILALREADYREGISTERED.getResponseMessage());
				} else {
					userMasterCreateDTO.setPassword(passwordEncoder.encode(defaultUserPassword));
					iUserMasterServiceDAO.createUser(userMasterCreateDTO);
						customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
								StatusMaster.SUCCESS.getResponseMessage());

				}

			}
			else {
				customReponseStatus = new CustomReponseStatus(StatusMaster.INVALIDINPUTREQUESTDTA.getResponseCode(),
						StatusMaster.INVALIDINPUTREQUESTDTA.getResponseMessage());
			}

		} catch (Exception sqx) {
			LOGGER.error("SQLException Occur in registerUser {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO upadteUser(UserMasterModifyDTO userMasterModifyDTO) {
		
		LOGGER.info("Executing  updateUserStatus() method of IUserMasterServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		Map<String,Object> userMoobileAndEmailDetail=null;
		long mobileNumber=0;
		String email="";
		try {
			if(userMasterModifyDTO!=null)
				if (StringUtils.isNotBlank(userMasterModifyDTO.getEmail()) && userMasterModifyDTO.getMobileNumber() != 0 && userMasterModifyDTO.getUserId()!=0) {
					userMoobileAndEmailDetail = iUserMasterServiceDAO
							.getUserByMobileAndEmail(userMasterModifyDTO.getMobileNumber(),userMasterModifyDTO.getEmail(),userMasterModifyDTO.getUserId(),true);
					
					if(userMoobileAndEmailDetail!=null)	{
						mobileNumber=userMoobileAndEmailDetail.get("user_mobile")!=null?(Long)userMoobileAndEmailDetail.get("user_mobile"):0;
						email=userMoobileAndEmailDetail.get("user_email")!=null?(String)userMoobileAndEmailDetail.get("user_email"):"";
					}
					
					if (mobileNumber==userMasterModifyDTO.getMobileNumber()) {
						customReponseStatus = new CustomReponseStatus(StatusMaster.MOBILEALREADYREGISTERED.getResponseCode(),
								StatusMaster.MOBILEALREADYREGISTERED.getResponseMessage());
					} else if (email.equalsIgnoreCase(userMasterModifyDTO.getEmail().trim())) {
						customReponseStatus = new CustomReponseStatus(StatusMaster.EMAILALREADYREGISTERED.getResponseCode(),
								StatusMaster.EMAILALREADYREGISTERED.getResponseMessage());
					} else {
						iUserMasterServiceDAO.updateUser(userMasterModifyDTO);
						customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
								StatusMaster.SUCCESS.getResponseMessage());	
					}
				
				}
			
			
		} catch (Exception ex) {
			LOGGER.error("Exception Occur in getAllRole {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			
		} finally {
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO updateUserStatus(UserMasterUpdateStatusDTO userMasterUpdateStatusDTO) {
		
		LOGGER.info("Executing  updateUserStatus() method of IUserMasterServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		
		try {
			if(userMasterUpdateStatusDTO!=null)
				iUserMasterServiceDAO.updateUserStatus(userMasterUpdateStatusDTO);
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());	
		} catch (Exception ex) {
			LOGGER.error("Exception Occur in getAllRole {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			
		} finally {
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO getAllUsers(int pageIndex, int totalNumberOfRecords) {
		LOGGER.info("Executing  getAllRole() method of ServiceResponseDTO");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<UserMasterFetchDTO> allUsers = null;
		Long count = 0L;
		int slNo[] = { 0 };
		try {
			allUsers = iUserMasterServiceDAO.fetchUserDetails(pageIndex, totalNumberOfRecords);
			if (allUsers != null && allUsers.size() > 0) {
				allUsers.forEach(item -> {
					slNo[0]++;
					item.setSlNo(slNo[0]);
				});
			}
			count = iUserMasterServiceDAO.countAllUser();
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.USER_MASTER_DATA_KEY.key(), allUsers);
			response.put(ResponseKeyValue.USER_MASTER_ALL_DATA_COUNT_KEY.key(), count);
		} catch (Exception ex) {
			LOGGER.error("Exception Occur in getAllRole {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		} finally {
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	/*
	 * @Autowired(required=true) private BCryptPasswordEncoder passwordEncoder;
	 */

	/*
	 * @Override public ServiceResponseDTO registerUser(UserMasterDTO userMasterDTO)
	 * { LOGGER.info("Executing  validateLogin() method of LoginServiceImpl");
	 * LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
	 * ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
	 * CustomReponseStatus customReponseStatus = null; int count = 0; if
	 * (userMasterDTO.getUserMobile() != null & userMasterDTO.getUserEmail() !=
	 * null) { String userExistQuery = env.getProperty("userExistQuery"); int
	 * emailCount = jdbcTemplate.queryForObject(userExistQuery, new Object[] {
	 * userMasterDTO.getUserEmail(), "" }, Integer.class); int mobileCount =
	 * jdbcTemplate.queryForObject(userExistQuery, new Object[] { "",
	 * userMasterDTO.getUserMobile() }, Integer.class); if (mobileCount > 0) {
	 * customReponseStatus = new
	 * CustomReponseStatus(StatusMaster.MOBILEALREADYREGISTERED.getResponseCode(),
	 * StatusMaster.MOBILEALREADYREGISTERED.getResponseMessage()); } else if
	 * (emailCount > 0) { customReponseStatus = new
	 * CustomReponseStatus(StatusMaster.EMAILALREADYREGISTERED.getResponseCode(),
	 * StatusMaster.EMAILALREADYREGISTERED.getResponseMessage()); } else { String
	 * registerUserQuery = env.getProperty("registerUserQuery"); /* Default Random
	 * Password
	 */
	// String encryptedPassword =
	// passwordEncoder.encode(CommonUtility.generatedRandomPassword());
	/*
	 * userMasterDTO.setPassword(CommonUtility.generatedRandomPassword()); count =
	 * jdbcTemplate.update(registerUserQuery, new Object[] {
	 * userMasterDTO.getUserName(), userMasterDTO.getUserMobile(),
	 * userMasterDTO.getUserEmail(), userMasterDTO.getPassword(),
	 * userMasterDTO.getRoleId(), userMasterDTO.getIsFirstLogin() }); if (count > 0)
	 * { customReponseStatus = new
	 * CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
	 * StatusMaster.SUCCESS.getResponseMessage()); } else { customReponseStatus =
	 * new CustomReponseStatus(StatusMaster.USERNOTREGISTERED.getResponseCode(),
	 * StatusMaster.USERNOTREGISTERED.getResponseMessage()); } } } else {
	 */
	/*
	 * Custom response message
	 */
	/*
	 * customReponseStatus = new
	 * CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
	 * StatusMaster.FAILED.getResponseMessage()); }
	 * response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(),
	 * customReponseStatus); serviceResponse.setServiceResponse(response); return
	 * serviceResponse; }
	 */

}