package com.tgweb.spring4.day02;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserCashierImplTest {

	private UserCashier userCashier;
	private ApplicationContext ctx;

	{
		ctx = new ClassPathXmlApplicationContext("spring-transcation.xml");
		userCashier = ctx.getBean(UserCashier.class);
	}

	@Test
	public void testCash() {
		List<Long> bookIds = new ArrayList<>();
		bookIds.add(new Long(1));
		bookIds.add(new Long(2));
		userCashier.cash(1L, bookIds);
	}
}
