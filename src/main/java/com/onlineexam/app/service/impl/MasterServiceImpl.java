package com.onlineexam.app.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.onlineexam.app.constants.ResponseKeyValue;
import com.onlineexam.app.constants.StatusMaster;
import com.onlineexam.app.dto.ServiceResponseDTO;
import com.onlineexam.app.dto.request.master.ClassCreateDTO;
import com.onlineexam.app.dto.request.master.ClassDeleteDTO;
import com.onlineexam.app.dto.request.master.ClassModifyDTO;
import com.onlineexam.app.dto.request.master.CourseCreateDTO;
import com.onlineexam.app.dto.request.master.CourseDeleteDTO;
import com.onlineexam.app.dto.request.master.CourseModifyDTO;
import com.onlineexam.app.dto.request.master.DivisionCreateDTO;
import com.onlineexam.app.dto.request.master.DivisionDeleteDTO;
import com.onlineexam.app.dto.request.master.DivisionModifyDTO;
import com.onlineexam.app.dto.request.master.RoleAuthCreateDTO;
import com.onlineexam.app.dto.request.master.RoleAuthModifyDTO;
import com.onlineexam.app.dto.request.master.RoleCreateDTO;
import com.onlineexam.app.dto.request.master.RoleDeleteDTO;
import com.onlineexam.app.dto.request.master.RoleModifyDTO;
import com.onlineexam.app.dto.request.master.SubSubjectCreateDTO;
import com.onlineexam.app.dto.request.master.SubSubjectDeleteDTO;
import com.onlineexam.app.dto.request.master.SubSubjectModifyDTO;
import com.onlineexam.app.dto.request.master.SubjectCreateDTO;
import com.onlineexam.app.dto.request.master.SubjectDeleteDTO;
import com.onlineexam.app.dto.request.master.SubjectModifyDTO;
import com.onlineexam.app.dto.request.master.TopicCreateDTO;
import com.onlineexam.app.dto.request.master.TopicDeleteDTO;
import com.onlineexam.app.dto.request.master.TopicModifyDTO;
import com.onlineexam.app.dto.response.master.ClassDTO;
import com.onlineexam.app.dto.response.master.CourseDTO;
import com.onlineexam.app.dto.response.master.DivisionDTO;
import com.onlineexam.app.dto.response.RoleAuthFetchRes;
import com.onlineexam.app.dto.response.RoleAuthFetctDTO;
import com.onlineexam.app.dto.response.master.RoleDTO;
import com.onlineexam.app.dto.response.master.SubSubjectDTO;
import com.onlineexam.app.dto.response.master.SubjectDTO;
import com.onlineexam.app.dto.response.master.TopicDTO;
import com.onlineexam.app.pojo.CustomReponseStatus;
import com.onlineexam.app.service.IMasterService;
import com.onlineexam.app.service.Dao.IMasterServiceDao;

@Service("IMasterServiceImpl")
public class MasterServiceImpl implements IMasterService {
	
	@Autowired
	private IMasterServiceDao iMasterServiceDao;
	private static final Logger LOGGER = LoggerFactory.getLogger(MasterServiceImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public ServiceResponseDTO getAllRole(int pageIndex, int totalRecords) {
		LOGGER.info("Executing  getAllRole() method of ServiceResponseDTO");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<RoleDTO> dataList = null;
		Integer count = 0;
		int slNo[] = { 0 };
		try {
			dataList = iMasterServiceDao.getAllRole(pageIndex, totalRecords);
			if (dataList != null && dataList.size() > 0) {
				dataList.forEach(item -> {
					slNo[0]++;
					item.setSno(slNo[0]);
				});
			}
			count = iMasterServiceDao.getTotalRoleCount();
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.ROLE_MASTER_DATA_KEY.key(), dataList);
			response.put(ResponseKeyValue.ROLE_MASTER_ALL_DATA_COUNT_KEY.key(), count);
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

	@Override
	public ServiceResponseDTO saveRoles(RoleCreateDTO roleCreateDTO) {
		LOGGER.info("Executing  menuMaster() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		int roleCount = 0;
		boolean isDuplicateData = true;
		try {
			roleCount = iMasterServiceDao.findRoleCountByRoleName(roleCreateDTO.getRoleName().trim());
			if (roleCount == 0)
				status = iMasterServiceDao.saveRole(roleCreateDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in saveUpdateRoles {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		} finally {

		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.ROLEREADYEXIST.getResponseCode(),
						StatusMaster.ROLEREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO updateRoles(RoleModifyDTO roleModifyDTO) {
		LOGGER.info("Executing  menuMaster() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (roleModifyDTO.getRoleId() > 0)
				status = iMasterServiceDao.updateRole(roleModifyDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in saveUpdateRoles {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.ROLEREADYEXIST.getResponseCode(),
						StatusMaster.ROLEREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO deleteRoles(RoleDeleteDTO roleDeleteDTO) {
		LOGGER.info("Executing  menuMaster() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (roleDeleteDTO.getRoleId() > 0)
				status = iMasterServiceDao.deleteRole(roleDeleteDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in saveUpdateRoles {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.ROLEREADYEXIST.getResponseCode(),
						StatusMaster.ROLEREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO fetchRoleAuthByRoleId(int roleId) {
		LOGGER.info("Executing  fetchRoleAuthByRoleId() method of ServiceResponseDTO");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<RoleAuthFetctDTO> rawRoleAuthList = null;
		List<RoleAuthFetctDTO> actualRoleAuthList = null;
		List<RoleAuthFetchRes> resultList = null;
		try {
			rawRoleAuthList = iMasterServiceDao.fetchRoleAuthByRoleId(roleId);
			if (rawRoleAuthList != null && !rawRoleAuthList.isEmpty()) {
				actualRoleAuthList = getMenuWithSubMenu(rawRoleAuthList);
				resultList = convertToParentChildFormate(actualRoleAuthList);
			}

			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.ROLE_AUTHORIZATION_RESPONSE_KEY.key(), resultList);

		} catch (Exception ex) {
			LOGGER.error("Exception Occur in fetchRoleAuthByRoleId {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		} finally {
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	private List<RoleAuthFetctDTO> getMenuWithSubMenu(List<RoleAuthFetctDTO> roleAuthFetchDTO) {

		List<RoleAuthFetctDTO> subMenuList = roleAuthFetchDTO.stream().filter(subMenu -> subMenu.getMenuParentId() > 0)
				.collect(Collectors.toList());
		List<RoleAuthFetctDTO> menuList = roleAuthFetchDTO.stream().filter(menu -> menu.getMenuParentId() <= 0)
				.collect(Collectors.toList());
		List<RoleAuthFetctDTO> finalMasterList = new ArrayList<RoleAuthFetctDTO>();
		menuList.forEach(parentMenu -> {

			List<RoleAuthFetctDTO> childSubMenu = subMenuList.stream()
					.filter(subMenuData -> parentMenu.getMenuId() == subMenuData.getMenuParentId())
					.collect(Collectors.toList());
			if (childSubMenu.size() > 0) {
				List<RoleAuthFetctDTO> subChildList = new ArrayList<RoleAuthFetctDTO>();
				childSubMenu.forEach(childSubMenuData -> {
					RoleAuthFetctDTO menuDTO = addSubMenuChildrens(childSubMenuData, subMenuList, false);
					subChildList.add(menuDTO);
				});
				parentMenu.setSubMenus(subChildList);
			}

			finalMasterList.add(parentMenu);
		});
		return finalMasterList;
	}

	private RoleAuthFetctDTO addSubMenuChildrens(RoleAuthFetctDTO parentSubMenuData, List<RoleAuthFetctDTO> subMenuList,
			boolean isRecurrsive) {
		List<RoleAuthFetctDTO> subChildSubMenu = subMenuList.stream()
				.filter(subMenuData -> parentSubMenuData.getMenuId() == subMenuData.getMenuParentId())
				.collect(Collectors.toList());
		if (subChildSubMenu.size() > 0) {
			List<RoleAuthFetctDTO> subChildList = new ArrayList<RoleAuthFetctDTO>();
			subChildSubMenu.forEach(subMenu -> {
				RoleAuthFetctDTO menuDTO = addSubMenuChildrens(subMenu, subMenuList, true);
				subChildList.add(menuDTO);
			});
			parentSubMenuData.setSubMenus(subChildList);
		}
		return parentSubMenuData;
	}

	private List<RoleAuthFetchRes> convertToParentChildFormate(List<RoleAuthFetctDTO> subMenuList) {
		List<RoleAuthFetchRes> roleAuthFetchResList = new ArrayList<>();

		if (subMenuList != null && !subMenuList.isEmpty()) {
			subMenuList.forEach(item -> {
				roleAuthFetchResList.add(convertToRoleAuthFetchRes(item));
			});
		}

		return roleAuthFetchResList;
	}

	private RoleAuthFetchRes convertToRoleAuthFetchRes(RoleAuthFetctDTO roleAuthFetctDTO) {
		RoleAuthFetchRes roleAuthFetchRes = new RoleAuthFetchRes();
		if (roleAuthFetctDTO != null) {
			roleAuthFetchRes.setParent(roleAuthFetctDTO);
			if (roleAuthFetctDTO.getSubMenus() != null && !roleAuthFetctDTO.getSubMenus().isEmpty()) {
				List<RoleAuthFetchRes> roleAuthFetctDTOList = new ArrayList<>();
				for (int i = 0; i < roleAuthFetctDTO.getSubMenus().size(); i++) {
					RoleAuthFetctDTO roleAuth = (RoleAuthFetctDTO) roleAuthFetctDTO.getSubMenus().get(i);
					if (roleAuth.getSubMenus() != null && !roleAuth.getSubMenus().isEmpty()) {
						roleAuthFetctDTOList.add(convertToRoleAuthFetchRes(roleAuth));
					} else {
						RoleAuthFetchRes RoleAuthFetchRes = new RoleAuthFetchRes();
						RoleAuthFetchRes.setParent(roleAuthFetctDTO.getSubMenus().get(i));
						roleAuthFetctDTOList.add(RoleAuthFetchRes);
					}
				}
				roleAuthFetchRes.setChildren(roleAuthFetctDTOList);
			}
		}
		return roleAuthFetchRes;
	}

	@Transactional
	@Override
	public ServiceResponseDTO updateRoleAuth(List<RoleAuthModifyDTO> roleAuthModifyDTOList) {
		LOGGER.info("Executing  updateRoleAuth() method of MasterServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;

		try {
			if (roleAuthModifyDTOList != null && !roleAuthModifyDTOList.isEmpty())
				iMasterServiceDao.updateRoleAuth(roleAuthModifyDTOList);
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());

		} catch (Exception sqx) {
			LOGGER.error("SQLException Occur in updateRoleAuth {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}

		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Transactional
	@Override
	public ServiceResponseDTO createRoleAuth(List<RoleAuthCreateDTO> roleAuthCreateDTOList) {
		LOGGER.info("Executing  createRoleAuth() method of MasterServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;

		try {
			if (roleAuthCreateDTOList != null && !roleAuthCreateDTOList.isEmpty())
				iMasterServiceDao.createRoleAuth(roleAuthCreateDTOList);
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());

		} catch (Exception sqx) {
			LOGGER.error("SQLException Occur in updateRoleAuth {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}

		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO getAllSubjects(int pageIndex, int totalNumberOfRecords) {
		LOGGER.info("Executing  getAllSubjects() method of ServiceResponseDTO");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<SubjectDTO> allSubjects = null;
		Integer count = 0;
		int slNo[] = { 0 };
		try {
			allSubjects = iMasterServiceDao.getAllSubjects(pageIndex, totalNumberOfRecords);
			if (allSubjects != null && allSubjects.size() > 0) {
				allSubjects.forEach(item -> {
					slNo[0]++;
					item.setSno(slNo[0]);
				});
			}
			count = iMasterServiceDao.getTotalSubjectCount();
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.SUBJECT_MASTER_DATA_KEY.key(), allSubjects);
			response.put(ResponseKeyValue.SUBJECT_MASTER_ALL_DATA_COUNT_KEY.key(), count);
		} catch (Exception ex) {
			LOGGER.error("Exception Occur in getAllSubjects {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		} finally {
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO saveSubjects(SubjectCreateDTO subjectCreateDTO) {
		LOGGER.info("Executing  saveSubjects() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		int subjectCount = 0;
		boolean isDuplicateData = true;
		try {
			subjectCount = iMasterServiceDao.findSubjectCountBySubjectName(subjectCreateDTO.getSubjectName().trim());
			if (subjectCount == 0)
				status = iMasterServiceDao.saveSubject(subjectCreateDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in saveSubjects {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		} finally {

		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.SUBJECTREADYEXIST.getResponseCode(),
						StatusMaster.SUBJECTREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO updateSubjects(SubjectModifyDTO subjectModifyDTO) {
		LOGGER.info("Executing  updateSubjects() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (subjectModifyDTO.getSubjectId() > 0)
				status = iMasterServiceDao.updateSubject(subjectModifyDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in updateSubjects {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.SUBJECTREADYEXIST.getResponseCode(),
						StatusMaster.SUBJECTREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO deleteSubjects(SubjectDeleteDTO subjectDeleteDTO) {
		LOGGER.info("Executing  deleteSubjects() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (subjectDeleteDTO.getSubjectId() > 0)
				status = iMasterServiceDao.deleteSubject(subjectDeleteDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in deleteSubjects {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.SUBJECTREADYEXIST.getResponseCode(),
						StatusMaster.SUBJECTREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO getAllCourses(int pageIndex, int totalNumberOfRecords) {
		LOGGER.info("Executing  getAllCourses() method of ServiceResponseDTO");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<CourseDTO> dataList = null;
		Integer count = 0;
		int slNo[] = { 0 };
		try {
			dataList = iMasterServiceDao.getAllCourse(pageIndex, totalNumberOfRecords);
			if (dataList != null && dataList.size() > 0) {
				dataList.forEach(item -> {
					slNo[0]++;
					item.setSno(slNo[0]);
				});
			}
			count = iMasterServiceDao.getTotalCourseCount();
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.COURSE_MASTER_DATA_KEY.key(), dataList);
			response.put(ResponseKeyValue.COURSE_MASTER_ALL_DATA_COUNT_KEY.key(), count);
		} catch (Exception ex) {
			LOGGER.error("Exception Occur in getAllCourses {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		} finally {
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO saveCourses(CourseCreateDTO courseCreateDTO) {
		LOGGER.info("Executing  saveCourses() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		int roleCount = 0;
		boolean isDuplicateData = true;
		try {
			roleCount = iMasterServiceDao.findRoleCountByRoleName(courseCreateDTO.getCourseName().trim());
			if (roleCount == 0)
				status = iMasterServiceDao.saveCourse(courseCreateDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in saveCourses {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		} finally {

		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.COURSEREADYEXIST.getResponseCode(),
						StatusMaster.COURSEREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO updateCourses(CourseModifyDTO courseModifyDTO) {
		LOGGER.info("Executing  updateCourses() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (courseModifyDTO.getCourseId() > 0)
				status = iMasterServiceDao.updateCourse(courseModifyDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in updateCourses {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.COURSEREADYEXIST.getResponseCode(),
						StatusMaster.COURSEREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO deleteCourses(CourseDeleteDTO courseDeleteDTO) {
		LOGGER.info("Executing  deleteCourses() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (courseDeleteDTO.getCourseId() > 0)
				status = iMasterServiceDao.deleteCourse(courseDeleteDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in deleteCourses {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.COURSEREADYEXIST.getResponseCode(),
						StatusMaster.COURSEREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO getAllDivisions(int pageIndex, int totalNumberOfRecords) {
		LOGGER.info("Executing  getAllDivisions() method of ServiceResponseDTO");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<DivisionDTO> dataList = null;
		Integer count = 0;
		int slNo[] = { 0 };
		try {
			dataList = iMasterServiceDao.getAllDivision(pageIndex, totalNumberOfRecords);
			if (dataList != null && dataList.size() > 0) {
				dataList.forEach(item -> {
					slNo[0]++;
					item.setSno(slNo[0]);
				});
			}
			count = iMasterServiceDao.getTotalDivisionCount();
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.DIVISION_MASTER_DATA_KEY.key(), dataList);
			response.put(ResponseKeyValue.DIVISION_MASTER_ALL_DATA_COUNT_KEY.key(), count);
		} catch (Exception ex) {
			LOGGER.error("Exception Occur in getAllDivisions {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		} finally {
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO saveDivisions(DivisionCreateDTO divisionCreateDTO) {
		LOGGER.info("Executing  saveDivisions() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		int roleCount = 0;
		boolean isDuplicateData = true;
		try {
			roleCount = iMasterServiceDao.findDivisionCountByDivisionName(divisionCreateDTO.getDivisionName().trim());
			if (roleCount == 0)
				status = iMasterServiceDao.saveDivision(divisionCreateDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in saveDivisions {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		} finally {

		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.DIVISIONREADYEXIST.getResponseCode(),
						StatusMaster.DIVISIONREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO updateDivisions(DivisionModifyDTO divisionModifyDTO) {
		LOGGER.info("Executing  updateDivisions() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (divisionModifyDTO.getDivisionId() > 0)
				status = iMasterServiceDao.updateDivision(divisionModifyDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in updateDivisions {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.DIVISIONREADYEXIST.getResponseCode(),
						StatusMaster.DIVISIONREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO deleteDivisions(DivisionDeleteDTO divisionDeleteDTO) {
		LOGGER.info("Executing  deleteDivisions() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (divisionDeleteDTO.getDivisionId() > 0)
				status = iMasterServiceDao.deleteDivision(divisionDeleteDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in deleteDivisions {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.DIVISIONREADYEXIST.getResponseCode(),
						StatusMaster.DIVISIONREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO getAllClasses(int pageIndex, int totalNumberOfRecords) {
		LOGGER.info("Executing  getAllClasses() method of ServiceResponseDTO");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<ClassDTO> dataList = null;
		Integer count = 0;
		int slNo[] = { 0 };
		try {
			dataList = iMasterServiceDao.getAllClass(pageIndex, totalNumberOfRecords);
			if (dataList != null && dataList.size() > 0) {
				dataList.forEach(item -> {
					slNo[0]++;
					item.setSno(slNo[0]);
				});
			}
			count = iMasterServiceDao.getTotalClassCount();
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.CLASS_MASTER_DATA_KEY.key(), dataList);
			response.put(ResponseKeyValue.CLASS_MASTER_ALL_DATA_COUNT_KEY.key(), count);
		} catch (Exception ex) {
			LOGGER.error("Exception Occur in getAllClasses {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		} finally {
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO saveClasses(ClassCreateDTO classCreateDTO) {
		LOGGER.info("Executing  saveClasses() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		int roleCount = 0;
		boolean isDuplicateData = true;
		try {
			roleCount = iMasterServiceDao.findClassCountByClassName(classCreateDTO.getClassName().trim());
			if (roleCount == 0)
				status = iMasterServiceDao.saveClass(classCreateDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in saveClasses {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		} finally {

		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.CLASSREADYEXIST.getResponseCode(),
						StatusMaster.CLASSREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO updateClasses(ClassModifyDTO classModifyDTO) {
		LOGGER.info("Executing  updateClasses() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (classModifyDTO.getClassId() > 0)
				status = iMasterServiceDao.updateClass(classModifyDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in updateClasses {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.CLASSREADYEXIST.getResponseCode(),
						StatusMaster.CLASSREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO deleteClasses(ClassDeleteDTO classDeleteDTO) {
		LOGGER.info("Executing  deleteClasses() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (classDeleteDTO.getClassId() > 0)
				status = iMasterServiceDao.deleteClass(classDeleteDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in deleteClasses {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.CLASSREADYEXIST.getResponseCode(),
						StatusMaster.CLASSREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO getAllSubSubjects(int pageIndex, int totalNumberOfRecords) {
		LOGGER.info("Executing  getAllSubSubjects() method of ServiceResponseDTO");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<SubSubjectDTO> allSubSubjects = null;
		Integer count = 0;
		int slNo[] = { 0 };
		try {
			allSubSubjects = iMasterServiceDao.getAllSubSubjects(pageIndex, totalNumberOfRecords, null);
			if (allSubSubjects != null && allSubSubjects.size() > 0) {
				allSubSubjects.forEach(item -> {
					slNo[0]++;
					item.setSno(slNo[0]);
				});
			}
			count = iMasterServiceDao.getTotalSubSubjectCount();
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.SUB_SUBJECT_MASTER_DATA_KEY.key(), allSubSubjects);
			response.put(ResponseKeyValue.SUB_SUBJECT_MASTER_ALL_DATA_COUNT_KEY.key(), count);
		} catch (Exception ex) {
			LOGGER.error("Exception Occur in getAllSubSubjects {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		} finally {
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO getAllSubSubjectsByFilter(Map<String, String> filters, int pageIndex,
			int totalNumberOfRecords) {
		LOGGER.info("Executing  getAllSubSubjects() method of ServiceResponseDTO");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<SubSubjectDTO> allSubSubjects = null;
		Integer count = 0;
		int slNo[] = { 0 };
		try {
			allSubSubjects = iMasterServiceDao.getAllSubSubjects(pageIndex, totalNumberOfRecords, filters);
			if (allSubSubjects != null && allSubSubjects.size() > 0) {
				allSubSubjects.forEach(item -> {
					slNo[0]++;
					item.setSno(slNo[0]);
				});
			}
			count = iMasterServiceDao.getTotalSubSubjectCount();
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.SUB_SUBJECT_MASTER_DATA_KEY.key(), allSubSubjects);
			response.put(ResponseKeyValue.SUB_SUBJECT_MASTER_ALL_DATA_COUNT_KEY.key(), count);
		} catch (Exception ex) {
			LOGGER.error("Exception Occur in getAllSubSubjects {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		} finally {
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO saveSubSubjects(SubSubjectCreateDTO subjectCreateDTO) {
		LOGGER.info("Executing  saveSubSubjects() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		int roleCount = 0;
		boolean isDuplicateData = true;
		try {
			roleCount = iMasterServiceDao
					.findSubSubjectCountBySubSubjectName(subjectCreateDTO.getSubSubjectName().trim());
			if (roleCount == 0)
				status = iMasterServiceDao.saveSubSubject(subjectCreateDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in saveSubSubjects {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		} finally {

		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.SUBSUBJECTREADYEXIST.getResponseCode(),
						StatusMaster.SUBSUBJECTREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO updateSubSubjects(SubSubjectModifyDTO subSubjectModifyDTO) {
		LOGGER.info("Executing  updateSubSubjects() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (subSubjectModifyDTO.getSubSubjectId() > 0)
				status = iMasterServiceDao.updateSubSubject(subSubjectModifyDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in updateSubSubjects {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.SUBSUBJECTREADYEXIST.getResponseCode(),
						StatusMaster.SUBSUBJECTREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO deleteSubSubjects(SubSubjectDeleteDTO subSubjectDeleteDTO) {
		LOGGER.info("Executing  deleteSubSubjects() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (subSubjectDeleteDTO.getSubSubjectId() > 0)
				status = iMasterServiceDao.deleteSubSubject(subSubjectDeleteDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in deleteSubSubjects {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.SUBSUBJECTREADYEXIST.getResponseCode(),
						StatusMaster.SUBSUBJECTREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO getAllTopics(int pageIndex, int totalNumberOfRecords) {
		LOGGER.info("Executing  getAllTopics() method of ServiceResponseDTO");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<TopicDTO> allTopics = null;
		Integer count = 0;
		int slNo[] = { 0 };
		try {
			allTopics = iMasterServiceDao.getAllTopics(pageIndex, totalNumberOfRecords, null);
			if (allTopics != null && allTopics.size() > 0) {
				allTopics.forEach(item -> {
					slNo[0]++;
					item.setSno(slNo[0]);
				});
			}
			count = iMasterServiceDao.getTotalTopicCount();
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.TOPIC_MASTER_DATA_KEY.key(), allTopics);
			response.put(ResponseKeyValue.TOPIC_MASTER_ALL_DATA_COUNT_KEY.key(), count);
		} catch (Exception ex) {
			LOGGER.error("Exception Occur in getAllTopics {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		} finally {
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO getAllTopicsByFilter(Map<String, String> filters, int pageIndex,
			int totalNumberOfRecords) {
		LOGGER.info("Executing  getAllTopics() method of ServiceResponseDTO");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		List<TopicDTO> allTopics = null;
		Integer count = 0;
		int slNo[] = { 0 };
		try {
			allTopics = iMasterServiceDao.getAllTopics(pageIndex, totalNumberOfRecords, filters);
			if (allTopics != null && allTopics.size() > 0) {
				allTopics.forEach(item -> {
					slNo[0]++;
					item.setSno(slNo[0]);
				});
			}
			count = iMasterServiceDao.getTotalTopicCount();
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
			response.put(ResponseKeyValue.TOPIC_MASTER_DATA_KEY.key(), allTopics);
			response.put(ResponseKeyValue.TOPIC_MASTER_ALL_DATA_COUNT_KEY.key(), count);
		} catch (Exception ex) {
			LOGGER.error("Exception Occur in getAllTopics {}", ex.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
			response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		} finally {
			serviceResponse.setServiceResponse(response);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO saveTopics(TopicCreateDTO topicCreateDTO) {
		LOGGER.info("Executing  saveTopics() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		int roleCount = 0;
		boolean isDuplicateData = true;
		try {
			roleCount = iMasterServiceDao.findTopicCountByTopicName(topicCreateDTO.getTopicName().trim());
			if (roleCount == 0)
				status = iMasterServiceDao.saveTopic(topicCreateDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in saveTopics {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		} finally {

		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.TOPICREADYEXIST.getResponseCode(),
						StatusMaster.TOPICREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO updateTopics(TopicModifyDTO topicModifyDTO) {
		LOGGER.info("Executing  updateTopics() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (topicModifyDTO.getTopicId() > 0)
				status = iMasterServiceDao.updateTopic(topicModifyDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in updateTopics {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.TOPICREADYEXIST.getResponseCode(),
						StatusMaster.TOPICREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponseDTO deleteTopics(TopicDeleteDTO topicDeleteDTO) {
		LOGGER.info("Executing  deleteTopics() method of MenuServiceImpl");
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		ServiceResponseDTO serviceResponse = new ServiceResponseDTO();
		CustomReponseStatus customReponseStatus = null;
		int status = 0;
		boolean isDuplicateData = true;
		try {
			if (topicDeleteDTO.getTopicId() > 0)
				status = iMasterServiceDao.deleteTopic(topicDeleteDTO);
			else
				isDuplicateData = false;
		} catch (SQLException sqx) {
			LOGGER.error("SQLException Occur in deleteTopics {}", sqx.getMessage());
			customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
					StatusMaster.FAILED.getResponseMessage());
		}
		if (status > 0)
			customReponseStatus = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseCode(),
					StatusMaster.SUCCESS.getResponseMessage());
		else {
			if (!isDuplicateData)
				customReponseStatus = new CustomReponseStatus(StatusMaster.TOPICREADYEXIST.getResponseCode(),
						StatusMaster.TOPICREADYEXIST.getResponseMessage());
			else
				customReponseStatus = new CustomReponseStatus(StatusMaster.FAILED.getResponseCode(),
						StatusMaster.FAILED.getResponseMessage());
		}
		response.put(ResponseKeyValue.CUSTOM_RESPONSE_KEY.key(), customReponseStatus);
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}
}
