package com.tgweb.spring4.day03;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

public class ValidationAspect {

	public void validate(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.println("validate run: method: " + methodName + " , args: " + Arrays.asList(args));
	}

}
