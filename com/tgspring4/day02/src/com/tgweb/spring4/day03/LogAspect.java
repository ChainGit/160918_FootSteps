package com.tgweb.spring4.day03;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect /* LogProxy */ {

	public void beforeAdvice/* beforeMethod */(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
	}

	public void afterAdvice(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " has ended");
	}

	public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends with result: " + result);
	}

	public void afterThrowingAdvice(JoinPoint joinPoint, Exception e) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " throw execption: " + e);
	}

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
