package com.tgweb.hibernate.day03;

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
	public void testMany2ManyDelete() {
		// 删除会先删除中间表的Category的记录，再删除Category本身，不会删除Item
		Category category2 = session.get(Category.class, 2L);
		session.delete(category2);
	}

	@Test
	public void testMany2ManyUpdate() {
		Category category1 = session.get(Category.class, 1L);
		Category category2 = session.get(Category.class, 2L);
		// Item 和 Category 是独立的，只是引用相连，修改会相互影响
		System.out.println(category2.getItems().iterator().next().getName());
		category1.getItems().iterator().next().setName("HGAYGS");
		System.out.println(category2.getItems().iterator().next().getName());
	}

	@Test
	public void testMany2ManyGet() {
		Category category1 = session.get(Category.class, 1L);
		category1.getName();

		// 懒加载和懒加载异常
		// session.close();
		Set<Item> items = category1.getItems();
		// inner join
		System.out.println(items.size());

	}

	@Test
	public void testMany2ManySave() {
		// 单向的多对多映射(Category -> Item)
		Category category1 = new Category("CATEGORY-AA");
		Category category2 = new Category("CATEGORY-BB");

		Item item1 = new Item("ITEM-AA");
		Item item2 = new Item("ITEM-BB");

		category1.getItems().add(item1);
		category1.getItems().add(item2);

		category2.getItems().add(item1);
		category2.getItems().add(item2);

		// Item和Category的保存顺序无所谓，最后都会执行4条INSERT到中间表
		session.save(item1);
		session.save(item2);

		session.save(category1);
		session.save(category2);

	}

}
