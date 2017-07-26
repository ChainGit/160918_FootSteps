package com.tgweb.spring4.day01;

public class Person {

	private String name;
	private int age;
	private Integer math;
	private Car car;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("setName:" + name);
		this.name = name;
	}

	public void show() {
		System.out.println(this);
	}

	public Person() {
		System.out.println("Person Construtor");
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Person(String name, Integer math) {
		super();
		this.name = name;
		this.math = math;
	}

	public Person(String name, Car car) {
		super();
		this.name = name;
		this.car = car;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [name=").append(name).append(", age=").append(age).append(", math=").append(math)
				.append(", car=").append(car).append("]");
		return builder.toString();
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
