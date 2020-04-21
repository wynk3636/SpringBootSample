package com.example.demo.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//Aspect識別子=>AOPで、ログ出力などの共通処理を自動でキャッチし、ビジネスロジックのコーディングにログ出力などの関係ないものをなくせる
//実行前、実行後、エラーキャッチ時などに使える
@Aspect
@Component
public class LogAspect {
	/*
	//実行前のログ --> 最初の*の後にスペースがないとこけるので注意
	//@Before("execution(* com.example.demo.login.controller.LoginController.getLogin(..))")
	//名前が〜Controllerになってるクラスの全てのメソッドの実行前にログを出力
	@Before("execution(* *..*.*Controller.*(..))")
	public void startLog(JoinPoint jp) {
		//jp.getSignature()でメソッド名取得してログに出力
		System.out.println("Method Start " + jp.getSignature());
	}
	
	//実行前のログ
	//@After("execution(* com.example.demo.login.controller.LoginController.getLogin(..))")
	@After("execution(* *..*.*Controller.*(..))")
	public void endLog(JoinPoint jp) {
		System.out.println("Method Finish " + jp.getSignature());
	}
	
	*/
	//カスタマイズ
	@Around("execution(*  *..*.*Controller.*(..))")
	public Object startLogAround(ProceedingJoinPoint jp) throws Throwable{
		System.out.println("Around-Method Start " + jp.getSignature());
		
		try {
			Object result = jp.proceed();
			System.out.println("Around-Method Finish " + jp.getSignature());
			return result;
		}
		catch(Exception e){
			System.out.println("Around-Method Exception " + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}

	@Around("execution(*  *..*.*UserDao*.*(..))")
	public Object daoLog(ProceedingJoinPoint jp) throws Throwable{
		System.out.println("DaoLog Start " + jp.getSignature());
		
		try {
			Object result = jp.proceed();
			System.out.println("DaoLog Finish " + jp.getSignature());
			return result;
		}
		catch(Exception e){
			System.out.println("DaoLog Exception " + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}
}
