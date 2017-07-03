package com.tgweb.spring4.day05;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	@Test
	public void test() {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-scope.xml");

		Person james1 = (Person) ctx.getBean("james1");
		System.out.println(james1);

		Person james2 = (Person) ctx.getBean("james2");
		System.out.println(james2);

		Person james3 = (Person) ctx.getBean("james2");
		System.out.println(james3);

		Person james4 = (Person) ctx.getBean("james3");
		System.out.println(james4);

		Person james5 = (Person) ctx.getBean("james3");
		System.out.println(james5);

		System.out.println(james1 == james2);
		System.out.println(james2 == james3);
		System.out.println(james4 == james5);

	}
}
