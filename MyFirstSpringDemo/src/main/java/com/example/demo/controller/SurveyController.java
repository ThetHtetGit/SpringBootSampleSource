package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Survey;
import com.example.demo.service.SurveyService;
import com.example.demo.Form.SurveyForm;

@Controller
@RequestMapping("/survey")
public class SurveyController {	

	private final SurveyService surveyService;	
		
	public SurveyController(SurveyService surveyService) {		
		this.surveyService = surveyService;
	}	
	
	@GetMapping("/list")
	public String list(Model model) {
		
		/*
		 * Survey survey=new Survey(); survey.setId(4); survey.setAge(40);
		 * survey.setSatisfaction(2); survey.setComment("Exception Test");
		 * 
		 * try { surveyService.updateSurvey(survey); }catch (SurveyNotFoundException e)
		 * { model.addAttribute("message", e); return "error/CustomErrorPage"; }
		 */
		
		List<Survey> list = surveyService.getAll();		
		model.addAttribute("surveyList", list); 
		model.addAttribute("title","アンケート一覧");
		
		return "survey/list_boot";
	}

	@GetMapping("/form")
	public String form(SurveyForm surveyForm, Model model) {
		model.addAttribute("title", "アンケートフォーム");
		System.out.println("@GetMapping survey/form");
		return "survey/form_boot";	
	}
	
	@PostMapping("/form")
	public String formBack(SurveyForm surveyForm, Model model) {
		model.addAttribute("title", "アンケートフォーム");
		System.out.println("@PostMapping formBack survey/form");
		return "survey/form_boot";	
	}
	
	@PostMapping("/confirm")
	public String confirm(@Validated SurveyForm surveyForm,BindingResult result, Model model) {
		
		System.out.println("@PostMapping confirm survey/confirm_boot");
		
		if(result.hasErrors()) {
			model.addAttribute("title", "アンケートフォーム");
			System.out.println(">>HasErrors");
			return "survey/form_boot";
		}
		
		model.addAttribute("title", "アンケートフォーム（確認）");
		System.out.println(">>NoErrors");
		return "survey/confirm_boot";
		
	}
	
	@PostMapping("/complete")
	public String complete(@Validated SurveyForm surveyForm,
			BindingResult result,
			Model model, 
			RedirectAttributes redirectAttribute) {
		
		System.out.println("@PostMapping complete redirect:/survey/form [before Saved]");
		
		if(result.hasErrors()) {
			model.addAttribute("title", "アンケートフォーム");
			return "survey/form_boot";
		}
		
		Survey survey = new Survey();
		survey.setAge(surveyForm.getAge());
		survey.setSatisfaction(surveyForm.getSatisfaction());
		survey.setComment(surveyForm.getComment());
		survey.setCreatedDate(LocalDateTime.now());
		
		System.out.println("Form Data Age " + surveyForm.getAge() + survey.getAge());
		surveyService.save(survey);
		
		System.out.println("@PostMapping complete redirect:/survey/form [after Saved]");
		
		redirectAttribute.addFlashAttribute("complete", "完成しました!");
		return "redirect:/survey/form";
		
	}

}
