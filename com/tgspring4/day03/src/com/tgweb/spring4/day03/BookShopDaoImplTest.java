package com.tgweb.spring4.day03;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookShopDaoImplTest {

	private ApplicationContext ctx;
	private BookShopDao bookShopDao;

	{
		ctx = new ClassPathXmlApplicationContext("spring-transcation2.xml");
		bookShopDao = ctx.getBean(BookShopDao.class);
	}

	@Test
	public void testGetBookPriceById() {
		System.out.println(bookShopDao.getBookPriceById(new LongId(1L).getId()));
	}

	@Test
	public void testUpdateBookStockById() {
		bookShopDao.updateBookStockById(new LongId(2L).getId(), 1);
	}

	@Test
	public void testUpdateCustomerBalanceById() {
		bookShopDao.updateCustomerBalanceById(new LongId(1L).getId(), new Money(0.12).getMoney());
	}

}
