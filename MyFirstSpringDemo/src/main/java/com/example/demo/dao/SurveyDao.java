package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Survey;

public interface SurveyDao {
	
	void insertSurvey(Survey survey);
	int updateSurvey(Survey survey);
	List<Survey> getAll();	

}
