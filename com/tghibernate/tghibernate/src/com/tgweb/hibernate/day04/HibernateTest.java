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
	public void testMany2ManyGet() {
		Category category1 = session.get(Category.class, 3L);
		System.out.println(category1.getName());

		// 懒加载和懒加载异常
		System.out.println(category1.getItems().getClass());
		// inner join
		System.out.println(category1.getItems().iterator().next().getName());
	}

	@Test
	public void testMany2ManySave() {
		// 双向多对多映射
		Category category1 = new Category("CATEGORY-AA");
		Category category2 = new Category("CATEGORY-BB");

		Item item1 = new Item("ITEM-AA");
		Item item2 = new Item("ITEM-BB");

		category1.getItems().add(item1);
		category1.getItems().add(item2);

		category2.getItems().add(item1);
		category2.getItems().add(item2);

		item1.getCategories().add(category1);
		item1.getCategories().add(category2);

		item2.getCategories().add(category1);
		item2.getCategories().add(category2);

		// 保存顺序都可以，但是有一方需要进行Inverse=true放弃维护双向关系，不然就会出现重复添加到中间表，会抛异常
		session.save(category1);
		session.save(category2);
		session.save(item1);
		session.save(item2);

	}
}
