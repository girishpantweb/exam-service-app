/**
 * 
 */
package com.onlineexam.app.service.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.onlineexam.app.constants.ResponseKeyValue;
import com.onlineexam.app.constants.StatusMaster;
import com.onlineexam.app.dto.MenuMasterDTO;
import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.pojo.CustomReponseStatus;
import com.onlineexam.app.service.IMenuService;

/**
 * @author Anupam.Bisen
 *
 */
@Service
@PropertySource("classpath:SQL.properties")
public class MenuServiceImpl implements IMenuService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private Environment env;

	@Override
	public ServiceResponseDTO menuMaster() {
		LOGGER.info("Executing  menuMaster() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<MenuMasterDTO> menuMasterList = new LinkedList<MenuMasterDTO>();
		List<MenuMasterDTO> menuWithSubMenuList = null;
		String menuMasterQuery = env.getProperty("menuMasterQuery");
		try {
			menuMasterList = (List<MenuMasterDTO>) jdbcTemplate.query(menuMasterQuery,
					new BeanPropertyRowMapper<MenuMasterDTO>(MenuMasterDTO.class));
			if (menuMasterList != null) {
				menuWithSubMenuList = getMenuWithSubMenu(menuMasterList);

				/*
				 * Custom response message
				 */
				customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
						StatusMaster.SUCCESS.getResponseMessage());
			} else {
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
			}
		} catch (Exception ex) {
			LOGGER.error(" Exception Occur in menuMaster() MenuServiceImpl{}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		} finally {
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.MENU_DETAILS_RESPONSE_KEY.key(), menuWithSubMenuList);
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO menuUserMaster(long roleId) {
		LOGGER.info("Executing  validateLogin() method of LoginServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<MenuMasterDTO> menuMasterList = new LinkedList<MenuMasterDTO>();
		String menuMasterQuery = env.getProperty("usermenuMasterQuery");
		menuMasterList = (List<MenuMasterDTO>) jdbcTemplate.query(menuMasterQuery, new Object[] { roleId },
				new int[] { Types.BIGINT }, new BeanPropertyRowMapper<MenuMasterDTO>(MenuMasterDTO.class));
		boolean successFlag = false;
		if (menuMasterList.size() > 0) {
			List<MenuMasterDTO> finalMasterList = getMenuWithSubMenu(menuMasterList);
			if (finalMasterList.size() > 0) {
				response.put(ResponseKeyValue.MENU_DETAILS_RESPONSE_KEY.key(), finalMasterList);
				successFlag = true;
			}
		}
		if (successFlag) {
			/*
			 * Custom response message
			 */
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		} else {
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	private List<MenuMasterDTO> getMenuWithSubMenu(List<MenuMasterDTO> menuMasterList) {
		int[] slNo = { 0 };
		List<MenuMasterDTO> subMenuList = menuMasterList.stream().filter(subMenu -> subMenu.getMenuParentId() > 0)
				.collect(Collectors.toList());
		List<MenuMasterDTO> menuList = menuMasterList.stream().filter(menu -> menu.getMenuParentId() <= 0)
				.collect(Collectors.toList());
		List<MenuMasterDTO> finalMasterList = new ArrayList<MenuMasterDTO>();
		menuList.forEach(parentMenu -> {
			slNo[0]++;
			List<MenuMasterDTO> childSubMenu = subMenuList.stream()
					.filter(subMenuData -> parentMenu.getMenuId() == subMenuData.getMenuParentId())
					.collect(Collectors.toList());
			if (childSubMenu.size() > 0) {
				List<MenuMasterDTO> subChildList = new ArrayList<MenuMasterDTO>();
				childSubMenu.forEach(childSubMenuData -> {
					MenuMasterDTO menuDTO = addSubMenuChildrens(childSubMenuData, subMenuList, false);
					subChildList.add(menuDTO);
				});
				parentMenu.setSubMenus(subChildList);
			}

			parentMenu.setSlNo(slNo[0]);
			finalMasterList.add(parentMenu);
		});
		return finalMasterList;
	}

	private MenuMasterDTO addSubMenuChildrens(MenuMasterDTO parentSubMenuData, List<MenuMasterDTO> subMenuList,
			boolean isRecurrsive) {
		List<MenuMasterDTO> subChildSubMenu = subMenuList.stream()
				.filter(subMenuData -> parentSubMenuData.getMenuId() == subMenuData.getMenuParentId())
				.collect(Collectors.toList());
		if (subChildSubMenu.size() > 0) {
			List<MenuMasterDTO> subChildList = new ArrayList<MenuMasterDTO>();
			subChildSubMenu.forEach(subMenu -> {
				MenuMasterDTO menuDTO = addSubMenuChildrens(subMenu, subMenuList, true);
				subChildList.add(menuDTO);
			});
			parentSubMenuData.setSubMenus(subChildList);
		}
		return parentSubMenuData;
	}
}