package com.onlineexam.app.service.Dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.onlineexam.app.dto.request.StudentFilterDTO;
import com.onlineexam.app.dto.request.exam.AsignStudentExamQuestionsDTO;
import com.onlineexam.app.dto.request.exam.AssignExamCreateDTO;
import com.onlineexam.app.dto.request.exam.AssignExamDTO;
import com.onlineexam.app.dto.request.exam.AssignExamDeleteDTO;
import com.onlineexam.app.dto.request.exam.AssignExamModifDTO;
import com.onlineexam.app.dto.request.exam.AssignStudentExamCreateDTO;
import com.onlineexam.app.dto.request.exam.AssignStudentExamModifyDTO;
import com.onlineexam.app.dto.request.exam.ExamCreateDTO;
import com.onlineexam.app.dto.request.exam.ExamDeleteDTO;
import com.onlineexam.app.dto.request.exam.ExamModifyDTO;
import com.onlineexam.app.dto.request.exam.ExamResultDTO;
import com.onlineexam.app.dto.request.exam.OptionsDTO;
import com.onlineexam.app.dto.request.exam.StudentExamInfoDTO;
import com.onlineexam.app.dto.response.exam.AssignStudentDTO;
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
	public List<ExamDTO> getAllExamsByYear(String examYear) {
		StringBuilder query = new StringBuilder();
		query.append(env.getProperty("fetchExamMasterByYearQuery"));
		return jdbcTemplate.query(query.toString(), new Object[] { examYear }, new int[] { Types.VARCHAR },
				new RowMapper<ExamDTO>() {
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

	@Override
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int saveAssignExam(AssignExamCreateDTO assignExamCreateDTO) throws SQLException {
		String assignExamQuery = env.getProperty("saveAssignExamQuery");
		List<AssignStudentExamCreateDTO> assignCreateDTOList = assignExamCreateDTO.getAssignStudentExamCreateDTOList();
		int[] size = jdbcTemplate.batchUpdate(assignExamQuery, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, assignExamCreateDTO.getExamId());
				ps.setString(2, assignExamCreateDTO.getExamYear());
				ps.setLong(3, assignCreateDTOList.get(i).getStudentId());
				ps.setLong(4, assignCreateDTOList.get(i).getSetNo());
				ps.setLong(5, assignExamCreateDTO.getUserId());
			}

			@Override
			public int getBatchSize() {
				return assignCreateDTOList.size();
			}
		});
		if (size.length > 0) {
			return size[0];
		} else {
			return 0;
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int updateAssignExam(AssignExamModifDTO assignExamModifDTO) throws SQLException {
		String assignExamQuery = env.getProperty("saveAssignExamQuery");
		List<AssignStudentExamModifyDTO> assignModifyDTOList = assignExamModifDTO.getAssignStudentExamModifyDTOList();
		int[] size = jdbcTemplate.batchUpdate(assignExamQuery, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, assignModifyDTOList.get(i).getSetNo());
				ps.setLong(2, assignExamModifDTO.getUserId());
				ps.setLong(3, assignExamModifDTO.getExamId());
				ps.setString(4, assignExamModifDTO.getExamYear());
				ps.setLong(5, assignModifyDTOList.get(i).getStudentId());
			}

			@Override
			public int getBatchSize() {
				return assignModifyDTOList.size();
			}
		});
		if (size.length > 0) {
			return size[0];
		} else {
			return 0;
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int deleteAssignExam(AssignExamDeleteDTO assignExamDeleteDTO) throws SQLException {
		String deleteMasterQuery = env.getProperty("deleteExamMasterQuery");
		return jdbcTemplate.update(deleteMasterQuery, assignExamDeleteDTO.getExamId(),
				assignExamDeleteDTO.getExamYear());
	}

	@Override
	public List<AssignStudentDTO> getAllAssignStudentDetails(StudentFilterDTO studentFilterDTO) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append(env.getProperty("fetchAssignStudentQuery"));
		List<Object> paramList = new ArrayList<Object>();
		List<Integer> typesList = new ArrayList<Integer>();
		if (studentFilterDTO.getCourseId() != 0) {
			query.append(" and si.course_id=? ");
			paramList.add(studentFilterDTO.getCourseId());
			typesList.add(Types.BIGINT);
		}
		if (studentFilterDTO.getDivisionId() != 0) {
			query.append(" and si.division_id =? ");
			paramList.add(studentFilterDTO.getDivisionId());
			typesList.add(Types.BIGINT);
		}
		if (studentFilterDTO.getClassId() != 0) {
			query.append(" and si.class_id=? ");
			paramList.add(studentFilterDTO.getClassId());
			typesList.add(Types.BIGINT);
		}
		return jdbcTemplate.query(query.toString(), paramList.toArray(), typesList.stream().mapToInt(i -> i).toArray(),
				new RowMapper<AssignStudentDTO>() {
					@Override
					public AssignStudentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						AssignStudentDTO assignStudentDTO = new AssignStudentDTO();
						ClassDTO classDTO = new ClassDTO();
						classDTO.setClassId(rs.getLong("class_id"));
						classDTO.setClassName(rs.getString("class_name"));
						CourseDTO courseDTO = new CourseDTO();
						courseDTO.setCourseId(rs.getLong("course_id"));
						courseDTO.setCourseName(rs.getString("course_name"));
						DivisionDTO divisionDTO = new DivisionDTO();
						divisionDTO.setDivisionId(rs.getLong("division_id"));
						divisionDTO.setDivisionName(rs.getString("division_name"));
						assignStudentDTO.setStudentId(rs.getLong("student_id"));
						assignStudentDTO.setStudentName(rs.getString("student_name"));
						assignStudentDTO.setSetNo(rs.getLong("set_no"));
						assignStudentDTO.setCourseDTO(courseDTO);
						assignStudentDTO.setDivisionDTO(divisionDTO);
						assignStudentDTO.setClassDTO(classDTO);
						return assignStudentDTO;
					}
				});
	}

	@Override
	public List<AssignExamDTO> getAllAssignExams(int pageIndex, int totalRecords) {
		StringBuilder query = new StringBuilder();
		if (pageIndex == 0 && totalRecords == 0)
			query.append(env.getProperty("fetchAssignExamQuery"));
		else
			query.append(env.getProperty("fetchAssignExamQuery"))
					.append("  limit " + (pageIndex * totalRecords) + " , ").append(totalRecords);
		return jdbcTemplate.query(query.toString(), new RowMapper<AssignExamDTO>() {
			@Override
			public AssignExamDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AssignExamDTO assignExamDTO = new AssignExamDTO();
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
				assignExamDTO.setCourseDTO(courseDTO);
				assignExamDTO.setDivisionDTO(divisionDTO);
				assignExamDTO.setClassDTO(classDTO);
				assignExamDTO.setSubjectDTO(subjectDTO);
				assignExamDTO.setExamId(rs.getLong("exam_id"));
				assignExamDTO.setExamCode(rs.getString("exam_code"));
				assignExamDTO.setExamYear(rs.getString("exam_year"));
				return assignExamDTO;
			}
		});
	}

	@Override
	public Integer getTotalAssignExams() {
		String countQuery = env.getProperty("fetchAssignExamCountQuery");
		return jdbcTemplate.queryForObject(countQuery, Integer.class);
	}

	@Override
	public List<AsignStudentExamQuestionsDTO> getAllAssignedStudentQuestions(int examId, String examYear, int studentId)
			throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append(env.getProperty("fetchStudentQuestions"));
		return jdbcTemplate.query(query.toString(), new Object[] { examId, examYear, studentId },
				new int[] { Types.BIGINT, Types.VARCHAR, Types.BIGINT }, new RowMapper<AsignStudentExamQuestionsDTO>() {
					@Override
					public AsignStudentExamQuestionsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						AsignStudentExamQuestionsDTO asignStudentExamQuestionsDTO = new AsignStudentExamQuestionsDTO();
						asignStudentExamQuestionsDTO.setQuestionId(rs.getLong("question_id"));
						asignStudentExamQuestionsDTO.setQuestion(rs.getString("question"));
						List<OptionsDTO> options = new ArrayList<OptionsDTO>();
						OptionsDTO optionsDTO = new OptionsDTO();
						optionsDTO.setKey(1);
						optionsDTO.setOption(rs.getString("option1"));
						options.add(optionsDTO);
						optionsDTO = new OptionsDTO();
						optionsDTO.setKey(2);
						optionsDTO.setOption(rs.getString("option2"));
						options.add(optionsDTO);
						optionsDTO = new OptionsDTO();
						optionsDTO.setKey(3);
						optionsDTO.setOption(rs.getString("option3"));
						options.add(optionsDTO);
						optionsDTO = new OptionsDTO();
						optionsDTO.setKey(4);
						optionsDTO.setOption(rs.getString("option4"));
						options.add(optionsDTO);
						asignStudentExamQuestionsDTO.setOptions(options);
						return asignStudentExamQuestionsDTO;
					}
				});
	}

	@Override
	public int saveExamResult(List<ExamResultDTO> examResultDTOList) throws SQLException {
		String saveMasterQuery = env.getProperty("saveExamResult");
		int count[] = jdbcTemplate.batchUpdate(saveMasterQuery, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ExamResultDTO examResultDTO = (ExamResultDTO) examResultDTOList.get(i);
				ps.setLong(1, examResultDTO.getStudentId());
				ps.setLong(2, examResultDTO.getExamId());
				ps.setLong(3, examResultDTO.getQuestionId());
				ps.setLong(4, examResultDTO.getAnswerKey());
				ps.setInt(5, examResultDTO.getIsAnswered());
			}

			public int getBatchSize() {
				return examResultDTOList.size();
			}
		});

		if (count.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<StudentExamInfoDTO> getStudentExamsInfo(int studentId) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append(env.getProperty("fetchStudentExamInfo"));
		return jdbcTemplate.query(query.toString(), new Object[] { studentId }, new int[] { Types.BIGINT },
				new RowMapper<StudentExamInfoDTO>() {
					@Override
					public StudentExamInfoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						StudentExamInfoDTO studentExamInfoDTO = new StudentExamInfoDTO();
						studentExamInfoDTO.setExamId(rs.getLong("exam_id"));
						studentExamInfoDTO.setExamCode(rs.getString("exam_code"));
						studentExamInfoDTO.setExamDate(rs.getString("exam_date"));
						studentExamInfoDTO.setExamDuration(rs.getInt("exam_duration"));
						studentExamInfoDTO.setExamTime(rs.getString("exam_time"));
						studentExamInfoDTO.setExamYear(rs.getString("exam_year"));
						studentExamInfoDTO.setStudentId(rs.getLong("student_id"));
						SubjectDTO subjectDTO = new SubjectDTO();
						subjectDTO.setSubjectId(rs.getLong("subject_id"));
						subjectDTO.setSubjectCode(rs.getString("subject_code"));
						subjectDTO.setSubjectName(rs.getString("subject_name"));
						studentExamInfoDTO.setSubjectDTO(subjectDTO);
						return studentExamInfoDTO;
					}
				});
	}
}