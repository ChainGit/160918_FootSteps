package com.tgweb.spring4.day07;

public class Car {

	private String name;
	private Integer price;
	private String info;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

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

	public Car(String name, Integer price, String info) {
		super();
		this.name = name;
		this.price = price;
		this.info = info;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [name=").append(name).append(", price=").append(price).append(", info=").append(info)
				.append("]");
		return builder.toString();
	}

	public Car() {
	}

}
