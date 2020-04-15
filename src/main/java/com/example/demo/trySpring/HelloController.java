package com.example.demo.trySpring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controllerアノテーションを付けることで、DI（依存性注入）で利用できる
@Controller
public class HelloController {
	
	//@GetMappingアノテーションをメソッドに付けると、HTTPリクエストのGETメソッドを処理
	@GetMapping("/hello")
	public String getHello() {
		//hello.htmlに画面遷移
		return "hello";
	}
	
	//Postメソッドを処理
	@PostMapping("/hello")
	public String postRequest(@RequestParam("freeText")String str, Model model) {
		//受け取った文字列をModelに登録
		model.addAttribute("sendValue", str);
		//helloResponse.htmlに遷移
		return "helloResponse";
	}
}
