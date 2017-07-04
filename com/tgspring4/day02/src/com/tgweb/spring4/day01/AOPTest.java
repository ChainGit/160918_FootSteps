package com.tgweb.spring4.day01;

import org.junit.Test;

public class AOPTest {

	@Test
	public void test2() {
		ArithmeticCalculator target = new ArithmeticCalculatorImpl();
		ArithmeticCalculator proxy = new ArithmeticCalculatorLogProxy(target).getArithmeticCalculatorLogProxy();
		int result = proxy.add(1, 2);
		System.out.println(result);
	}

	// @Test
	public void test1() {
		ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculatorWithLogImpl();
		int result = arithmeticCalculator.add(1, 2);
		System.out.println(result);
	}
}
