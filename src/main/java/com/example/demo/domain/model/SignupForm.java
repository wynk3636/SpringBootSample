package com.example.demo.domain.model;

import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class SignupForm {
	
	//必須入力、メール形式
	@NotBlank(message="{require_check}")
	@Email(message="{email_check}")
	private String userId;
	
	//必須入力、文字数制限、半角英数値のみ
	@NotBlank(message="{require_check}")
	@Length(min=4,max=100,message="{length_check}")
	@Pattern(regexp="^[a-zA-Z0-9]+$",message="{pattern_check}")
	private String password;
	
	@NotBlank(message="{require_check}")
	private String userName;
	
	//指定したフォーマットの文字列を日付型に変換
	//Null出ないことのチェック=>Date型はblankにならないので注意
	@NotNull(message="{require_check}")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date birthday;
	
	@Min(value=20, message="{min_check}")
	@Max(value=100, message="{max_check}")
	private int age;
	
	//falseのみ
	@AssertFalse(message="{false_check}")
	private boolean marriage;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isMarriage() {
		return marriage;
	}
	public void setMarriage(boolean marriage) {
		this.marriage = marriage;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
