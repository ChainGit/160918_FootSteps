package com.tgweb.spring4.day07;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-spel.xml");

		House house = (House) ctx.getBean("house");
		System.out.println(house);

		Car car = (Car) ctx.getBean("car");
		System.out.println(car);

		Person james = (Person) ctx.getBean("james");
		System.out.println(james);
	}

}
