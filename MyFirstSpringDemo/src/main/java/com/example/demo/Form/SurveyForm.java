package com.example.demo.Form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

public class SurveyForm {
	
	//@Size(min = 0, max = 150)
	@Min(0)
	@Max(150)
	private int age;
	
	@Min(1)
	@Max(5)
	private int satisfaction;
	
	@NotNull(message = "コメントを入力してください。")
	@Size(min = 1, max = 200, message = "Please input 200 characters or less")
	private String comment;

	public SurveyForm() {
	}	
	public SurveyForm(int age,int satisfaction,String comment) {
	
		this.age = age;
		this.satisfaction = satisfaction;
		this.comment = comment;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(int satisfaction) {
		this.satisfaction = satisfaction;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
