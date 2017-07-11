package com.tgweb.hibernate.day01;

import java.util.HashSet;
import java.util.Set;

public class Department {

	private Long id;
	private String name;
	private Set<Employee> employees;

	{
		employees = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Override
	// 再打印时Set的成员不要打印，否则会无限互相调用，发生StackOverFlow异常
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Department [id=").append(id).append(", name=").append(name).append("]");
		return builder.toString();
	}

	public Department(String name, Set<Employee> employees) {
		super();
		this.name = name;
		this.employees = employees;
	}

	public Department(String name) {
		super();
		this.name = name;
	}

	public Department() {
	}

}
