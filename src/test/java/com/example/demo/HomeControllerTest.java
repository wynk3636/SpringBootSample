package com.example.demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.login.domain.service.UserService;
/*
//ログイン後の画面表示のテスト
//テスト用アノテーション
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc //モックのアノテーション
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mock;//モックを準備
	@MockBean	
	private UserService service; //モックの戻り値設定
	
	
	@Test
	@WithMockUser
	public void ユーザーリスト画面のテスト() throws Exception {
		
		when(service.count()).thenReturn(10);//戻り値を設定
		
		mock.perform(get("/userList"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("合計：10件")));//htmlにユーザーIDという文字が含まれているかチェック
	}
}
*/
