package com.tgweb.spring4.day02;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class AdviceExpression {

	@Pointcut("execution(* com.tgweb.spring4.day02.ArithmeticCalculator.*(int, int))")
	public void isArithmeticCalculatorAdvice() {
	}
}
