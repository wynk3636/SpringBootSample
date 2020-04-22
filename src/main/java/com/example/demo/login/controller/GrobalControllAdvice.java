package com.example.demo.login.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//アプリ全体でのエラー処理をキャッチ
@ControllerAdvice
@Component
public class GrobalControllAdvice {
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		model.addAttribute("error", "内部サーバーエラー（DB） : Grobal");
		model.addAttribute("message", "SignupControllerでエラー発生");
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(DataAccessException e, Model model) {
		model.addAttribute("error", "内部サーバーエラー : Grobal");
		model.addAttribute("message", "SignupControllerでエラー発生");
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
}
