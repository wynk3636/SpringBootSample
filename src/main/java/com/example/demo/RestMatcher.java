package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class RestMatcher implements RequestMatcher{

	private AntPathRequestMatcher matcher;
	
	public RestMatcher(String url) {
		super();
		matcher = new AntPathRequestMatcher(url);
	}
	
	@Override
	public boolean matches(HttpServletRequest request) {
		
		//GETメソッドならCSRFチェックなし
		if("GET".equals(request.getMethod())) {
			return false;
		}
		
		//特定のURLはCSRFチェックなし
		if(matcher.matches(request)) {
			return false;
		}
		
		return true;
	}
	
}
