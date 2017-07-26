package com.tgweb.spring4.day05;

public class Person {

	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [name=").append(name).append(", age=").append(age).append("]");
		return builder.toString();
	}

	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Person() {
		System.out.println("Person's Construtor..");
	}

}
