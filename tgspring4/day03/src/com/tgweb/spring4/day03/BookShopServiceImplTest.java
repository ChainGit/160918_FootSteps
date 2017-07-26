package com.tgweb.spring4.day03;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookShopServiceImplTest {

	private ApplicationContext ctx;
	private BookShopService bookShopService;

	{
		ctx = new ClassPathXmlApplicationContext("spring-transcation2.xml");
		bookShopService = ctx.getBean(BookShopService.class);
	}

	@Test
	public void testPurchase() {
		bookShopService.purchase(new LongId(1).getId(), new LongId(1).getId(), 1);
	}

}
