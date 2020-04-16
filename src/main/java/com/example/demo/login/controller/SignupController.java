package com.example.demo.login.controller;


import java.util.Map;
import java.util.LinkedHashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.SignupForm;

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
	
	//ModelAttributeを引数にセットすることで、自動でモデルに登録をする
	//クラス名の名前の先頭を小文字にしたものをデフォルトでキーとして登録する
	@GetMapping("/signup")
	public String getSignup(@ModelAttribute SignupForm form,Model model) {
		radioMarriage = initRadioMarriage();
		
		model.addAttribute("radioMarriage", radioMarriage);
		
		//SignupFormは自動でセットされる
		
		return "/login/signup";
	}
	
	//データバインドの結果を受け取るためにBindingResultを引数に設定
	//@Validatedでバリデーション実施
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute @Validated SignupForm form,BindingResult bindingResult, Model model) {
		
		//入力チェックに引っかかっていた場合、元のページに戻る
		if(bindingResult.hasErrors()) {
			System.out.println("validate error cause in login page");
			return getSignup(form,model);
		}
		
		System.out.println("validate ok in login page " + form);
		
		//リダイレクト
		return "redirect:/login";
	}
}
