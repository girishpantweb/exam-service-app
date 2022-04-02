/**
 * 
 */
package com.onlineexam.app.service.impl;

import java.sql.Types;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlineexam.app.constants.ResponseKeyValue;
import com.onlineexam.app.constants.StatusMaster;
import com.onlineexam.app.dto.ChangePasswordDTO;
import com.onlineexam.app.dto.RoleAuthorizationDTO;
import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.UserMasterDTO;
import com.onlineexam.app.pojo.CustomReponseStatus;
import com.onlineexam.app.service.ILoginService;
import com.onlineexam.app.service.Dao.IUserMasterServiceDAO;
import com.onlineexam.app.utils.CommonUtility;

/**
 * @author Anupam.Bisen
 *
 */
@Service
@PropertySource("classpath:SQL.properties")
public class LoginServiceImpl implements ILoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private IUserMasterServiceDAO iUserMasterServiceDAO;

	@Autowired
	private Environment env;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ServiceResponseDTO validateLogin(UserMasterDTO userMasterDTO) {
		LOGGER.info("Executing  validateLogin() method of LoginServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<RoleAuthorizationDTO> roleAuthorizationList = new LinkedList<RoleAuthorizationDTO>();
		String password = "";
		if ((userMasterDTO.getUserMobile() != null || userMasterDTO.getUserEmail() != null)
				& userMasterDTO.getPassword() != null) {

			String userDetailsQuery = env.getProperty("userDetailsQuery");
			String roleAuthorizationQuery = env.getProperty("roleAuthorizationQuery");
			try {
				password = iUserMasterServiceDAO.getPasswordByMobileOrEmail(userMasterDTO.getUserEmail(),
						userMasterDTO.getUserMobile());

				if (StringUtils.isNotBlank(password)) {
					if (passwordEncoder.matches(userMasterDTO.getPassword(), password)) {

						userMasterDTO = jdbcTemplate.queryForObject(userDetailsQuery,
								new Object[] { userMasterDTO.getUserEmail(), userMasterDTO.getUserMobile() },
								new int[] { Types.VARCHAR, Types.VARCHAR },
								new BeanPropertyRowMapper<UserMasterDTO>(UserMasterDTO.class));
						if (userMasterDTO != null) {
							roleAuthorizationList = (List<RoleAuthorizationDTO>) jdbcTemplate.query(
									roleAuthorizationQuery, new Object[] { userMasterDTO.getRoleId() },
									new int[] { Types.BIGINT },
									new BeanPropertyRowMapper<RoleAuthorizationDTO>(RoleAuthorizationDTO.class));
						}
						customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
								StatusMaster.SUCCESS.getResponseMessage());

					} else {
						customReponseStatus = new CustomReponseStatus(StatusMaster.PASSWORDNOTVALID.getResponseCode(),
								StatusMaster.PASSWORDNOTVALID.getResponseMessage());
					}

				} else {
					customReponseStatus = new CustomReponseStatus(StatusMaster.EMAILORMOBILEINVALID.getResponseCode(),
							StatusMaster.EMAILORMOBILEINVALID.getResponseMessage());

				}
			} catch (Exception ex) {
				System.err.print(ex.getMessage());
				LOGGER.error("Error Occure In validateLogin method:" + ex);
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
			}
		}

		else {
			/*
			 * Custom response message
			 */
			customReponseStatus = new CustomReponseStatus(StatusMaster.INVALIDINPUTREQUESTDTA.getResponseCode(),
					StatusMaster.INVALIDINPUTREQUESTDTA.getResponseMessage());
		}

		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		response.put(ResponseKeyValue.PERSONAL_DETAILS_RESPONSE_KEY.key(), userMasterDTO);
		response.put(ResponseKeyValue.ROLE_AUTHORIZATION_RESPONSE_KEY.key(), roleAuthorizationList);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;

	}

	@Override
	public ServiceResponseDTO changePassword(ChangePasswordDTO changePasswordDTO) {
		LOGGER.info("Executing  changePassword() method of LoginServiceImpl");

		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		String password = "";
		if (changePasswordDTO != null && StringUtils.isNotBlank(changePasswordDTO.getNewPassword())
				&& StringUtils.isNotBlank(changePasswordDTO.getNewPassword())
				&& StringUtils.isNotBlank(changePasswordDTO.getOldPassword()) && changePasswordDTO.getUserId() != 0) {

			// String userCheckOldPassQuery = env.getProperty("userCheckOldPassQuery");
			String updateUserPassword = env.getProperty("updateUserPassword");
			if (changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())) {
				try {
					password = iUserMasterServiceDAO.getPasswordByuserId(changePasswordDTO.getUserId());

					if (StringUtils.isNotBlank(password)) {
						if (passwordEncoder.matches(changePasswordDTO.getOldPassword(), password)) {
							// System.err.println(passwordEncoder.encode(changePasswordDTO.getConfirmNewPassword()));
							int passUpdatecount = jdbcTemplate.update(updateUserPassword,
									new Object[] { passwordEncoder.encode(changePasswordDTO.getConfirmNewPassword()),
											changePasswordDTO.getUserId(), CommonUtility.getCurrentDateTime(),
											changePasswordDTO.getUserId() });
							if (passUpdatecount > 0) {

								customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
										StatusMaster.SUCCESS.getResponseMessage());

							} else {
								customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
										StatusMaster.FAILED.getResponseMessage());
							}

						} else {
							customReponseStatus = new CustomReponseStatus(
									StatusMaster.CHANGEPASSWORDOLD.getResponseCode(),
									StatusMaster.CHANGEPASSWORDOLD.getResponseMessage());
						}
					} else {
						customReponseStatus = new CustomReponseStatus(StatusMaster.INVALIDUSER.getResponseCode(),
								StatusMaster.INVALIDUSER.getResponseMessage());
					}
				}

				catch (Exception ex) {
					LOGGER.error("Error Occure In changePassword method:" + ex);
					customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
							StatusMaster.FAILED.getResponseMessage());
				}
			} else {
				customReponseStatus = new CustomReponseStatus(StatusMaster.CHANGEPASSWORD.getResponseCode(),
						StatusMaster.CHANGEPASSWORD.getResponseMessage());
			}
		}

		else {
			/*
			 * Custom response message
			 */
			customReponseStatus = new CustomReponseStatus(StatusMaster.INVALIDINPUTREQUESTDTA.getResponseCode(),
					StatusMaster.INVALIDINPUTREQUESTDTA.getResponseMessage());

		}

		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;

	}

}