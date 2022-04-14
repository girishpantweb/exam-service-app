package com.onlineexam.app.service.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.onlineexam.app.dto.request.question.ManageSubSetCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperDeleteDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperModifyDTO;
import com.onlineexam.app.dto.request.question.QuestionPaperSetCreateDTO;
import com.onlineexam.app.dto.request.question.QuestionSubSubjectCreateDTO;
import com.onlineexam.app.dto.request.question.SubSetCreateDTO;
import com.onlineexam.app.dto.request.question.SubSetDeleteDTO;
import com.onlineexam.app.dto.request.question.SubSetModifyDTO;
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
}
