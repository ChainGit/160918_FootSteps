package com.tgweb.hibernate.day04;

import java.util.HashSet;
import java.util.Set;

public class Item {

	private Long id;
	private String name;

	private Set<Category> categories;

	{
		categories = new HashSet<>();
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

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [id=").append(id).append(", name=").append(name).append(", categories=").append(categories)
				.append("]");
		return builder.toString();
	}

	public Item(String name) {
		super();
		this.name = name;
	}

	public Item() {
	}

}
