package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class MyController {
	
	//To use database operations
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MyController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	@GetMapping("/index")
	public String index(Model model) {
			
	    model.addAttribute("title","ホームページ");		
		return "/index";
	}
	
	//http://localhost:8080/demo/test
	@GetMapping("/test")
	public String test(Model model) { 
		
		String sql="SELECT id, name, email FROM inquiry WHERE id = 1";
		
		Map<String,Object> map= jdbcTemplate.queryForMap(sql);		
		model.addAttribute("title","Inquiry Form");
		model.addAttribute("name", map.get("name"));
		model.addAttribute("email", map.get("email"));
		
		return "test";
	}
}
