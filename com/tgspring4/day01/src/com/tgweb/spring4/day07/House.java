package com.tgweb.spring4.day07;

public class House {

	private String address;
	private Integer price;

	public House(String address, Integer price) {
		super();
		this.address = address;
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("House [address=").append(address).append(", price=").append(price).append("]");
		return builder.toString();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public House() {
	}
}
