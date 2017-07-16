package com.tgweb.ssh.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.tgweb.ssh.entity.Book;
import com.tgweb.ssh.entity.User;
import com.tgweb.ssh.utils.HibernateUtils;
import com.tgweb.ssh.utils.SpringUtils;

public class SpringHibernateTest {

	private SpringUtils springUtils;
	private HibernateUtils hibernateUtils;
	private ApplicationContext ctx;
	private SessionFactory sessionFactory;

	@Before
	public void init() {
		springUtils = SpringUtils.getInstance();
		hibernateUtils = HibernateUtils.getInstance();
		ctx = springUtils.getApplicationContext();
		sessionFactory = hibernateUtils.getSessionFactory();
	}

	@After
	public void destory() {
		sessionFactory.close();
	}

	@Test
	public void testDataSource() throws SQLException {
		DataSource ds = (DataSource) ctx.getBean("dataSource");
		System.out.println(ds.getConnection());
	}

	@Test
	public void testMakeData() {
		// 由于java.lang.Double不能直接转为java.math.BigDecimal，所以金额不可以设置，需要手动填写

		Session session = hibernateUtils.getSession();

		Transaction transaction = session.beginTransaction();

		Book book1 = new Book();
		book1.setIsbn("A1001");
		book1.setName("Java");
		// book1.setPrice(23.23);
		book1.setStock(10);
		Book book2 = new Book();
		book2.setIsbn("A1002");
		book2.setName("C#");
		// book2.setPrice(34.89);
		book2.setStock(10);

		session.save(book1);
		session.save(book2);

		User user = new User("Jack");
		// user.setAccount(new Account(87.34));
		session.save(user);

		transaction.commit();
		// session.close();
	}

}
