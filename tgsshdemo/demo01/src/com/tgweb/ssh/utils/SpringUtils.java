package com.tgweb.ssh.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {

	private ApplicationContext ctx;

	private static SpringUtils instance;

	private SpringUtils() {

	}

	public ApplicationContext getApplicationContext(String configLocation) {
		if (ctx == null)
			ctx = new ClassPathXmlApplicationContext(configLocation);
		return ctx;
	}

	public ApplicationContext getApplicationContext() {
		return getApplicationContext("resources/applicationContext.xml");
	}

	public static SpringUtils getInstance() {
		if (instance == null)
			instance = new SpringUtils();
		return instance;
	}

}
