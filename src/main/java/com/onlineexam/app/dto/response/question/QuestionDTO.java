package com.onlineexam.app.dto.response.question;

import java.sql.Blob;
import com.onlineexam.app.dto.response.master.ClassDTO;
import com.onlineexam.app.dto.response.master.CourseDTO;
import com.onlineexam.app.dto.response.master.DivisionDTO;
import com.onlineexam.app.dto.response.master.SubSubjectDTO;
import com.onlineexam.app.dto.response.master.SubjectDTO;
import com.onlineexam.app.dto.response.master.TopicDTO;

public class QuestionDTO {

	private long sno;
	private long questionId;
	private CourseDTO courseDTO;
	private DivisionDTO divisionDTO;
	private ClassDTO classDTO;
	private SubjectDTO subject;
	private SubSubjectDTO subSubject;
	private TopicDTO topic;
	private String question;
	private Blob uploads;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answerKey;
	private String description;
	private DifficultyDTO difficultyDTO;
	private String userName;
	private int activeStatus;

	public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public CourseDTO getCourseDTO() {
		return courseDTO;
	}

	public void setCourseDTO(CourseDTO courseDTO) {
		this.courseDTO = courseDTO;
	}

	public DivisionDTO getDivisionDTO() {
		return divisionDTO;
	}

	public void setDivisionDTO(DivisionDTO divisionDTO) {
		this.divisionDTO = divisionDTO;
	}

	public ClassDTO getClassDTO() {
		return classDTO;
	}

	public void setClassDTO(ClassDTO classDTO) {
		this.classDTO = classDTO;
	}

	public SubjectDTO getSubject() {
		return subject;
	}

	public void setSubject(SubjectDTO subject) {
		this.subject = subject;
	}

	public SubSubjectDTO getSubSubject() {
		return subSubject;
	}

	public void setSubSubject(SubSubjectDTO subSubject) {
		this.subSubject = subSubject;
	}

	public TopicDTO getTopic() {
		return topic;
	}

	public void setTopic(TopicDTO topic) {
		this.topic = topic;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Blob getUploads() {
		return uploads;
	}

	public void setUploads(Blob uploads) {
		this.uploads = uploads;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getAnswerKey() {
		return answerKey;
	}

	public void setAnswerKey(String answerKey) {
		this.answerKey = answerKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DifficultyDTO getDifficultyDTO() {
		return difficultyDTO;
	}

	public void setDifficultyDTO(DifficultyDTO difficultyDTO) {
		this.difficultyDTO = difficultyDTO;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuestionDTO [sno=");
		builder.append(sno);
		builder.append(", questionId=");
		builder.append(questionId);
		builder.append(", courseDTO=");
		builder.append(courseDTO);
		builder.append(", divisionDTO=");
		builder.append(divisionDTO);
		builder.append(", classDTO=");
		builder.append(classDTO);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", subSubject=");
		builder.append(subSubject);
		builder.append(", topic=");
		builder.append(topic);
		builder.append(", question=");
		builder.append(question);
		builder.append(", uploads=");
		builder.append(uploads);
		builder.append(", option1=");
		builder.append(option1);
		builder.append(", option2=");
		builder.append(option2);
		builder.append(", option3=");
		builder.append(option3);
		builder.append(", option4=");
		builder.append(option4);
		builder.append(", answerKey=");
		builder.append(answerKey);
		builder.append(", description=");
		builder.append(description);
		builder.append(", difficultyDTO=");
		builder.append(difficultyDTO);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", activeStatus=");
		builder.append(activeStatus);
		builder.append("]");
		return builder.toString();
	}

}
