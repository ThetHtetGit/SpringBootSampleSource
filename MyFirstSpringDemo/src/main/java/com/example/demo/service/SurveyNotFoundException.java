package com.example.demo.service;

public class SurveyNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SurveyNotFoundException(String message) {
		super(message);
	}

}
