package com.example.demo.trySpring;

import lombok.Data;

//getter,setterを自動で付与
@Data
public class Employee {
	private int employeeId;
	private String employeeName;
	private int age;
}

//ドメインクラスに該当
