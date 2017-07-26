package com.tgweb.struts2.day06;

public class City {

	private long id;
	private String name;

	public City() {
	}

	public City(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
		builder.append("City [id=").append(id).append(", name=").append(name).append("]");
		return builder.toString();
	}

}
