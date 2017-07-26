package com.tgweb.hibernate.day05;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private Long id;
	private String name;
	private Set<Buy> buys;

	{
		// 防止发生空指针异常
		buys = new HashSet<>();
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

	public Set<Buy> getBuys() {
		return buys;
	}

	public void setBuys(Set<Buy> buys) {
		this.buys = buys;
	}

	public Customer(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [id=").append(id).append(", name=").append(name).append(", buys=").append(buys)
				.append("]");
		return builder.toString();
	}

	public Customer() {
	}

}
