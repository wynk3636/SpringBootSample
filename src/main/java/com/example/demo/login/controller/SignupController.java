package com.example.demo.login.controller;


import java.util.Map;
import java.util.LinkedHashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
	
	private Map<String,String> radioMarriage;
	
	//初期化処理
	private Map<String,String> initRadioMarriage(){
		Map<String,String> radio = new LinkedHashMap<>();
		
		radio.put("既婚","true");
		radio.put("未婚","false");
		
		return radio;
	}
	
	@GetMapping("/signup")
	public String getSignup(Model model) {
		radioMarriage = initRadioMarriage();
		
		model.addAttribute("radioMarriage", radioMarriage);
		
		return "/login/signup";
	}
	
	@PostMapping("/signup")
	public String postSignup(Model model) {
		//リダイレクト
		return "redirect:/login";
	}
}
