package com.tgweb.spring4.day03;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tgweb.spring4.day07.Person;

public class SpringTest {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-autowire.xml");

		Person james1 = (Person) ctx.getBean("james1");
		System.out.println(james1);

		Person james2 = (Person) ctx.getBean("james2");
		System.out.println(james2);

		Person james3 = (Person) ctx.getBean("james3");
		System.out.println(james3);
	}

}
