package com.tgweb.spring4.day10;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tgweb.spring4.day08.Car;

public class SprintTest {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-factorybean.xml");

		Car car1 = (Car) ctx.getBean("car1");
		System.out.println(car1);

		Car car2 = (Car) ctx.getBean("car2");
		System.out.println(car2);
	}

}
