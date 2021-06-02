package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.SurveyDao;
import com.example.demo.entity.Survey;

@Service
public class SurveyServiceImpl implements SurveyService {

	private final SurveyDao surveyDao;
	
	SurveyServiceImpl(SurveyDao surveyDao) {		
		this.surveyDao = surveyDao;
		System.out.println("SurveyServiceImpl Constructor ");
	}

	@Override
	public void save(Survey survey) {
		
		System.out.println("SurveyServiceImpl save");
		surveyDao.insertSurvey(survey);
	}

	@Override
	public List<Survey> getAll() {
		System.out.println("SurveyServiceImpl getAll");
		var list = surveyDao.getAll();
		return list;
	}

	@Override
	public void updateSurvey(Survey survey) {
		if(surveyDao.updateSurvey(survey)==0) {
			throw new SurveyNotFoundException("Can't find same Survey ID");
		}
		
	}

}
