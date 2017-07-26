package com.tgweb.spring4.day03;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserCashierImplTest {

	private UserCashier userCashier;
	private ApplicationContext ctx;

	{
		ctx = new ClassPathXmlApplicationContext("spring-transcation2.xml");
		userCashier = ctx.getBean(UserCashier.class);
	}

	@Test
	public void testCash() {
		List<Long> bookIds = new ArrayList<>();
		bookIds.add(new Long(1));
		bookIds.add(new Long(2));
		userCashier.cash(1L, bookIds);
	}

	@Test
	public void testCash2() {
		Map<Long, Integer> books = new LinkedHashMap<>();
		books.put(1L, 2);
		books.put(2L, 1);
		userCashier.cash(1L, books);
	}
}
