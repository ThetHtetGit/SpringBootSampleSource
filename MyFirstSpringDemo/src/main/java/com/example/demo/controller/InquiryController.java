package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Form.InquiryForm;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {
	
	@GetMapping("/form")
	public String form(InquiryForm form, Model model) {
		model.addAttribute("title", "Inquiry Form");
		System.out.println("@GetMapping form inquiry/form");
		return "inquiry/form";
	}
	
	@PostMapping("/form")
	public String formGoBack(InquiryForm form, Model model) {
		model.addAttribute("title", "Inquiry Form");
		System.out.println("@PostMapping formGoBack inquiry/form");
		return "inquiry/form";
	}
	
	@PostMapping("/confirm")
	public String confirm(@Validated InquiryForm Form, BindingResult result, Model model) {
		
		System.out.println("@PostMapping confirm inquiry/confirm");
		
		if(result.hasErrors()) {
			model.addAttribute("title", "Inquiry Form");
			System.out.println(">>HasErrors");
			return "inquiry/form";
		}
		
		model.addAttribute("title", "Confirm Page");
		System.out.println(">>NoErrors");
		return "inquiry/confirm";
	}	
	
	@PostMapping("/complete")
	public String complete(@Validated InquiryForm form,BindingResult result,Model model, RedirectAttributes redirectAttri) {
		
		System.out.println("@PostMapping complete inquiry/form");
		
		if(result.hasErrors()) {
			model.addAttribute("title", "Inquiry Form");
			return "inquiry/form";
		}
		
		redirectAttri.addFlashAttribute("complete", "Registered!");
		return "redirect:/inquiry/form";//If it has no error, redirect to inquiry form url
		
	}
}
