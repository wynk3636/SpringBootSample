package com.example.demo.login.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.RestService;

//RestApi用のコントローラー->各メソッドの戻り値にHTML以外を指定できる
@RestController
public class UserRestController {
	
	@Autowired
	RestService service;
	
	//全件取得
	@GetMapping("rest/get")
	public List<User> getUserMany(){
		return service.selectMany();
	}
	
	//1件取得
	@GetMapping("/rest/get/{id:.+}")
	public User getUserOne(@PathVariable("id")String userId) {
		return service.selectOne(userId);
	}
	
	//insert
	@PostMapping("/rest/insert")
	public String postUserOne(@RequestBody User user) {
		boolean result = service.insert(user);
		String str="";
		
		if(result==true) {
			str = "{\"result\":\"ok\"}";
        } else {
            str = "{\"result\":\"error\"}";
        }
        // 結果用の文字列をリターン
        return str;
	}
	
	//update
	@PutMapping("/rest/update")
	public String putUserOne(@RequestBody User user) {
		// ユーザーを１件登録
        boolean result = service.update(user);

        String str = "";
        if(result == true) {
            str = "{\"result\":\"ok\"}";
        } else {
            str = "{\"result\":\"error\"}";
        }

        // 結果用の文字列をリターン
        return str;
	}
	
	//delete
	@DeleteMapping("/rest/delete/{id:.+}")
    public String deleteUserOne(@PathVariable("id") String userId) {
        // ユーザーを１件削除
        boolean result = service.delete(userId);
        String str = "";
        if(result == true) {
            str = "{\"result\":\"ok\"}";
        } else {
            str = "{\"result\":\"error\"}";
        }
        // 結果用の文字列をリターン
        return str;
    }
}
