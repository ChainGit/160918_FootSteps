package com.tgweb.hibernate.day02;

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
	public void testOne2OneDelete() {
		// 因为是主键一对一映射，Manager和Department是一一对应，不存在多对多映射，所以可以手动删除
		Department department = session.get(Department.class, 2L);
		Manager manager = session.get(Manager.class, 2L);
		// 需要先删除Department再删除Manager
		session.delete(department);
		session.delete(manager);
	}

	@Test
	public void testOne2OneUpdate() {
		Department department = session.get(Department.class, 1L);
		department.setName("AAAAA");
		department.getManager().setName("BBBBB");
	}

	@Test
	public void testOne2OneGet() {
		Department department = session.get(Department.class, 1L);
		System.out.println(department.getName());

		// session.close();
		System.out.println(department.getManager().getClass());
		// 无需设定property-ref
		System.out.println(department.getId());
		System.out.println(department.getManager().getId());
		System.out.println(department.getManager().getName());
	}

	@Test
	public void testOne2OneSave() {
		// 基于主键的One2One映射
		Department department = new Department("DEPT-AA");
		Manager manager = new Manager("MGR-AA");

		department.setManager(manager);
		manager.setDepartment(department);

		// 交换顺序不会多出UPDATE，因为均为主键映射，即使department在前也会先执行manager
		session.save(department);
		session.save(manager);

	}

}
