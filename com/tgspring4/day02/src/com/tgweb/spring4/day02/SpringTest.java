package com.tgweb.spring4.day02;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) ctx.getBean("arithmeticCalculator");

		int result = 0;

		result = arithmeticCalculator.add(1, 2);
		System.out.println(result);

		result = arithmeticCalculator.div(4, 0);
		System.out.println(result);
	}

}
