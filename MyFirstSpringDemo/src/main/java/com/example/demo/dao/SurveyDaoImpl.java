package com.example.demo.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Survey;

@Repository
public class SurveyDaoImpl implements SurveyDao {

	//To use database operations
	private final JdbcTemplate jdbcTemplate;
	private String sql;
		
	public SurveyDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertSurvey(Survey survey) {
		
		System.out.println("SurveyDaoImpl insertSurvey Before executing insert command");
		
		sql="INSERT INTO survey(age, satisfaction, comment, created)"
				+ " VALUES(?, ?, ?, ?)";
		jdbcTemplate.update(sql,survey.getAge(),survey.getSatisfaction(),survey.getComment(),survey.getCreatedDate());
		
		System.out.println("SurveyDaoImpl insertSurvey After executing insert command");

	}

	@Override
	public int updateSurvey(Survey survey) {	
		
		sql="UPDATE survey SET age = ?, satisfaction = ?, comment = ?, created = ? WHERE id = ?";
		return jdbcTemplate.update(sql, survey.getAge(),survey.getSatisfaction(), survey.getComment(), survey.getCreatedDate(), survey.getId());		
	}
	
	@Override
	public List<Survey> getAll() {
		
		System.out.println("SurveyDaoImpl getAll <Before> sql executing");
		
		sql="SELECT id,age, satisfaction, comment, created FROM survey";
		List<Map<String,Object>> resultList=jdbcTemplate.queryForList(sql);
		
		System.out.println("SurveyDaoImpl getAll <After> sql executing");
		
		List<Survey> list=new ArrayList<Survey>();
		
		for(Map<String,Object> result: resultList) {
			
			Survey survey=new Survey();
			survey.setId((int)result.get("id"));
			survey.setAge((int)result.get("age"));			
			survey.setSatisfaction((int)result.get("satisfaction"));
			survey.setComment((String)result.get("comment"));
			survey.setCreatedDate(((Timestamp)result.get("created")).toLocalDateTime());
			list.add(survey);
		}
		
		System.out.println("List count " + list.size());
		return list;
	}
}
