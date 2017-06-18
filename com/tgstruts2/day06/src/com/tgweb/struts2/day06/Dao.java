package com.tgweb.struts2.day06;

import java.util.ArrayList;
import java.util.List;

public class Dao {

	public List<Role> getRoles() {
		List<Role> roles = new ArrayList<>();
		roles.add(new Role(10001, "AAA"));
		roles.add(new Role(10002, "BBB"));
		roles.add(new Role(10003, "CCC"));
		return roles;
	}

	public List<Department> getDepartments() {
		List<Department> depts = new ArrayList<>();
		depts.add(new Department(1000001, "aaa"));
		depts.add(new Department(1000002, "bbb"));
		depts.add(new Department(1000003, "ccc"));
		depts.add(new Department(1000004, "ddd"));
		return depts;
	}

}
