package com.tgweb.hibernate.day02;

import java.util.List;

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
	public void testJoinedSubClassDelete() {
		Person person = session.get(Person.class, 4L);// 实际为Student
		// 会将student对应的一并删除
		session.delete(person);
	}

	@Test
	public void testJoinedSubClassUpdate() {
		Person person = session.get(Person.class, 4L);// 实际为Student
		Student student = (Student) person;
		student.setSchool("SDSDSD");
	}

	@Test
	public void testJoinedSubClassQuery() {
		// 查询两张表
		// 查询Person里的Student时，为left outer join
		List<Person> persons = session.createQuery("FROM Person", Person.class).list();
		System.out.println(persons);
		// 查询Student时，为inner join
		List<Student> students = session.createQuery("FROM Student", Student.class).list();
		System.out.println(students);
	}

	@Test
	public void testJoinedSubClassGet() {
		// left outer join
		Person person = session.get(Person.class, 1L);
		// 懒加载
		System.out.println(person);
		// inner join
		Person person2 = session.get(Person.class, 2L);
		System.out.println(person2);
		Student student = session.get(Student.class, 4L);
		System.out.println(student);
	}

	@Test
	public void testJoinedSubClassSave() {
		Person person = new Person("AAAA", 12);
		Student student = new Student("BBBB", 23, "XXXX");
		// 先保存两个Person,再保存Student,插入两张表，性能较subclass降低，但无浪费（无冗余），无辨别者，可以设置列约束
		session.save(person);
		session.save(student);
	}

}
