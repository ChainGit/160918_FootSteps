package com.tgweb.hibernate.day03;

import java.util.HashSet;
import java.util.Set;

public class Category {

	private Long id;
	private String name;
	private Set<Item> items;

	{
		items = new HashSet<>();
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

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [id=").append(id).append(", name=").append(name).append("]");
		return builder.toString();
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public Category() {
	}

}
