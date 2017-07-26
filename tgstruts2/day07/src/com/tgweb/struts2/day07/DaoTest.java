package com.tgweb.struts2.day07;

import org.junit.Test;

public class DaoTest {

	@Test
	public void test() {
		Dao dao = new Dao();
		System.out.println(dao.getEmployees());
		Employee emp1 = dao.get(100001L);
		System.out.println(emp1);
		dao.delete(100002L);
		dao.add(new Employee(null, "EEE", "EEE@xxx.com"));
		emp1.setEmpName("XXX");
		dao.update(emp1);
		System.out.println(dao.getEmployees());
	}
}
