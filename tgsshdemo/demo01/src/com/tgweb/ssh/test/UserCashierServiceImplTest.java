package com.tgweb.ssh.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tgweb.ssh.service.UserCashierService;
import com.tgweb.ssh.utils.HibernateUtils;
import com.tgweb.ssh.utils.SpringUtils;

public class UserCashierServiceImplTest {

	private UserCashierService userCashierService;
	private Transaction transaction;

	@Before
	public void init() {
		userCashierService = SpringUtils.getInstance().getApplicationContext().getBean(UserCashierService.class);
		transaction = HibernateUtils.getInstance().getSession().beginTransaction();
	}

	@After
	public void destory() {
		transaction.commit();
	}

	@Test
	public void test() {

	}

	@Test
	public void testCash() {
		List<Long> bookIds = new ArrayList<>();
		bookIds.add(new Long(1));
		bookIds.add(new Long(2));
		userCashierService.cash(1L, bookIds);
	}

	@Test
	public void testCash2() {
		Map<Long, Integer> books = new LinkedHashMap<>();
		books.put(1L, 2);
		books.put(2L, 1);
		userCashierService.cash(1L, books);
	}

}
