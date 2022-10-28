package com.training.pms.galaxe.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspects {

	@Before(value =  "execution(* com.training.pms.galaxe.service.ProductServiceImpl.*(..))")
	public void doLogging() {
		System.out.println("###Logged in at :"+new Date()+ " By Aspects");
	}

	@After(value =  "execution(* com.training.pms.galaxe.service.ProductServiceImpl.*(..))")
	public void doSomeWork() {
		System.out.println("###Do Some work called at :"+new Date()+ " By Aspects");
	}
	
	@Around(value =  "execution(* com.training.pms.galaxe.service.ProductServiceImpl.*(..))")
	public Object around(ProceedingJoinPoint joinpoint) throws Throwable {
		Signature methods = joinpoint.getSignature();
		
		System.out.println("1. ###Before the method gets called :: "+new Date()+ " By Aspects");
		//decide whether to proceed with the method execution
		Object retval = joinpoint.proceed();
		System.out.println("4. ###After the method gets called :: "+new Date()+ " By Aspects");
		return retval;
	}
}
