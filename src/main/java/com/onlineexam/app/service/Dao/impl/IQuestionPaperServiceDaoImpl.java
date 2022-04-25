package com.onlineexam.app.service.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.onlineexam.app.dto.request.question.ManageSubSetCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperModifyDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperSetCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionSetDTO;
import com.onlineexam.app.dto.request.question.QuestionSubSubjectCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionSubSubjectModifyDTO;
import com.onlineexam.app.dto.request.question.SubSetCreateDTO;
import com.onlineexam.app.dto.request.question.SubSetDeleteDTO;
import com.onlineexam.app.dto.request.question.SubSetModifyDTO;
import com.onlineexam.app.dto.response.KeyValueDTO;
import com.onlineexam.app.dto.response.QuestionPaperSetDTO;
import com.onlineexam.app.dto.response.master.ClassDTO;
import com.onlineexam.app.dto.response.master.CourseDTO;
import com.onlineexam.app.dto.response.master.DivisionDTO;
import com.onlineexam.app.dto.response.master.SubSubjectDTO;
import com.onlineexam.app.dto.response.master.SubjectDTO;
import com.onlineexam.app.dto.response.master.TopicDTO;
import com.onlineexam.app.dto.response.question.QuestionPaperDTO;
import com.onlineexam.app.dto.response.question.QuestionSubSubjectDTO;
import com.onlineexam.app.service.Dao.IQuestionPaperServiceDao;
import com.onlineexam.app.utils.CommonUtility;

@Repository("questionPaperServiceDaoImpl")
public class IQuestionPaperServiceDaoImpl implements IQuestionPaperServiceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private Environment env;

	@Override
	public Integer getTotalQuestionPaper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public long saveQuestionPaper(QuestionPaperCreateDTO questionPaperCreateDTO) throws SQLException {
		String saveMasterQuery = env.getProperty("saveQuestionPaperQuery");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(saveMasterQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, questionPaperCreateDTO.getQuestionYear());
				ps.setString(2, questionPaperCreateDTO.getQuestionPaperCode());
				ps.setLong(3, questionPaperCreateDTO.getClassId());
				ps.setLong(4, questionPaperCreateDTO.getCourseId());
				ps.setLong(5, questionPaperCreateDTO.getDivisionId());
				ps.setLong(6, questionPaperCreateDTO.getSubjectId());
				ps.setInt(7, questionPaperCreateDTO.getNoOfSet());
				ps.setLong(8, questionPaperCreateDTO.getUserId());
				return ps;
			}
		}, keyHolder);
		long questionPaperId = keyHolder.getKey().longValue();
		List<QuestionSubSubjectCreateDTO> questionSubSubjectCreateDTO = questionPaperCreateDTO
				.getQuestionSubSubjectCreateDTO();
		String saveMasterQuery1 = env.getProperty("saveQuestionPaperSubConfigMaster");
		int[] size = jdbcTemplate.batchUpdate(saveMasterQuery1, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, questionPaperId);
				ps.setLong(2, questionSubSubjectCreateDTO.get(i).getSubSubjectId());
				ps.setLong(3, questionSubSubjectCreateDTO.get(i).getNoOfQuestions());
				ps.setLong(4, questionSubSubjectCreateDTO.get(i).getUserId());
			}

			@Override
			public int getBatchSize() {
				return questionSubSubjectCreateDTO.size();
			}
		});
		if (size.length > 0) {
			return questionPaperId;
		} else {
			return 0;
		}
	}

	@Override
	public int[] saveAllQuestionPaper(List<Object> questionList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int updateQuestionPaper(QuestionPaperModifyDTO questionPaperModifyDTO) throws SQLException {
		String saveMasterQuery = env.getProperty("updateQuestionPaperQuery");
		jdbcTemplate.update(saveMasterQuery,
				new Object[] { questionPaperModifyDTO.getQuestionYear(), questionPaperModifyDTO.getQuestionPaperCode(),
						questionPaperModifyDTO.getClassId(), questionPaperModifyDTO.getCourseId(),
						questionPaperModifyDTO.getDivisionId(), questionPaperModifyDTO.getSubjectId(),
						questionPaperModifyDTO.getNoOfSet(), questionPaperModifyDTO.getUserId(),
						questionPaperModifyDTO.getQuestionPaperId() });
		String deleteSubConfig = env.getProperty("deleteQuestionPaperSubConfigMaster");
		jdbcTemplate.update(deleteSubConfig, new Object[] { questionPaperModifyDTO.getQuestionPaperId() },
				new int[] { Types.BIGINT });
		List<QuestionSubSubjectModifyDTO> questionSubSubjectCreateDTO = questionPaperModifyDTO
				.getQuestionSubSubjectModifyDTO();
		String saveMasterQuery1 = env.getProperty("saveQuestionPaperSubConfigMaster");
		int[] size = jdbcTemplate.batchUpdate(saveMasterQuery1, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, questionPaperModifyDTO.getQuestionPaperId());
				ps.setLong(2, questionSubSubjectCreateDTO.get(i).getSubSubjectId());
				ps.setLong(3, questionSubSubjectCreateDTO.get(i).getNoOfQuestions());
				ps.setLong(4, questionSubSubjectCreateDTO.get(i).getUserId());
			}

			@Override
			public int getBatchSize() {
				return questionSubSubjectCreateDTO.size();
			}
		});
		if (size.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteQuestionPaper(QuestionPaperDeleteDTO questionPaperDeleteDTO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] saveQuestionPaperSet(List<QuestionPaperSetCreateDTO> questionPaperSetCreateList) throws SQLException {
		String saveMasterQuery = env.getProperty("saveQuestionPaperSetQuery");
		return jdbcTemplate.batchUpdate(saveMasterQuery, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, questionPaperSetCreateList.get(i).getQuestionPaperId());
				ps.setLong(2, questionPaperSetCreateList.get(i).getSetNo());
				ps.setLong(3, questionPaperSetCreateList.get(i).getQuestionId());
				ps.setLong(4, questionPaperSetCreateList.get(i).getSortNo());
				ps.setLong(5, questionPaperSetCreateList.get(i).getUserId());
			}

			@Override
			public int getBatchSize() {
				return questionPaperSetCreateList.size();
			}
		});
	}

	@Override
	public int saveQuestionSubSet(SubSetCreateDTO subSetCreateDTO) throws SQLException {
		String saveMasterQuery = env.getProperty("saveQuestionPaperSubSetQuery");
		return jdbcTemplate.update(saveMasterQuery,
				new Object[] { subSetCreateDTO.getCourseId(), subSetCreateDTO.getDivisionId(),
						subSetCreateDTO.getClassId(), subSetCreateDTO.getSubjectId(), subSetCreateDTO.getSubSubjectId(),
						subSetCreateDTO.getSubsetName(), subSetCreateDTO.getUserId() });
	}

	@Override
	public int updateQuestionSubSet(SubSetModifyDTO subSetModifyDTO) throws SQLException {
		String updateMasterQuery = env.getProperty("updateQuestionPaperSubSetQuery");
		return jdbcTemplate.update(updateMasterQuery, subSetModifyDTO.getCourseId(), subSetModifyDTO.getDivisionId(),
				subSetModifyDTO.getClassId(), subSetModifyDTO.getSubjectId(), subSetModifyDTO.getSubSubjectId(),
				subSetModifyDTO.getSubsetName(), subSetModifyDTO.getUserId(), subSetModifyDTO.getQuesSubsetId());

	}

	@Override
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int deleteQuestionSubSet(SubSetDeleteDTO subSetDeleteDTO) throws SQLException {
		String deleteMasterQuery = env.getProperty("deleteQuestionPaperSubSetQuery");
		jdbcTemplate.update(deleteMasterQuery, subSetDeleteDTO.getActiveStatus(), subSetDeleteDTO.getUserId(),
				CommonUtility.getCurrentDateTime(), subSetDeleteDTO.getQuesSubsetId());
		String deleteMasterQuery1 = env.getProperty("manageDeleteQuestionPaperSubSetQuery1");
		return jdbcTemplate.update(deleteMasterQuery1, subSetDeleteDTO.getActiveStatus(),
				subSetDeleteDTO.getQuesSubsetId());
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public long manageQuestionSubSet(ManageSubSetCreateDTO manageSubSetCreateDTO) throws SQLException {
		String saveMasterQuery = env.getProperty("manageUpdateQuestionPaperSubSetQuery");
		jdbcTemplate.update(saveMasterQuery, manageSubSetCreateDTO.getQuesSubsetId());
		List<Integer> questionList = manageSubSetCreateDTO.getQuestionId();
		String saveMasterQuery1 = env.getProperty("manageSaveQuestionPaperSubSetQuery");
		int[] size = jdbcTemplate.batchUpdate(saveMasterQuery1, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, manageSubSetCreateDTO.getQuesSubsetId());
				ps.setLong(2, questionList.get(i));
			}

			@Override
			public int getBatchSize() {
				return questionList.size();
			}
		});
		if (size.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<QuestionPaperDTO> getAllQuestionPaper(int pageIndex, int totalRecords) {
		String query = "";
		if (pageIndex == 0 && totalRecords == 0)
			query = env.getProperty("fetchQuestionPaperMasterQuery") + " where qpm.active_status=1";
		else
			query = env.getProperty("fetchQuestionPaperMasterQuery") + "  limit " + (pageIndex * totalRecords) + " , "
					+ totalRecords;

		return jdbcTemplate.query(query, new RowMapper<QuestionPaperDTO>() {
			@Override
			public QuestionPaperDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				QuestionPaperDTO questionPaperDTO = new QuestionPaperDTO();
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
				subjectDTO.setSubjectName(rs.getString("subject_name"));
				questionPaperDTO.setCourseDTO(courseDTO);
				questionPaperDTO.setDivisionDTO(divisionDTO);
				questionPaperDTO.setClassDTO(classDTO);
				questionPaperDTO.setSubjectDTO(subjectDTO);
				questionPaperDTO.setQuestionPaperId(rs.getLong("question_paper_id"));
				questionPaperDTO.setQuestionYear(rs.getString("question_year"));
				questionPaperDTO.setQuestionPaperCode(rs.getString("question_paper_code"));
				questionPaperDTO.setNoofSet(rs.getInt("no_of_set"));
				questionPaperDTO.setUserName(rs.getString("user_name"));
				questionPaperDTO.setActiveStatus(rs.getInt("active_status"));
				return questionPaperDTO;
			}
		});
	}

	@Override
	public QuestionPaperDTO getAllQuestionPaperById(long questionPaperId, long subjectId) {
		String query = "";
		query = env.getProperty("fetchQuestionPaperMasterByPaperId");
		return jdbcTemplate.query(query, new Object[] { questionPaperId, subjectId },
				new int[] { Types.BIGINT, Types.BIGINT }, new ResultSetExtractor<List<QuestionPaperDTO>>() {
					@Override
					public List<QuestionPaperDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
						Map<Long, QuestionPaperDTO> map = new HashedMap<Long, QuestionPaperDTO>();
						while (rs.next()) {
							Long questionPaperId = rs.getLong("question_paper_id");
							QuestionPaperDTO questionPaperDTO = null;
							if (questionPaperId == 0 || questionPaperId == null) {
								Optional<Long> key = map.keySet().stream().findFirst();
								if (key.isPresent()) {
									questionPaperId = key.get();
								}
							}
							questionPaperDTO = map.get(questionPaperId);
							if (questionPaperDTO == null) {
								questionPaperDTO = new QuestionPaperDTO();
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
								subjectDTO.setSubjectName(rs.getString("subject_name"));
								questionPaperDTO.setCourseDTO(courseDTO);
								questionPaperDTO.setDivisionDTO(divisionDTO);
								questionPaperDTO.setClassDTO(classDTO);
								questionPaperDTO.setSubjectDTO(subjectDTO);
								questionPaperDTO.setQuestionPaperId(rs.getLong("question_paper_id"));
								questionPaperDTO.setQuestionYear(rs.getString("question_year"));
								questionPaperDTO.setQuestionPaperCode(rs.getString("question_paper_code"));
								questionPaperDTO.setNoofSet(rs.getInt("no_of_set"));
								questionPaperDTO.setUserName(rs.getString("user_name"));
								questionPaperDTO.setActiveStatus(rs.getInt("active_status"));
								List<QuestionSubSubjectDTO> questionSubSujectList = new ArrayList<QuestionSubSubjectDTO>();
								QuestionSubSubjectDTO questionSubSubjectDTO = new QuestionSubSubjectDTO();
								SubSubjectDTO subSubjectDTO = new SubSubjectDTO();
								subSubjectDTO.setSubSubjectId(rs.getLong("sub_subject_id"));
								subSubjectDTO.setSubSubjectName(rs.getString("sub_subject_name"));
								questionSubSubjectDTO.setSubjectDTO(subSubjectDTO);
								questionSubSubjectDTO.setNoOfQuestion(rs.getLong("no_of_question"));
								questionSubSujectList.add(questionSubSubjectDTO);
								questionPaperDTO.setQuestionSubSubjectDTO(questionSubSujectList);
							} else {
								List<QuestionSubSubjectDTO> questionSubSujectList = questionPaperDTO
										.getQuestionSubSubjectDTO();
								QuestionSubSubjectDTO questionSubSubjectDTO = new QuestionSubSubjectDTO();
								SubSubjectDTO subSubjectDTO = new SubSubjectDTO();
								subSubjectDTO.setSubSubjectId(rs.getLong("sub_subject_id"));
								subSubjectDTO.setSubSubjectName(rs.getString("sub_subject_name"));
								questionSubSubjectDTO.setSubjectDTO(subSubjectDTO);
								questionSubSubjectDTO.setNoOfQuestion(rs.getLong("no_of_question"));
								questionSubSujectList.add(questionSubSubjectDTO);
								questionPaperDTO.setQuestionSubSubjectDTO(questionSubSujectList);
							}
							map.put(questionPaperId, questionPaperDTO);
						}
						System.out.println("Map data" + map);
						return new ArrayList<QuestionPaperDTO>(map.values());
					}
				}).get(0);
	}

	@Override
	public List<QuestionPaperSetDTO> getAllQuestionPaperBySet(QuestionSetDTO questionSetDTO) {
		String query = "";
		query = env.getProperty("fetchQuestionPaperMasterBySetQuery");
		return jdbcTemplate.query(query,
				new Object[] { questionSetDTO.getQuestionPaperId(), questionSetDTO.getSetId() },
				new int[] { Types.BIGINT, Types.BIGINT }, new RowMapper<QuestionPaperSetDTO>() {
					@Override
					public QuestionPaperSetDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						QuestionPaperSetDTO questionPaperSetDTO = new QuestionPaperSetDTO();
						questionPaperSetDTO.setQuestionId(rs.getLong("question_id"));
						questionPaperSetDTO.setSetNo(rs.getLong("set_no"));
						questionPaperSetDTO.setSortNo(rs.getLong("sort_no"));
						questionPaperSetDTO.setQuestion(rs.getString("question"));
						questionPaperSetDTO.setOptionValue(rs.getString("optionval"));
						TopicDTO topicDTO = new TopicDTO();
						topicDTO.setTopicId(rs.getLong("topic_id"));
						topicDTO.setTopicName(rs.getString("topic_name"));
						SubSubjectDTO subSubjectDTO = new SubSubjectDTO();
						subSubjectDTO.setSubSubjectId(rs.getLong("sub_subject_id"));
						subSubjectDTO.setSubSubjectName(rs.getString("sub_subject_name"));
						questionPaperSetDTO.setTopicDTO(topicDTO);
						questionPaperSetDTO.setSubjectDTO(subSubjectDTO);
						questionPaperSetDTO.setSetNo(rs.getLong("set_no"));
						questionPaperSetDTO.setActiveStatus(rs.getInt("active_status"));
						return questionPaperSetDTO;
					}
				});
	}

	@Override
	public List<QuestionPaperDTO> getAllQuestionPaperBySubjectId(long subjectId, String questionYear) {
		String query = "";
		query = env.getProperty("fetchQuestionPaperMasterBySubjectIdQuery");
		return jdbcTemplate.query(query, new Object[] { subjectId, questionYear },
				new int[] { Types.BIGINT, Types.BIGINT }, new RowMapper<QuestionPaperDTO>() {
					@Override
					public QuestionPaperDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						QuestionPaperDTO questionPaperDTO = new QuestionPaperDTO();
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
						subjectDTO.setSubjectName(rs.getString("subject_name"));
						questionPaperDTO.setCourseDTO(courseDTO);
						questionPaperDTO.setDivisionDTO(divisionDTO);
						questionPaperDTO.setClassDTO(classDTO);
						questionPaperDTO.setSubjectDTO(subjectDTO);
						questionPaperDTO.setQuestionPaperId(rs.getLong("question_paper_id"));
						questionPaperDTO.setQuestionYear(rs.getString("question_year"));
						questionPaperDTO.setQuestionPaperCode(rs.getString("question_paper_code"));
						questionPaperDTO.setNoofSet(rs.getInt("no_of_set"));
						questionPaperDTO.setUserName(rs.getString("user_name"));
						questionPaperDTO.setActiveStatus(rs.getInt("active_status"));
						return questionPaperDTO;
					}
				});
	}

	@Override
	public List<KeyValueDTO> getAllQuestionSetByPaperId(long questionPaperId) {
		String query = "";
		query = env.getProperty("fetchDifferentSet");
		List<Character> alphabetic = new ArrayList<Character>();
		char c;
		for (c = 'A'; c <= 'Z'; c++) {
			alphabetic.add(c);
		}
		return jdbcTemplate.query(query, new Object[] { questionPaperId }, new int[] { Types.BIGINT },
				new RowMapper<KeyValueDTO>() {
					@Override
					public KeyValueDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						KeyValueDTO keyValueDTO = new KeyValueDTO();
						keyValueDTO.setKey(rs.getLong("set_no"));
						keyValueDTO.setValue("" + alphabetic.get(rs.getInt("set_no") - 1));
						return keyValueDTO;
					}
				});
	}
}