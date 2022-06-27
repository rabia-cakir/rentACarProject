package com.kodlamaio.rentACar.core.utilities.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
/*
	@Around("execution(* com.kodlamaio.rentACar.business.concretes.BrandManager.deleteById(int))")
	public void beforeLog(ProceedingJoinPoint proceedingJoinPoint) {
		try {
			System.out.println("before logging");
			proceedingJoinPoint.proceed();
			System.out.println("after returning");
		} catch (Throwable e) {
			System.out.println("after throwing");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("after finally");
	}  */

}
