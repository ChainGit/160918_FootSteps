package com.tgweb.spring4.day08;

public class Car {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("Car's setName(): " + name);
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [name=").append(name).append("]");
		return builder.toString();
	}

	public Car(String name) {
		super();
		this.name = name;
	}

	public Car() {
		System.out.println("Car's Construtor..");
	}

	public void init() {
		System.out.println("Car's init()..");
	}

	public void destory() {
		System.out.println("Car's destory()..");
	}

}
