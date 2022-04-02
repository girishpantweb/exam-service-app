package com.onlineexam.app.service.Dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.onlineexam.app.constants.DifficultyMaster;
import com.onlineexam.app.dto.request.question.QuestionCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionModifyDTO;
import com.onlineexam.app.dto.response.master.SubSubjectDTO;
import com.onlineexam.app.dto.response.master.SubjectDTO;
import com.onlineexam.app.dto.response.master.TopicDTO;
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
				questionDTO.setDifficulty(DifficultyMaster.getDicultyFromId(rs.getInt("difficulty_level")));
				questionDTO.setUserName(rs.getString("user_name"));
				questionDTO.setActiveStatus(rs.getInt("active_status"));
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
		return jdbcTemplate.update(saveMasterQuery,
				new Object[] { questionCreateDTO.getSubjectId(), questionCreateDTO.getSubSubjectId(),
						questionCreateDTO.getTopicId(), questionCreateDTO.getQuestion(), questionCreateDTO.getOption1(),
						questionCreateDTO.getOption2(), questionCreateDTO.getOption3(), questionCreateDTO.getOption4(),
						questionCreateDTO.getAnswerKey(), questionCreateDTO.getDescription(),
						questionCreateDTO.getDifficultyLevel(), questionCreateDTO.getUserId() });
	}

	@Override
	public int updateQuestion(QuestionModifyDTO questionModifyDTO) throws SQLException {
		String updateMasterQuery = env.getProperty("updateQuestionQuery");
		return jdbcTemplate.update(updateMasterQuery, questionModifyDTO.getSubjectId(),
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
				ps.setLong(1, questData.getSubjectId());
				ps.setLong(2, questData.getSubSubjectId());
				ps.setLong(3, questData.getTopicId());
				ps.setString(4, questData.getQuestion());
				ps.setString(5, questData.getOption1());
				ps.setString(6, questData.getOption2());
				ps.setString(7, questData.getOption3());
				ps.setString(8, questData.getOption4());
				ps.setInt(9, questData.getAnswerKey());
				ps.setString(10, questData.getDescription());
				ps.setInt(11, questData.getDifficultyLevel());
				ps.setLong(12, questData.getUserId());
			}

			public int getBatchSize() {
				return questionsList.size();
			}
		});
	}

}
