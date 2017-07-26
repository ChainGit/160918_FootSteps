package com.tgweb.hibernate.day01;

public class Department {

	private Long id;
	private String name;
	private Manager manager;

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

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Department(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Department [id=").append(id).append(", name=").append(name).append(", manager=").append(manager)
				.append("]");
		return builder.toString();
	}

	public Department() {
	}

}
