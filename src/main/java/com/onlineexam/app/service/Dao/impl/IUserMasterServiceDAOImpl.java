package com.onlineexam.app.service.Dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onlineexam.app.dto.request.UserMasterCreateDTO;
import com.onlineexam.app.dto.request.UserMasterModifyDTO;
import com.onlineexam.app.dto.request.UserMasterUpdateStatusDTO;
import com.onlineexam.app.dto.response.UserMasterFetchDTO;
import com.onlineexam.app.service.Dao.IUserMasterServiceDAO;
import com.onlineexam.app.utils.CommonUtility;

@Repository("IUserMasterServiceDAOImpl")
public class IUserMasterServiceDAOImpl implements IUserMasterServiceDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(IUserMasterServiceDAOImpl.class);

	@Autowired
	private Environment env;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	@Override
	public int createUser(UserMasterCreateDTO userMasterCreateDTO) {
		String query = env.getProperty("createUserQuery");
		// Logger.i
		return jdbcTemplate.update(query, userMasterCreateDTO.getUserName(), userMasterCreateDTO.getMobileNumber(),
				userMasterCreateDTO.getEmail(), userMasterCreateDTO.getRoleId(), userMasterCreateDTO.getCreatedBy(),
				userMasterCreateDTO.getCreatedBy(), userMasterCreateDTO.getPassword(),
				CommonUtility.getCurrentDateTime(), CommonUtility.getCurrentDateTime());
	}

	@Transactional
	@Override
	public int updateUser(UserMasterModifyDTO userMasterModifyDTO) {
		String query = env.getProperty("updateUserQuery");
		LOGGER.info("Excecutiong query:{}", query);
		return jdbcTemplate.update(query, userMasterModifyDTO.getUserName(), userMasterModifyDTO.getMobileNumber(),
				userMasterModifyDTO.getEmail(), userMasterModifyDTO.getRoleId(), userMasterModifyDTO.getUpdatedBy(),
				CommonUtility.getCurrentDateTime(), userMasterModifyDTO.getUserId());
	}

	@Transactional
	@Override
	public int updateUserStatus(UserMasterUpdateStatusDTO userMasterUpdateStatusDTO) {
		String query = env.getProperty("updateUserStatusQuery");
		LOGGER.info("Excecutiong query:{}", query);
		return jdbcTemplate.update(query, userMasterUpdateStatusDTO.getUserStatus(),
				userMasterUpdateStatusDTO.getUpdatedBy(), CommonUtility.getCurrentDateTime(),
				userMasterUpdateStatusDTO.getUserId());
	}

	@Override
	public List<UserMasterFetchDTO> fetchUserDetails(int pageNo, int tolatRecord) {
		String query = env.getProperty("fetchUserDetailQuery") + "  limit " + (pageNo * tolatRecord) + " , "
				+ tolatRecord;

		LOGGER.info("Excecutiong query:{}", query);
		return jdbcTemplate.query(query, new RowMapper<UserMasterFetchDTO>() {

			@Override
			public UserMasterFetchDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserMasterFetchDTO userMasterFetchDTO = new UserMasterFetchDTO();
				userMasterFetchDTO.setUserId(rs.getLong("user_id"));
				userMasterFetchDTO.setUserName(rs.getString("user_name"));
				userMasterFetchDTO.setMobileNumber(rs.getLong("user_mobile"));
				userMasterFetchDTO.setEmail(rs.getString("user_email"));
				userMasterFetchDTO.setRoleId(rs.getInt("role_id"));
				userMasterFetchDTO.setRoleName(rs.getString("role_name"));
				userMasterFetchDTO.setUserStatus(rs.getInt("user_status"));
				return userMasterFetchDTO;
			}

		});

	}

	@Override
	public Long countAllUser() {
		String query = env.getProperty("countAllUserQuery");
		return jdbcTemplate.queryForObject(query, Long.class);
	}

	@Override
	public Map<String, Object> getUserByMobileAndEmail(long mobileNumber, String email, long userID,
			boolean isForUpdateUser) {
		Map<String, Object> result = null;
		String query = "";
		Object[] objectArray = null;
		if (isForUpdateUser) {
			query = env.getProperty("fetchUserMobAndEmaiForUpQuery");
			objectArray = new Object[] { mobileNumber, email, userID };
		} else {
			query = env.getProperty("fetchUserByMobileOrEmailQuery");
			objectArray = new Object[] { mobileNumber, email };
		}
		try {
			result = jdbcTemplate.queryForMap(query, objectArray);
		} catch (DataAccessException ex) {
			result = null;
		}
		return result;
	}

	@Override
	public String getPasswordByMobileOrEmail(String email, String mobile) {
		String result = "";
		String userValidateQuery = env.getProperty("userValidateQuery");
		try {
			result = jdbcTemplate.queryForObject(userValidateQuery, new Object[] { email, mobile },
					new int[] { Types.VARCHAR, Types.VARCHAR }, String.class);
		} catch (EmptyResultDataAccessException rs) {
			result = "";
		}
		return result;
	}

	@Override
	public String getPasswordByuserId(long userId) {
		String result = "";
		String userValidateQuery = env.getProperty("getPasswordByUserIdQuery");
		try {
			result = jdbcTemplate.queryForObject(userValidateQuery, new Object[] { userId }, new int[] { Types.BIGINT },
					String.class);
		} catch (EmptyResultDataAccessException rs) {
			result = "";
		}
		return result;
	}

}
