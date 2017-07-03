package com.tgweb.spring4.day03;

public class Car {

	private String name;
	private Integer price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Car(String name, Integer price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Car() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [name=").append(name).append(", price=").append(price).append("]");
		return builder.toString();
	}

}
