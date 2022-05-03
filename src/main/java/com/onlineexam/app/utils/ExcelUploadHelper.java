package com.onlineexam.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.onlineexam.app.constants.DifficultyMaster;
import com.onlineexam.app.constants.OptionMaster;
import com.onlineexam.app.dto.request.question.QuestionCreateDTO;
import com.onlineexam.app.dto.response.master.ClassDTO;
import com.onlineexam.app.dto.response.master.CourseDTO;
import com.onlineexam.app.dto.response.master.DivisionDTO;
import com.onlineexam.app.dto.response.master.SubSubjectDTO;
import com.onlineexam.app.dto.response.master.SubjectDTO;
import com.onlineexam.app.dto.response.master.TopicDTO;
import com.onlineexam.app.service.impl.CacheService;

@Service
public class ExcelUploadHelper {

	Workbook workbook;

	@Value("${app.upload.file:${user.home}}")
	public String EXCEL_FILE_PATH;

	@Autowired
	CacheService cacheManager;

	private final Map<String, String> columnConfigurationMap = new HashedMap<String, String>();

	{
		columnConfigurationMap.put("", "");
		columnConfigurationMap.put("", "");
		columnConfigurationMap.put("", "");
		columnConfigurationMap.put("", "");
		columnConfigurationMap.put("", "");
		columnConfigurationMap.put("", "");
		columnConfigurationMap.put("", "");
		columnConfigurationMap.put("", "");
		columnConfigurationMap.put("", "");
		columnConfigurationMap.put("", "");
	}

	public List<Object> getExcelDataAsList(InputStream fis, String fileType, Class<?> T, long userId) {
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		DataFormatter dataFormatter = new DataFormatter();
		List<Object> finalDataList = null;
		List<String> headersList = new ArrayList<String>();
		try {
			workbook = new XSSFWorkbook();
			workbook = WorkbookFactory.create(fis);
			System.out.println("-------Workbook has '" + workbook.getNumberOfSheets() + "' Sheets-----");
			Sheet sheet = workbook.getSheetAt(0);
			int noOfColumns = sheet.getRow(0).getLastCellNum();
			System.out.println("-------Sheet has '" + noOfColumns + "' columns------");
			for (Row row : sheet) {
				Map<String, String> excelMap = new LinkedHashMap<String, String>();
				for (Cell cell : row) {
					String cellValue = dataFormatter.formatCellValue(cell);
					if (row.getRowNum() == 0) {
						if (!StringUtils.isEmpty(cellValue))
							headersList.add(cellValue);
					} else {
						if (!StringUtils.isEmpty(cellValue)) {
							int columnIndex = cell.getColumnIndex();
							excelMap.put(headersList.get(columnIndex), cellValue);
						}
					}
				}
				if (row.getRowNum() != 0 && (excelMap != null && !excelMap.isEmpty()))
					dataList.add(excelMap);
			}
			finalDataList = createList(dataList, noOfColumns, fileType, T, userId);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return finalDataList;
	}

	private List<Object> createList(List<Map<String, String>> excelData, int noOfColumns, String fileType, Class<?> T,
			long userId) {
		ArrayList<Object> objectList = new ArrayList<Object>();
		if (fileType.equalsIgnoreCase("question_master")) {
			List<CourseDTO> courseDTOs = cacheManager.getCourseDataList();
			List<DivisionDTO> divisionDTO = cacheManager.getDivisionDataList();
			List<ClassDTO> classDTOs = cacheManager.getClassDataList();
			List<SubjectDTO> subjectData = cacheManager.getSubjectDataList();
			List<SubSubjectDTO> subSubjectDTO = cacheManager.getSubSubjectDataList();
			List<TopicDTO> topicDTOs = cacheManager.getTopicDataList();

			for (Map<String, String> data : excelData) {
				QuestionCreateDTO questionCreateDTO = new QuestionCreateDTO();
				questionCreateDTO.setUserId(userId);
				data.forEach((key, value) -> {
					if (key.equalsIgnoreCase("Course")) {
						Optional<CourseDTO> course = courseDTOs.stream()
								.filter(cour -> cour.getCourseName().equalsIgnoreCase(value.trim())).findAny();
						if (course != null && course.get() != null)
							questionCreateDTO.setCourseId(course.get().getCourseId());

					} else if (key.equalsIgnoreCase("Stream")) {
						Optional<DivisionDTO> stream = divisionDTO.stream()
								.filter(divi -> divi.getDivisionName().equalsIgnoreCase(value.trim())).findAny();
						if (stream != null && stream.get() != null)
							questionCreateDTO.setDivisionId(stream.get().getDivisionId());
					} else if (key.equalsIgnoreCase("Class")) {
						Optional<ClassDTO> classDTO = classDTOs.stream()
								.filter(clas -> clas.getClassName().equalsIgnoreCase(value.trim())).findAny();
						if (classDTO != null && classDTO.get() != null)
							questionCreateDTO.setClassId(classDTO.get().getClassId());
					} else if (key.equalsIgnoreCase("Subject")) {
						Optional<SubjectDTO> subject = subjectData.stream()
								.filter(sub -> sub.getSubjectName().equalsIgnoreCase(value.trim())).findAny();
						if (subject != null && subject.get() != null)
							questionCreateDTO.setSubjectId(subject.get().getSubjectId());
					} else if (key.equalsIgnoreCase("Sub Subject")) {
						Optional<SubSubjectDTO> subSubject = subSubjectDTO.stream()
								.filter(sub -> sub.getSubSubjectName().equalsIgnoreCase(value.trim())).findAny();
						if (subSubject != null && subSubject.get() != null)
							questionCreateDTO.setSubSubjectId(subSubject.get().getSubSubjectId());
					} else if (key.equalsIgnoreCase("Topic")) {
						Optional<TopicDTO> topic = topicDTOs.stream()
								.filter(top -> top.getTopicName().equals(value.trim())).findAny();
						if (topic != null && topic.get() != null)
							questionCreateDTO.setTopicId(topic.get().getTopicId());
					} else if (key.equalsIgnoreCase("Question")) {
						questionCreateDTO.setQuestion(value.trim());
					} else if (key.equalsIgnoreCase("Option1")) {
						questionCreateDTO.setOption1(value.trim());
					} else if (key.equalsIgnoreCase("Option2")) {
						questionCreateDTO.setOption2(value.trim());
					} else if (key.equalsIgnoreCase("Option3")) {
						questionCreateDTO.setOption3(value.trim());
					} else if (key.equalsIgnoreCase("Option4")) {
						questionCreateDTO.setOption4(value.trim());
					} else if (key.equalsIgnoreCase("Answer Key")) {
						questionCreateDTO.setAnswerKey(OptionMaster.valueOf(value.trim()).getId());
					} else if (key.equalsIgnoreCase("Difficulty Level")) {
						questionCreateDTO.setDifficultyLevel(DifficultyMaster.valueOf(value.trim()).getId());
					} else if (key.equalsIgnoreCase("Description")) {
						questionCreateDTO.setDescription(value.trim());
					}
				});
				if (questionCreateDTO.getSubjectId() != 0 && questionCreateDTO.getSubSubjectId() != 0
						&& questionCreateDTO.getTopicId() != 0)
					objectList.add(questionCreateDTO);
				else
					break;
			}
		}
		return objectList;
	}

}