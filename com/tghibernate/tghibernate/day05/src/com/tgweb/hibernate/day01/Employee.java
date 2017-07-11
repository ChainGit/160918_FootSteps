package com.tgweb.hibernate.day01;

public class Employee {

	private Long id;
	private String name;
	private Integer salary;
	private String email;

	private Department department;

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

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee(String name, Integer salary, String email, Department department) {
		super();
		this.name = name;
		this.salary = salary;
		this.email = email;
		this.department = department;
	}

	public Employee(String name, Integer salary, String email) {
		super();
		this.name = name;
		this.salary = salary;
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=").append(id).append(", name=").append(name).append(", salary=").append(salary)
				.append(", email=").append(email).append("]");
		return builder.toString();
	}

	public Employee() {
	}
}
