package com.tgweb.spring4.day01;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	@Test
	public void test() {
		/*
		 * Person person = new Person(); person.setName("tom");
		 */

		// IOC 和 DI
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");
		Person person = (Person) ctx.getBean("tom");
		person.show();

		Person jack1 = (Person) ctx.getBean("jack1");
		jack1.show();

		Person jack2 = (Person) ctx.getBean("jack2");
		jack2.show();

		Person jack3 = (Person) ctx.getBean("jack3");
		jack3.show();

		Person jack4 = (Person) ctx.getBean("jack4");
		jack4.show();

		Person jack5 = (Person) ctx.getBean("jack5");
		jack5.show();

		Person jack6 = (Person) ctx.getBean("jack6");
		jack6.show();

		Person jack7 = (Person) ctx.getBean("jack7");
		jack7.show();
	}

}
