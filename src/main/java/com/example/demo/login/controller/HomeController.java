package com.example.demo.login.controller;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class HomeController {
	
	//オートワイヤード設定で自動的にDIから取得
	@Autowired
	UserService userService;
	
	private Map<String,String> radioMarriage;
	
	private Map<String,String> initRadioMarriage(){
		Map<String,String> radio = new LinkedHashMap<>();
		
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		
		return radio;
	}
	
	@GetMapping("/home")
	public String getHome(Model model) {
		//home.htmlとhomeLayout.htmlを紐付け
		model.addAttribute("contents", "login/home :: home_contents");
		
		return "login/homeLayout";
	}
	
	@GetMapping("/userList")
	public String getUserList(Model model) {
		model.addAttribute("contents", "login/userList :: userList_contents");
		
		List<User> userList = userService.selectMany();
		model.addAttribute("userList", userList);
		
		int count = userService.count();
		model.addAttribute("userListCount", count);
		
		return "login/homeLayout";
	}
	
	@PostMapping("/logout")
	public String getLogout() {
		return "redirect:/login";
	}
	
	//ユーザIDを受け取る
	//このサンプルではユーザIDがメールアドレス形式になっているため、正規表現を使用
	@GetMapping("userDetail/{id:.+}")
	public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id")String userId) {
		System.out.println("userID = " + userId);
		
		model.addAttribute("contents", "login/userDetail :: userDetail_contents");
		
		radioMarriage=initRadioMarriage();
		model.addAttribute("radioMarriage", radioMarriage);
		
		if(userId != null && userId.length() > 0) {
			User user = userService.selectOne(userId);
			form.setUserId(user.getUserId()); //ユーザーID
            form.setUserName(user.getUserName()); //ユーザー名
            form.setBirthday(user.getBirthday()); //誕生日
            form.setAge(user.getAge()); //年齢
            form.setMarriage(user.isMarriage()); //結婚ステータス

            // Modelに登録
            model.addAttribute("signupForm", form);
        }

        return "login/homeLayout";
	}
	
	//削除と見分けるようにparamsを付与。値はHTMLでnameで指定
	@PostMapping(value="/userDetail", params="update")
	public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
		System.out.println("更新");
		
		User user = new User();
		user.setUserId(form.getUserId()); //ユーザーID
        user.setUserName(form.getUserName()); //ユーザー名
        user.setPassword(form.getPassword());
        user.setBirthday(form.getBirthday()); //誕生日
        user.setAge(form.getAge()); //年齢
        user.setMarriage(form.isMarriage()); //結婚ステータス
        
        try {
            boolean result = userService.updateOne(user);
            if(result) {
            	model.addAttribute("result", "更新成功");
            }
            else {
            	model.addAttribute("result", "更新失敗");
            }
        } 
        catch (DataAccessException e) {
            model.addAttribute("result", "更新失敗(トランザクションテスト)");
        }
        
        return getUserList(model);
	}
	
	//更新と見分けるようにparamsを付与。値はHTMLでnameで指定
	@PostMapping(value="/userDetail", params="delete")
	public String postUserDetailDelete(@ModelAttribute SignupForm form, Model model) {
		System.out.println("削除");
        
        boolean result = userService.deleteOne(form.getUserId());
        if(result) {
        	model.addAttribute("result", "削除成功");
        }
        else {
        	model.addAttribute("result", "削除失敗");
        }
        
        return getUserList(model);
	}
	
	@GetMapping("/userList/csv")
	public ResponseEntity<byte[]> getUserListCsv(Model model){
		//csv書き込み
		userService.userCsvOut();
		byte[] bytes = null;
		
		//csv取得
		try {
			bytes = userService.getFile("sample.csv");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		//ヘッダーの作成
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/csv; charset=UTF-8");
		header.setContentDispositionFormData("filename","sample.csv");
		
		return new ResponseEntity<>(bytes,header,HttpStatus.OK);
	}
	
	@GetMapping("admin")
	public String getAdmin(Model model) {
		model.addAttribute("contents", "login/admin :: admin_contents");
		
		return "login/homeLayout";
	}
}
