package com.tgweb.spring4.day12;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpingTest {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotation2.xml");

		UserController userController = (UserController) ctx.getBean("userController");
		userController.execute();

	}

}
