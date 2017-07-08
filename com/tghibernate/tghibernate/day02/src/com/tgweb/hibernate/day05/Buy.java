package com.tgweb.hibernate.day05;

public class Buy {

	private Long id;
	private String name;

	private Customer customer;

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Buy [id=").append(id).append(", name=").append(name).append(", customer=").append(customer)
				.append("]");
		return builder.toString();
	}

	public Buy(String name) {
		super();
		this.name = name;
	}

	public Buy() {
	}
}
