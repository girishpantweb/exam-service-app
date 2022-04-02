package com.onlineexam.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onlineexam.app.dto.response.master.SubSubjectDTO;
import com.onlineexam.app.dto.response.master.SubjectDTO;
import com.onlineexam.app.dto.response.master.TopicDTO;
import com.onlineexam.app.service.Dao.IMasterServiceDao;

@Service
public class CacheService {

	@Autowired
	private IMasterServiceDao iMasterServiceDao;

	private List<SubjectDTO> subjectDataList = new ArrayList<SubjectDTO>();
	private List<SubSubjectDTO> subSubjectDataList = new ArrayList<SubSubjectDTO>();
	private List<TopicDTO> topicDataList = new ArrayList<TopicDTO>();

	@PostConstruct
	public void intializeMasterData() {
		subjectDataList = iMasterServiceDao.getAllSubjectsCacheData();
		subSubjectDataList = iMasterServiceDao.getAllSubSubjectsCacheData();
		topicDataList = iMasterServiceDao.getAllTopicsCacheData();
	}

	public List<SubjectDTO> getSubjectDataList() {
		return subjectDataList;
	}

	public List<SubSubjectDTO> getSubSubjectDataList() {
		return subSubjectDataList;
	}

	public List<TopicDTO> getTopicDataList() {
		return topicDataList;
	}

}
