package com.tgweb.hibernate.day04;

public class Customer {

	private Long id;
	private String name;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [id=").append(id).append(", name=").append(name).append("]");
		return builder.toString();
	}

	public Customer(String name) {
		super();
		this.name = name;
	}

	public Customer() {
	}

}
