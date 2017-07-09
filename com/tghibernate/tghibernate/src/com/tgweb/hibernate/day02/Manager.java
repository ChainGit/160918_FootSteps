package com.tgweb.hibernate.day02;

public class Manager {

	private Long id;
	private String name;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Manager [id=").append(id).append(", name=").append(name).append(", department=")
				.append(department).append("]");
		return builder.toString();
	}

	public Manager(String name) {
		super();
		this.name = name;
	}

	public Manager() {
	}

}
