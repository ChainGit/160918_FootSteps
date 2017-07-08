package com.tgweb.hibernate.day01;

import java.io.Serializable;
import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest {

	@Test
	public void test() {

		// 加载配置Configuration
		Configuration configuration = new Configuration().configure();

		// 根据配置获取SessionFactory
		SessionFactory sessionFactory = null;
		sessionFactory = configuration.buildSessionFactory();

		/*
		 * ServiceRegistry serviceRegistry = new
		 * StandardServiceRegistryBuilder()
		 * .applySettings(configuration.getProperties()).build();
		 * 
		 * sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		 */

		// 由SessionFactory创建Session
		Session session = sessionFactory.openSession();

		// 由Session新建事务
		Transaction transaction = session.beginTransaction();

		// 新建一个要保存的New对象
		News news = new News("Java", "James" + ((int) Math.random() * 10), new Date(new java.util.Date().getTime()));
		System.out.println(news);

		// 使用Session的save来保存数据到对象和数据库
		Serializable serializableId = session.save(news);
		System.out.println(serializableId);

		// 提交这个事务，使得事务生效
		transaction.commit();

		// Hibernate的Session作为一级缓存
		// 获取刚才提交的事务中的New对象
		// 在这个Session内的就不需要再从数据库获取了，因为Hibernate和数据库是映射关系，简单讲就是对应关系
		News news2 = session.get(News.class, serializableId);
		System.out.println(news2);

		// 不在Session内的就需要从数据库获取
		// 利用反射来创建News对象，Hibernate是一个低侵入式的框架，类本身不需要继承或实现任何的类或接口
		News news3 = session.get(News.class, 1L);
		System.out.println(news3);

		// 获取完成后一旦在Session中就不要再获取了
		News news4 = session.get(News.class, 1L);
		System.out.println(news4);

		// 关闭Session
		session.close();

		// 关闭SessionFactory
		sessionFactory.close();

	}
}
