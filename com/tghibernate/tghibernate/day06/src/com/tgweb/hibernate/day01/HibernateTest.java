package com.tgweb.hibernate.day01;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.junit.Test;

public class HibernateTest {

	@Test
	public void testBatch() {
		// 批量操作，推荐使用JDBC原生的API
		Session session = HibernateUtils.getInstance().getSession();
		session.doWork(new Work() {

			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println(connection);
			}

		});
	}

	@Test
	public void testManageSession() {
		PersonDao personDao = new PersonDaoImpl();
		Session session = HibernateUtils.getInstance().getSession();
		Transaction transaction = session.beginTransaction();
		Person person1 = new Person("JACK1");
		Person person2 = new Person("JACK2");
		Person person3 = new Person("JACK3");
		personDao.save(person1);
		personDao.save(person2);
		personDao.save(person3);
		transaction.commit();
		// 与线程绑定的Session，会在事务提交后线程关闭，从而session也关闭
		System.out.println(session.isOpen());

		session = HibernateUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		Person person4 = new Person("JACK4");
		personDao.save(person4);
		transaction.commit();
		System.out.println(session.isOpen());
	}

}
