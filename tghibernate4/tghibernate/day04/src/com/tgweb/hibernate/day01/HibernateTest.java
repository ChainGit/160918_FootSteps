package com.tgweb.hibernate.day01;

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
	public void testSubClassDelete() {
		Person person = session.get(Person.class, 1L);
		session.delete(person);
		Student student = session.get(Student.class, 2L);
		session.delete(student);
	}

	@Test
	public void testSubClassUpdate() {
		Person person = session.get(Person.class, 1L);
		System.out.println(person);

		Student student = session.get(Student.class, 2L);
		System.out.println(student);
		student.setSchool(null);
		Person person2 = student;
		// 无法将Student 转为Person，因为person2的所引用的对象仍然是Student
		session.save(person2);
		System.out.println(person2.getClass().getName());
	}

	@Test
	public void testSubClassQuery() {
		// createQuery参数FROM 后面的表名和Mapping配置文件对应
		List<Person> persons = session.createQuery("FROM Person", Person.class).list();
		List<Student> students = session.createQuery("FROM Student", Student.class).list();
		// 包含Person和Student
		System.out.println(persons);
		// 只有Student
		System.out.println(students);
	}

	@Test
	public void testSubClassGet() {
		Person person = session.get(Person.class, 1L);
		Student student = session.get(Student.class, 2L);

		System.out.println(person);
		System.out.println(student);

		// 对调获取
		// Person的继承类的实例，实际是Student
		Person person2 = session.get(Person.class, 2L);
		// 因为SQL语句为
		// where
		// student0_.person_id=?
		// and student0_.type='Student'
		// 所以查询结果集为空，即student2为Null
		Student student2 = session.get(Student.class, 1L);

		System.out.println(person2.getClass().getName());// com.tgweb.hibernate.day01.Student
		// System.out.println(student2.getClass().getName());

		// 调用的是Person继承类Student的toString()方法
		// 懒加载和懒加载异常
		System.out.println(person2);
		System.out.println(student2);

	}

	@Test
	public void testSubClassSave() {
		// 辨别者类，若类继承较深则映射也比较麻烦
		Person person = new Person("AAAA", 12);
		Student student = new Student("BBBB", 23, "XXXX");
		// 数据库中的TYPE区别是Person还是Student，且Student_school列不能约束为非空
		session.save(person);
		session.save(student);
	}

}
