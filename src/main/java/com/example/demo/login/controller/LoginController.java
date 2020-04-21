package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.service.UserService;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		return "login/login";
	}
	
	@PostMapping("/login")
	public String PostLogin(Model model) {
		return "redirect:/home";
	}
}
