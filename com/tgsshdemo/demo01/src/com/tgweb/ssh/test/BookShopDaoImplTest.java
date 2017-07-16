package com.tgweb.ssh.test;

import org.hibernate.Transaction;
import org.junit.Test;

import com.tgweb.ssh.dao.BookShopDao;
import com.tgweb.ssh.dao.impl.BookShopDaoImpl;
import com.tgweb.ssh.utils.HibernateUtils;

public class BookShopDaoImplTest {

	private BookShopDao bookShopDao;

	{
		bookShopDao = new BookShopDaoImpl();
	}

	/**
	 * 事务的传播行为
	 * 
	 */

	@Test
	public void test() {
		Transaction transaction = HibernateUtils.getInstance().getSession().beginTransaction();
		System.out.println(bookShopDao.getBookPriceById(1L));
		bookShopDao.updateBookStockById(1L, 5);
		bookShopDao.updateUserBalanceById(1L, 111.11);
		transaction.commit();
	}

	@Test
	public void test2() {
		Transaction transaction = null;

		transaction = HibernateUtils.getInstance().getSession().beginTransaction();
		System.out.println(bookShopDao.getBookPriceById(1L));
		bookShopDao.updateBookStockById(1L, 5);
		transaction.commit();

		transaction = HibernateUtils.getInstance().getSession().beginTransaction();
		bookShopDao.updateUserBalanceById(1L, 111.11);
		transaction.commit();
	}

}
