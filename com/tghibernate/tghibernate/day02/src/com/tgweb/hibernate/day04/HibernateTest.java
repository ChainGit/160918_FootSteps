package com.tgweb.hibernate.day04;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateTest {

	private Configuration configuration;
	private SessionFactory sessionFactory;

	private Session session;
	private Transaction transaction;

	@Before
	public void init() {
		configuration = new Configuration().configure();
		sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	@After
	public void destory() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	@Test
	public void testMany2OneDelete() {
		// 级联删除时，N:1的关系，Customer想要删除，必须先删除所有的Buy，外键关联关系
		Customer customer = session.get(Customer.class, 1L);
		System.out.println(customer);
		// 会发生异常
		session.delete(customer);
	}

	@Test
	public void testMany2OneUpdate() {
		Buy buy1 = session.get(Buy.class, 1L);
		System.out.println(buy1.getName());
		buy1.setName("ASASAS");
		// 在使用Customer的时候才会去发送SQL查询语句，获得Customer对象后再更新值，发送UPDATE语句
		buy1.getCustomer().setName("AAAA");
	}

	@Test
	public void testMany2OneGet() {
		// 懒加载，如果在使用对象中关联对象时session关闭，则会发生异常
		Buy buy1 = session.get(Buy.class, 1L);
		System.out.println(buy1.getName());

		// 会发生异常
		// session.close();

		// 只有在到Customer时才会发送SQL查询语句
		// Customer为懒加载代理对象
		System.out.println(buy1.getCustomer());
	}

	@Test
	public void testMany2OneSave2() {
		// 单向 Order N : Customer 1
		Customer customer = new Customer("Jack");

		Buy buy1 = new Buy("Apple");
		Buy buy2 = new Buy("Banana");

		buy1.setCustomer(customer);
		buy2.setCustomer(customer);

		// 多了两个update，因为并不知道buy1和buy2并不知道customer的ID
		session.save(buy1);
		session.save(buy2);
		session.save(customer);

		System.out.println(customer);
		System.out.println(buy1);
		System.out.println(buy2);
	}

	@Test
	public void testMany2OneSave() {
		// 单向 Order N : Customer 1
		Customer customer = new Customer("Jack");

		Buy buy1 = new Buy("Apple");
		Buy buy2 = new Buy("Banana");

		buy1.setCustomer(customer);
		buy2.setCustomer(customer);

		session.save(customer);
		session.save(buy1);
		session.save(buy2);

		System.out.println(customer);
		System.out.println(buy1);
		System.out.println(buy2);
	}
}
