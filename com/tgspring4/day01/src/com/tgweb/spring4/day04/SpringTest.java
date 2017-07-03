package com.tgweb.spring4.day04;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-relation.xml");

		Person jack1 = (Person) ctx.getBean("jack1");
		System.out.println(jack1);

		Person jack2 = (Person) ctx.getBean("jack2");
		System.out.println(jack2);

		Person jack3 = (Person) ctx.getBean("jack3");
		System.out.println(jack3);

		Person jack4 = (Person) ctx.getBean("jack4");
		System.out.println(jack4);

		Person jack5 = (Person) ctx.getBean("jack5");
		System.out.println(jack5);
	}

}
