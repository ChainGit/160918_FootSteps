package com.tgweb.hibernate.day01;

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
	public void testOne2OneGet() {
		Department department = session.get(Department.class, 1L);
		System.out.println(department.getName());

		// 懒加载和懒加载异常
		// session.close();
		System.out.println(department.getManager().getClass());
		// 需要配置property-ref的值，否则left outer join 的id匹配错误
		System.out.println(department.getManager().getName());

		// 可以手动使用获得的department的manager_id再写一条sql语句查询manager表得到manager
		// hibernate使用left outer join来获得一张整表
		System.out.println(department.getId());
		System.out.println(department.getManager().getId());
	}

	@Test
	public void testOne2OneSave() {
		Department department1 = new Department("DEPT-AA");
		Department department2 = new Department("DEPT-BB");

		Manager manager1 = new Manager("MGR-AA");
		Manager manager2 = new Manager("MGR-BB");
		Manager manager3 = new Manager("MGR-CC");

		// 一一对应，外键只能设置一个，另一个用one-to-one即可，不能同时设置many-to-one，会造成映射混乱
		department1.setManager(manager1);
		manager1.setDepartment(department1);

		department2.setManager(manager2);
		manager2.setDepartment(department2);

		// 先保存1端Manager再保存N（限制为1）端的Department，避免多余的UPDATE
		session.save(manager1);
		session.save(manager2);
		session.save(manager3);
		session.save(department1);
		session.save(department2);
	}

}
