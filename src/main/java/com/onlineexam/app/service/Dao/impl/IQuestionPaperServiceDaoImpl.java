package com.onlineexam.app.service.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onlineexam.app.constants.DifficultyMaster;
import com.onlineexam.app.dto.request.question.QuestionPaperCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperModifyDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperSetCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionSubSubjectCreateDTO;
import com.onlineexam.app.dto.response.master.SubSubjectDTO;
import com.onlineexam.app.dto.response.master.SubjectDTO;
import com.onlineexam.app.dto.response.master.TopicDTO;
import com.onlineexam.app.dto.response.question.QuestionDTO;
import com.onlineexam.app.service.Dao.IQuestionPaperServiceDao;

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
				PreparedStatement ps = con.prepareStatement(saveMasterQuery);
				ps.setString(1, questionPaperCreateDTO.getQuestionYear());
				ps.setLong(2, questionPaperCreateDTO.getClassId());
				ps.setLong(3, questionPaperCreateDTO.getCourseId());
				ps.setLong(4, questionPaperCreateDTO.getSubjectId());
				ps.setInt(5, questionPaperCreateDTO.getNoOfSet());
				ps.setLong(6, questionPaperCreateDTO.getUserId());
				return ps;
			}
		}, keyHolder);
		Long questionPaperId = Long.valueOf((long) keyHolder.getKey());
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
	public int updateQuestionPaper(QuestionPaperModifyDTO questionPaperModifyDTO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
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
				ps.setLong(4, questionPaperSetCreateList.get(i).getUserId());
			}
			@Override
			public int getBatchSize() {
				return questionPaperSetCreateList.size();
			}
		});
	}

}
