package com.tgweb.hibernate.day03;

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
	public void testComponent() {
		// 组成关系（Value）:Score没有ID
		Student student = new Student();
		student.setName("Jack");
		student.setAge(21);
		Score score = new Score();
		score.setChinese(80);
		score.setMath(92);
		score.setEnglish(90);
		student.setScore(score);

		session.save(student);

		System.out.println(score);
		System.out.println(student);
	}

}
