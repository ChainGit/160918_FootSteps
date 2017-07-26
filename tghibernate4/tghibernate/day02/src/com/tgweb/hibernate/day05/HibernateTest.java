package com.tgweb.hibernate.day05;

import java.util.Set;

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
	public void testOrderBy() {
		// Buy的降序排,order-by属性,NAME DESC
		Customer customer = session.get(Customer.class, 4L);
		Set<Buy> buys = customer.getBuys();
		System.out.println(buys);
	}

	@Test
	public void testCascade() {
		// 级联操作
		Customer customer = session.get(Customer.class, 2L);
		// 删除孤儿delete-orphan
		customer.getBuys().clear();
	}

	@Test
	public void testOne2ManyDelete() {
		Customer customer = session.get(Customer.class, 1L);
		// 级联删除，这样是不可以的，需要设置Customer的mapping文件的cascade(级联),开发时不建议设定该属性，建议手工SQL语句删除
		session.delete(customer);
	}

	@Test
	public void testOne2ManyUpdate() {
		Customer customer = session.get(Customer.class, 1L);
		customer.setName("OOOOO");
		customer.getBuys().iterator().next().setName("IIIIIII");
	}

	@Test
	public void testOne2ManyGet() {
		Customer customer = session.get(Customer.class, 1L);
		System.out.println(customer.getName());

		// 懒加载，和懒加载LazyInitializationException异常
		// org.hibernate.collection.internal.PersistentSet
		// 是一个Hibernate的集合类，在Entity中需要使用Set接口，同时也是一个代理类
		// session.close();
		System.out.println(customer.getBuys().getClass());
		// 发送了SELECT语句后统计，加强的加载为COUNT()函数
		System.out.println(customer.getBuys().size());
	}

	@Test
	public void testOne2ManySave() {
		// 双向 Buy N : 1 Customer
		// 双向是指在Hibernate端存储1端存储的是Set集合，而N才是数据库中的外键映射关系
		// 最好由N(Many)的一方管理父子关系，减少update的数量，在1(One)的一端的set节点指定Inverse=true来使得1的一端放弃维护关联关系，这样可以避免出现UPDATE
		// 先save的1的一端，再插入N的一端，减少UPDATE语句

		Customer customer = new Customer("May");
		Buy buy1 = new Buy("AAAA");
		Buy buy2 = new Buy("BBBB");

		buy1.setCustomer(customer);
		buy2.setCustomer(customer);

		customer.getBuys().add(buy1);
		customer.getBuys().add(buy2);

		// 两种保存的方式的不同
		// cascade设置为save-update,可以设置成级联保存
		session.save(customer);
		session.save(buy1);
		session.save(buy2);

		/**
		 * session.save(buy1); session.save(buy2); session.save(customer);
		 **/

	}
}
