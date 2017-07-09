package com.tgweb.hibernate.day03;

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
	public void testUnionSubClassDelete() {
		// 删除操作也是分开删除
	}

	@Test
	public void testUnionSubClassUpdate() {
		Person person1 = session.get(Person.class, 2L);// 实际为Student
		System.out.println(person1);
		Student student1 = (Student) person1;
		// 只需修改Student表即可
		student1.setSchool("HASSASA");
		student1.setName("asssaS");
	}

	@Test
	public void testUnionSubClassQuery() {
		// 读取Person 会使用子查询，读取Person和Student表，用union方法取出重复部分，比较复杂
		List<Person> persons = session.createQuery("FROM Person", Person.class).list();
		System.out.println(persons);

		// 读取Student则直接读取Student表
		List<Student> students = session.createQuery("FROM Student", Student.class).list();
		System.out.println(students);
	}

	@Test
	public void testUnionSubClassGet() {
		// 从不同的 表读取
		Person person1 = session.get(Person.class, 1L);
		System.out.println(person1);
		Student student1 = session.get(Student.class, 2L);
		System.out.println(student1);
	}

	@Test
	public void testUnionSubClassSave() {
		// 两张独立的表,分别插入，但是独立的表会有很大的冗余（重复的数据）
		Person person1 = new Person("Jack", 22);
		person1.setId(1L);
		Student student1 = new Student("May", 23, "ASASAS");
		student1.setId(2L);

		session.save(person1);
		session.save(student1);
	}

}
