package com.tgweb.hibernate.day02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.Hibernate;
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
	public void testBigData() throws Exception {
		Text text1 = new Text();
		Blob image = null;
		InputStream is = new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream("a.jpg"));
		image = Hibernate.getLobCreator(session).createBlob(is, is.available());
		text1.setImage1(image);
		Serializable id = session.save(text1);
		session.clear();
		Text text2 = session.get(Text.class, id);
		Blob image2 = text2.getImage1();
		InputStream is2 = image2.getBinaryStream();
		OutputStream os2 = new BufferedOutputStream(new FileOutputStream("E:/Temps/aa.jpg"));
		byte[] buf = new byte[1024];
		while (is2.read(buf) != -1) {
			os2.write(buf);
		}
		is2.close();
		os2.flush();
		os2.close();
	}

	@Test
	public void testDate() {
		// 将Person保存到数据库中
		Person person1 = new Person();
		Serializable serializableId = session.save(person1);
		System.out.println(person1);
		session.clear();
		// 测试能否从数据库还原到Person
		// 注意：TIME,DATE,TIMESTAMP与java.sql.Time,java.sql.Date,java.sql.Timestamp一一对应，不可混用，因为他们都是继承自java.util.Date
		// 使用java.util.Date不会出问题，java.sql.Time,java.sql.Date,java.sql.Timestamp都可以赋值给java.util.Date，同理java.util.Date可以通过getTime()将时间转换为Long再复制给这三个
		Person person2 = session.get(Person.class, serializableId);
		System.out.println(person2);
	}

}
