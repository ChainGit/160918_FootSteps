package com.tgweb.ssh.test;

import org.hibernate.Transaction;
import org.junit.Test;

import com.tgweb.ssh.service.BookShopService;
import com.tgweb.ssh.utils.HibernateUtils;
import com.tgweb.ssh.utils.SpringUtils;

public class BookShopServiceImplTest {

	private BookShopService bookShopService;

	{
		bookShopService = SpringUtils.getInstance().getApplicationContext().getBean(BookShopService.class);
	}

	@Test
	public void test() {
		Transaction transaction = HibernateUtils.getInstance().getSession().beginTransaction();
		bookShopService.purchase(1L, 1L, 1);
		transaction.commit();
	}

}
