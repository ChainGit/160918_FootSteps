package com.tgweb.struts2.day07;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dao {

	private static Map<Long, Employee> emps = new LinkedHashMap<>();

	static {
		emps.put(100001L, new Employee(100001L, "AAA", "AAA@xxx.com"));
		emps.put(100002L, new Employee(100002L, "BBB", "BBB@xxx.com"));
		emps.put(100003L, new Employee(100003L, "CCC", "CCC@xxx.com"));
		emps.put(100004L, new Employee(100004L, "DDD", "DDD@xxx.com"));
	}

	public List<Employee> getEmployees() {
		return new ArrayList<>(emps.values());
	}

	public void delete(Long empId) {
		emps.remove(empId);
	}

	public void add(Employee emp) {
		Long empId = System.currentTimeMillis();
		emp.setEmpId(empId);
		emps.put(empId, emp);
	}

	public void update(Employee emp) {
		Long empId = emp.getEmpId();
		emps.put(empId, emp);
	}

	public Employee get(Long empId) {
		return emps.get(empId);
	}
}
