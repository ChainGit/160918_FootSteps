package com.tgweb.spring4.day02;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
@Aspect
public class LogProxy /* LogAspect */ {

	@Before("AdviceExpression.isArithmeticCalculatorAdvice()")
	public void beforeAdvice/* beforeMethod */(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
	}

	@After("AdviceExpression.isArithmeticCalculatorAdvice()")
	public void afterAdvice(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " has ended");
	}

	@AfterReturning(value = "AdviceExpression.isArithmeticCalculatorAdvice()", returning = "result")
	public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends with result: " + result);
	}

	@AfterThrowing(value = "AdviceExpression.isArithmeticCalculatorAdvice()", throwing = "e")
	public void afterThrowingAdvice(JoinPoint joinPoint, Exception e) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " throw execption: " + e);
	}

	@Around(value = "AdviceExpression.isArithmeticCalculatorAdvice()")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		String methodName = proceedingJoinPoint.getSignature().getName();
		Object result = null;

		try {
			// 前置通知
			System.out.println(
					"The method^ " + methodName + " begins with " + Arrays.asList(proceedingJoinPoint.getArgs()));
			result = proceedingJoinPoint.proceed();
			// 返回通知
			System.out.println("The method^ " + methodName + " ends with result: " + result);
		} catch (Throwable e) {
			// 异常通知
			System.out.println("The method^ " + methodName + " throw execption: " + e);
			throw new RuntimeException(e);
		} finally {
			// 后置通知
			System.out.println("The method^ " + methodName + " has ended");
		}

		return result;
	}
}
