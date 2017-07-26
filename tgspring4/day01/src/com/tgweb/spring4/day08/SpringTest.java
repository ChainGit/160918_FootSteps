package com.tgweb.spring4.day08;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	@Test
	public void test() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans-cycle.xml");
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);
		ctx.close();
	}

}
