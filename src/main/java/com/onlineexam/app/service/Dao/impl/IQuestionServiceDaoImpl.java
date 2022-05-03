package com.onlineexam.app.service.Dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.onlineexam.app.constants.DifficultyMaster;
import com.onlineexam.app.dto.request.question.QuestionCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionModifyDTO;
import com.onlineexam.app.dto.response.master.ClassDTO;
import com.onlineexam.app.dto.response.master.CourseDTO;
import com.onlineexam.app.dto.response.master.DivisionDTO;
import com.onlineexam.app.dto.response.master.SubSubjectDTO;
import com.onlineexam.app.dto.response.master.SubjectDTO;
import com.onlineexam.app.dto.response.master.TopicDTO;
import com.onlineexam.app.dto.response.question.DifficultyDTO;
import com.onlineexam.app.dto.response.question.QuestionDTO;
import com.onlineexam.app.service.Dao.IQuestionServiceDao;
import com.onlineexam.app.utils.CommonUtility;

@Repository("questionServiceDaoImpl")
public class IQuestionServiceDaoImpl implements IQuestionServiceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private Environment env;

	@Override
	public List<QuestionDTO> getAllQuestions(int pageIndex, int totalRecords) {
		String query = "";
		if (pageIndex == 0 && totalRecords == 0)
			query = env.getProperty("fetchQuestionMasterQuery") + " where qm.active_status=1";
		else
			query = env.getProperty("fetchQuestionMasterQuery") + "  limit " + (pageIndex * totalRecords) + " , "
					+ totalRecords;

		return jdbcTemplate.query(query, new RowMapper<QuestionDTO>() {
			@Override
			public QuestionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				QuestionDTO questionDTO = new QuestionDTO();
				ClassDTO classDTO = new ClassDTO();
				classDTO.setClassId(rs.getLong("class_id"));
				classDTO.setClassName(rs.getString("class_name"));
				CourseDTO courseDTO = new CourseDTO();
				courseDTO.setCourseId(rs.getLong("course_id"));
				courseDTO.setCourseName(rs.getString("course_name"));
				DivisionDTO divisionDTO = new DivisionDTO();
				divisionDTO.setDivisionId(rs.getLong("division_id"));
				divisionDTO.setDivisionName(rs.getString("division_name"));
				SubjectDTO subjectDTO = new SubjectDTO();
				subjectDTO.setSubjectId(rs.getLong("subject_id"));
				subjectDTO.setSubjectCode(rs.getString("subject_code"));
				subjectDTO.setSubjectName(rs.getString("subject_name"));
				SubSubjectDTO subSubjectDTO = new SubSubjectDTO();
				subSubjectDTO.setSubSubjectId(rs.getLong("sub_subject_id"));
				subSubjectDTO.setSubSubjectCode(rs.getString("sub_subject_code"));
				subSubjectDTO.setSubSubjectName(rs.getString("sub_subject_name"));
				subSubjectDTO.setSubjectDTO(subjectDTO);
				TopicDTO topicDTO = new TopicDTO();
				topicDTO.setTopicId(rs.getLong("topic_id"));
				topicDTO.setTopicCode(rs.getString("topic_name"));
				topicDTO.setTopicName(rs.getString("topic_code"));
				topicDTO.setSubjectDTO(subjectDTO);
				topicDTO.setSubSubjectDTO(subSubjectDTO);
				questionDTO.setCourseDTO(courseDTO);
				questionDTO.setDivisionDTO(divisionDTO);
				questionDTO.setClassDTO(classDTO);
				questionDTO.setSubject(subjectDTO);
				questionDTO.setSubSubject(subSubjectDTO);
				questionDTO.setTopic(topicDTO);
				questionDTO.setQuestionId(rs.getLong("question_id"));
				questionDTO.setQuestion(rs.getString("question"));
				questionDTO.setOption1(rs.getString("option1"));
				questionDTO.setOption2(rs.getString("option2"));
				questionDTO.setOption3(rs.getString("option3"));
				questionDTO.setOption4(rs.getString("option4"));
				questionDTO.setAnswerKey(rs.getString("answer_key"));
				questionDTO.setDescription(rs.getString("description"));
				DifficultyMaster df = DifficultyMaster.getDicultyFromId(rs.getInt("difficulty_level"));
				DifficultyDTO difficultyDTO = new DifficultyDTO();
				difficultyDTO.setId(df.getId());
				difficultyDTO.setValue(df.getValue());
				questionDTO.setDifficultyDTO(difficultyDTO);
				questionDTO.setUserName(rs.getString("user_name"));
				questionDTO.setActiveStatus(rs.getInt("active_status"));
				return questionDTO;
			}
		});
	}

	@Override
	public Map<Long, List<QuestionDTO>> getAllQuestionsBySubSubjectId(int pageIndex, int totalRecords,
			String subSubjectId) {
		String query = "";
		if (pageIndex == 0 && totalRecords == 0)
			query = env.getProperty("fetchQuestionMasterQuery") + " where qm.sub_subject_id in (" + subSubjectId
					+ ") AND qm.active_status=1";
		else
			query = env.getProperty("fetchQuestionMasterQuery") + "  where qm.sub_subject_id in (" + subSubjectId
					+ ") limit " + (pageIndex * totalRecords) + " , " + totalRecords;
		Map<Long, List<QuestionDTO>> mapData = new HashedMap<Long, List<QuestionDTO>>();
		return jdbcTemplate.query(query, new ResultSetExtractor<Map<Long, List<QuestionDTO>>>() {
			@Override
			public Map<Long, List<QuestionDTO>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					QuestionDTO questionDTO = new QuestionDTO();
					questionDTO.setQuestionId(rs.getLong("question_id"));
					List<QuestionDTO> dataList = null;
					if (mapData.get(rs.getLong("sub_subject_id")) != null) {
						dataList = mapData.get(rs.getLong("sub_subject_id"));
					} else {
						dataList = new ArrayList<QuestionDTO>();
					}
					dataList.add(questionDTO);
					mapData.put(rs.getLong("sub_subject_id"), dataList);
				}
				return mapData;
			}
		});
	}

	@Override
	public Map<Long, List<QuestionDTO>> getAllQuestionsBySubjectId(int pageIndex, int totalRecords, String subjectId) {
		String query = "";
		if (pageIndex == 0 && totalRecords == 0)
			query = env.getProperty("fetchQuestionMasterQuery") + " where qm.subject_id = " + subjectId
					+ " AND qm.active_status=1";
		else
			query = env.getProperty("fetchQuestionMasterQuery") + "  where qm.subject_id = " + subjectId
					+ " AND qm.active_status=1 limit " + (pageIndex * totalRecords) + " , " + totalRecords;
		Map<Long, List<QuestionDTO>> mapData = new HashedMap<Long, List<QuestionDTO>>();
		return jdbcTemplate.query(query, new ResultSetExtractor<Map<Long, List<QuestionDTO>>>() {
			@Override
			public Map<Long, List<QuestionDTO>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					QuestionDTO questionDTO = new QuestionDTO();
					questionDTO.setQuestionId(rs.getLong("question_id"));
					List<QuestionDTO> dataList = null;
					if (mapData.get(rs.getLong("subject_id")) != null) {
						dataList = mapData.get(rs.getLong("subject_id"));
					} else {
						dataList = new ArrayList<QuestionDTO>();
					}
					dataList.add(questionDTO);
					mapData.put(rs.getLong("subject_id"), dataList);
				}
				return mapData;
			}
		});
	}

	@Override
	public List<QuestionDTO> getAllQuestionsByFilter(int pageIndex, int totalRecords, Map<String, String> filters) {
		StringBuilder query = new StringBuilder();
		if (filters == null) {
			if (pageIndex == 0 && totalRecords == 0)
				query.append(env.getProperty("fetchQuestionMasterQuery")).append(" where qm.active_status=1");
			else
				query.append(env.getProperty("fetchQuestionMasterQuery"))
						.append("  limit " + (pageIndex * totalRecords) + " , ").append(totalRecords);
		} else {
			query.append(env.getProperty("fetchQuestionMasterQuery")).append(" where qm.active_status=1 ");
			filters.forEach((key, value) -> {
				if (key.equals("subjectId")) {
					query.append(" and qm.subject_id =").append(value);
				}
			});
		}
		return jdbcTemplate.query(query.toString(), new RowMapper<QuestionDTO>() {
			@Override
			public QuestionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				QuestionDTO questionDTO = new QuestionDTO();
				ClassDTO classDTO = new ClassDTO();
				classDTO.setClassId(rs.getLong("class_id"));
				classDTO.setClassName(rs.getString("class_name"));
				CourseDTO courseDTO = new CourseDTO();
				courseDTO.setCourseId(rs.getLong("course_id"));
				courseDTO.setCourseName(rs.getString("course_name"));
				DivisionDTO divisionDTO = new DivisionDTO();
				divisionDTO.setDivisionId(rs.getLong("division_id"));
				divisionDTO.setDivisionName(rs.getString("division_name"));
				SubjectDTO subjectDTO = new SubjectDTO();
				subjectDTO.setSubjectId(rs.getLong("subject_id"));
				subjectDTO.setSubjectCode(rs.getString("subject_code"));
				subjectDTO.setSubjectName(rs.getString("subject_name"));
				SubSubjectDTO subSubjectDTO = new SubSubjectDTO();
				subSubjectDTO.setSubSubjectId(rs.getLong("sub_subject_id"));
				subSubjectDTO.setSubSubjectCode(rs.getString("sub_subject_code"));
				subSubjectDTO.setSubSubjectName(rs.getString("sub_subject_name"));
				subSubjectDTO.setSubjectDTO(subjectDTO);
				TopicDTO topicDTO = new TopicDTO();
				topicDTO.setTopicId(rs.getLong("topic_id"));
				topicDTO.setTopicCode(rs.getString("topic_name"));
				topicDTO.setTopicName(rs.getString("topic_code"));
				topicDTO.setSubjectDTO(subjectDTO);
				topicDTO.setSubSubjectDTO(subSubjectDTO);
				questionDTO.setCourseDTO(courseDTO);
				questionDTO.setDivisionDTO(divisionDTO);
				questionDTO.setClassDTO(classDTO);
				questionDTO.setSubject(subjectDTO);
				questionDTO.setSubSubject(subSubjectDTO);
				questionDTO.setTopic(topicDTO);
				questionDTO.setQuestionId(rs.getLong("question_id"));
				questionDTO.setQuestion(rs.getString("question"));
				questionDTO.setOption1(rs.getString("option1"));
				questionDTO.setOption2(rs.getString("option2"));
				questionDTO.setOption3(rs.getString("option3"));
				questionDTO.setOption4(rs.getString("option4"));
				questionDTO.setAnswerKey(rs.getString("answer_key"));
				questionDTO.setDescription(rs.getString("description"));
				DifficultyMaster df = DifficultyMaster.getDicultyFromId(rs.getInt("difficulty_level"));
				DifficultyDTO difficultyDTO = new DifficultyDTO();
				difficultyDTO.setId(df.getId());
				difficultyDTO.setValue(df.getValue());
				questionDTO.setDifficultyDTO(difficultyDTO);
				questionDTO.setUserName(rs.getString("user_name"));
				questionDTO.setActiveStatus(rs.getInt("active_status"));
				return questionDTO;
			}
		});
	}

	@Override
	public List<QuestionDTO> getAllQuestionIdByFilter(int pageIndex, int totalRecords, Map<String, String> filters) {
		StringBuilder query = new StringBuilder();
		if (filters == null) {
			if (pageIndex == 0 && totalRecords == 0)
				query.append(env.getProperty("fetchQuestionMasterQuery")).append(" where qm.active_status=1");
			else
				query.append(env.getProperty("fetchQuestionMasterQuery"))
						.append("  limit " + (pageIndex * totalRecords) + " , ").append(totalRecords);
		} else {
			query.append(env.getProperty("fetchQuestionMasterQuery")).append(" where qm.active_status=1 ");
			filters.forEach((key, value) -> {
				if (key.equals("subjectId")) {
					query.append(" and qm.subject_id =").append(value);
				}
			});
		}
		return jdbcTemplate.query(query.toString(), new RowMapper<QuestionDTO>() {
			@Override
			public QuestionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				QuestionDTO questionDTO = new QuestionDTO();
				questionDTO.setQuestionId(rs.getLong("question_id"));
				return questionDTO;
			}
		});
	}

	@Override
	public Integer getTotalQuestions() {
		String countQuery = env.getProperty("countQuestionMasterQuery");
		return jdbcTemplate.queryForObject(countQuery, Integer.class);
	}

	@Override
	public Integer findQuestionsCountByQuestionsName(String questionName) throws SQLException {
		String checkDublicateQuery = env.getProperty("countForQuestionDuplicacyQuery");
		return jdbcTemplate.queryForObject(checkDublicateQuery, new Object[] { questionName },
				new int[] { Types.VARCHAR }, Integer.class);
	}

	@Override
	public int saveQuestion(QuestionCreateDTO questionCreateDTO) throws SQLException {
		String saveMasterQuery = env.getProperty("saveQuestionQuery");
		return jdbcTemplate.update(saveMasterQuery, new Object[] { questionCreateDTO.getCourseId(),
				questionCreateDTO.getDivisionId(), questionCreateDTO.getClassId(), questionCreateDTO.getSubjectId(),
				questionCreateDTO.getSubSubjectId(), questionCreateDTO.getTopicId(), questionCreateDTO.getQuestion(),
				questionCreateDTO.getOption1(), questionCreateDTO.getOption2(), questionCreateDTO.getOption3(),
				questionCreateDTO.getOption4(), questionCreateDTO.getAnswerKey(), questionCreateDTO.getDescription(),
				questionCreateDTO.getDifficultyLevel(), questionCreateDTO.getUserId() });
	}

	@Override
	public int updateQuestion(QuestionModifyDTO questionModifyDTO) throws SQLException {
		String updateMasterQuery = env.getProperty("updateQuestionQuery");
		return jdbcTemplate.update(updateMasterQuery, questionModifyDTO.getCourseId(),
				questionModifyDTO.getDivisionId(), questionModifyDTO.getClassId(), questionModifyDTO.getSubjectId(),
				questionModifyDTO.getSubSubjectId(), questionModifyDTO.getTopicId(), questionModifyDTO.getQuestion(),
				questionModifyDTO.getOption1(), questionModifyDTO.getOption2(), questionModifyDTO.getOption3(),
				questionModifyDTO.getOption4(), questionModifyDTO.getAnswerKey(), questionModifyDTO.getDescription(),
				questionModifyDTO.getDifficultyLevel(), CommonUtility.getCurrentDateTime(),
				questionModifyDTO.getUserId(), questionModifyDTO.getQuestionId());
	}

	@Override
	public int deleteQuestion(QuestionDeleteDTO questionDeleteDTO) throws SQLException {
		String deleteMasterQuery = env.getProperty("deleteQuestionQuery");
		return jdbcTemplate.update(deleteMasterQuery, questionDeleteDTO.getActiveStatus(),
				questionDeleteDTO.getUserId(), CommonUtility.getCurrentDateTime(), questionDeleteDTO.getQuestionId());
	}

	@Override
	public int[] saveAllQuestions(List<Object> questionsList) {
		String saveMasterQuery = env.getProperty("saveQuestionQuery");
		return jdbcTemplate.batchUpdate(saveMasterQuery, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				QuestionCreateDTO questData = (QuestionCreateDTO) questionsList.get(i);
				ps.setLong(1, questData.getCourseId());
				ps.setLong(2, questData.getDivisionId());
				ps.setLong(3, questData.getClassId());
				ps.setLong(4, questData.getSubjectId());
				ps.setLong(5, questData.getSubSubjectId());
				ps.setLong(6, questData.getTopicId());
				ps.setString(7, questData.getQuestion());
				ps.setString(8, questData.getOption1());
				ps.setString(9, questData.getOption2());
				ps.setString(10, questData.getOption3());
				ps.setString(11, questData.getOption4());
				ps.setInt(12, questData.getAnswerKey());
				ps.setString(13, questData.getDescription());
				ps.setInt(14, questData.getDifficultyLevel());
				ps.setLong(15, questData.getUserId());
			}

			public int getBatchSize() {
				return questionsList.size();
			}
		});
	}
}