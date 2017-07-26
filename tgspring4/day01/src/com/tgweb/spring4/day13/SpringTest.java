package com.tgweb.spring4.day13;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-generic-di.xml");

		UserService userService = (UserService) ctx.getBean("userService");
		userService.save();

	}
}
