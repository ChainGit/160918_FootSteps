package com.tgweb.struts2.day05;

public class Product2 {

	private String name;
	private long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product2 [name=").append(name).append(", id=").append(id).append("]");
		return builder.toString();
	}

}
