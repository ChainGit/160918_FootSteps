package com.tgweb.spring4.day02;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class ValidationAspect {

	@Before("AdviceExpression.isArithmeticCalculatorAdvice()")
	public void validate(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.println("validate run: method: " + methodName + " , args: " + Arrays.asList(args));
	}

}
