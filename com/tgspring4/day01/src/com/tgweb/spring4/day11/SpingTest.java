package com.tgweb.spring4.day11;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpingTest {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotation.xml");

		try {
			UserObject user = (UserObject) ctx.getBean("userObject");
			user.show();
		} catch (Exception e) {
			System.out.println("create failed: user");
		}

		try {

			UserRepository userRepository = (UserRepository) ctx.getBean("userRepository");
			userRepository.insert();
		} catch (Exception e) {
			System.out.println("create failed: userRepository");
		}

		try {
			UserService userService = (UserService) ctx.getBean("userService");
			userService.save();
		} catch (Exception e) {
			System.out.println("create failed: userService");
		}

		try {
			UserController userController = (UserController) ctx.getBean("userController");
			userController.execute();
		} catch (Exception e) {
			System.out.println("create failed: userController");
		}

	}

}
