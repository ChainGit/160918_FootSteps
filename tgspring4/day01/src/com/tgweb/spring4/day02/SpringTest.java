package com.tgweb.spring4.day02;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	@Test
	public void test() {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");

		Person peter1 = (Person) ctx.getBean("peter1");
		peter1.show();

		PersonMap may1 = (PersonMap) ctx.getBean("may1");
		may1.show();

		DataSource ds = (DataSource) ctx.getBean("dataSource");
		System.out.println(ds);

		DataSource ds2 = (DataSource) ctx.getBean("dataSource2");
		System.out.println(ds2);

		Person may2 = (Person) ctx.getBean("may2");
		may2.show();
	}

}
