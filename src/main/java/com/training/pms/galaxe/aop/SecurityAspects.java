package com.training.pms.galaxe.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SecurityAspects {
	@Before(value =  "execution(* com.training.pms.galaxe.service.ProductServiceImpl.*(..))")
	public void LoginSecurity() {
		System.out.println("###User LoggedIn: "+new Date()+ " By Security");
	}

	@After(value =  "execution(* com.training.pms.galaxe.service.ProductServiceImpl.*(..))")
	public void LogoutSecurity() {
		System.out.println("###User LoggedOut: "+new Date()+ " By Security");
	}
	
}
