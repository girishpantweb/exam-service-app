package com.onlineexam.app.service.Dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.onlineexam.app.dto.request.exam.ExamCreateDTO;
import com.onlineexam.app.dto.request.exam.ExamDeleteDTO;
import com.onlineexam.app.dto.request.exam.ExamModifyDTO;
import com.onlineexam.app.dto.response.exam.ExamDTO;
import com.onlineexam.app.dto.response.master.ClassDTO;
import com.onlineexam.app.dto.response.master.CourseDTO;
import com.onlineexam.app.dto.response.master.DivisionDTO;
import com.onlineexam.app.dto.response.master.SubjectDTO;
import com.onlineexam.app.dto.response.question.QuestionPaperDTO;
import com.onlineexam.app.service.Dao.IExamServiceDao;
import com.onlineexam.app.utils.CommonUtility;

@Repository("examServiceDaoImpl")
public class IExamServiceDaoImpl implements IExamServiceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private Environment env;

	@Override
	public List<ExamDTO> getAllExams(int pageIndex, int totalRecords) {
		StringBuilder query = new StringBuilder();
		if (pageIndex == 0 && totalRecords == 0)
			query.append(env.getProperty("fetchExamMasterQuery")).append(" where qm.active_status=1");
		else
			query.append(env.getProperty("fetchExamMasterQuery"))
					.append("  limit " + (pageIndex * totalRecords) + " , ").append(totalRecords);
		return jdbcTemplate.query(query.toString(), new RowMapper<ExamDTO>() {
			@Override
			public ExamDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ExamDTO examDTO = new ExamDTO();
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
				QuestionPaperDTO questionPaperDTO = new QuestionPaperDTO();
				questionPaperDTO.setQuestionPaperId(rs.getLong("question_paper_id"));
				questionPaperDTO.setQuestionPaperCode(rs.getString("question_paper_code"));
				questionPaperDTO.setQuestionYear(rs.getString("question_year"));
				examDTO.setCourseDTO(courseDTO);
				examDTO.setDivisionDTO(divisionDTO);
				examDTO.setClassDTO(classDTO);
				examDTO.setSubjectDTO(subjectDTO);
				examDTO.setQuestionPaperDTO(questionPaperDTO);
				examDTO.setExamId(rs.getLong("exam_id"));
				examDTO.setExamCode(rs.getString("exam_code"));
				examDTO.setExamYear(rs.getString("exam_year"));
				examDTO.setExamDate(rs.getString("exam_date"));
				examDTO.setExamTime(rs.getString("exam_time"));
				examDTO.setActiveStatus(rs.getInt("active_status"));
				return examDTO;
			}
		});
	}

	@Override
	public Integer getTotalExams() {
		String countQuery = env.getProperty("countExamMasterQuery");
		return jdbcTemplate.queryForObject(countQuery, Integer.class);
	}

	@Override
	public int saveExam(ExamCreateDTO examCreateDTO) throws SQLException {
		String saveMasterQuery = env.getProperty("saveExamMasterQuery");
		return jdbcTemplate.update(saveMasterQuery,
				new Object[] { examCreateDTO.getExamCode(), examCreateDTO.getExamYear(), examCreateDTO.getCourseId(),
						examCreateDTO.getDivisionId(), examCreateDTO.getClassId(), examCreateDTO.getSubjectId(),
						examCreateDTO.getQuestionPaperId(), examCreateDTO.getExamDate(), examCreateDTO.getExamTime(),
						examCreateDTO.getUserId() });
	}

	@Override
	public int updateExam(ExamModifyDTO examModifyDTO) throws SQLException {
		String updateMasterQuery = env.getProperty("updateExamMasterQuery");
		return jdbcTemplate.update(updateMasterQuery, examModifyDTO.getExamCode(), examModifyDTO.getExamYear(),
				examModifyDTO.getCourseId(), examModifyDTO.getDivisionId(), examModifyDTO.getClassId(),
				examModifyDTO.getSubjectId(), examModifyDTO.getQuestionPaperId(), examModifyDTO.getExamDate(),
				examModifyDTO.getExamTime(), examModifyDTO.getUserId(), examModifyDTO.getExamId());
	}

	@Override
	public int deleteExam(ExamDeleteDTO examDeleteDTO) throws SQLException {
		String deleteMasterQuery = env.getProperty("deleteExamMasterQuery");
		return jdbcTemplate.update(deleteMasterQuery, examDeleteDTO.getActiveStatus(), examDeleteDTO.getUserId(),
				CommonUtility.getCurrentDateTime(), examDeleteDTO.getExamId());
	}

}
