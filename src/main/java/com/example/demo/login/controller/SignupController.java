package com.example.demo.login.controller;


import java.util.Map;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class SignupController {
	
	@Autowired
	private UserService userService;
	
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
	//@Validatedでバリデーション実施->インターフェースを指定して、バリデーションをグループ実行（グループ１は全部、２は一部のものに設定）
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute @Validated(GroupOrder.class) SignupForm form,BindingResult bindingResult, Model model) {
		
		//入力チェックに引っかかっていた場合、元のページに戻る
		if(bindingResult.hasErrors()) {
			System.out.println("validate error cause in login page");
			return getSignup(form,model);
		}
		
		System.out.println("validate ok in login page " + form);
		
		//Insert用の変数
		User user = new User();
        user.setUserId(form.getUserId()); //ユーザーID
        user.setPassword(form.getPassword()); //パスワード
        user.setUserName(form.getUserName()); //ユーザー名
        user.setBirthday(form.getBirthday()); //誕生日
        user.setAge(form.getAge()); //年齢
        user.setMarriage(form.isMarriage()); //結婚ステータス
        user.setRole("ROLE_GENERAL"); //ロール（一般）

        // ユーザー登録処理
        boolean result = userService.insert(user);

        // ユーザー登録結果の判定
        if (result == true) {
            System.out.println("insert成功");
        } else {
            System.out.println("insert失敗");
        }
		
		//リダイレクト
		return "redirect:/login";
	}
}
