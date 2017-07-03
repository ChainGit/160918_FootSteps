package com.tgweb.spring4.day03;

import java.util.List;

public class Person {

	private String name;
	private Integer age;
	private House house;
	private List<Car> cars;

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

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [name=").append(name).append(", age=").append(age).append(", house=").append(house)
				.append(", cars=").append(cars).append("]");
		return builder.toString();
	}

	public Person(String name, Integer age, House house, List<Car> cars) {
		super();
		this.name = name;
		this.age = age;
		this.house = house;
		this.cars = cars;
	}

	public Person() {
	}
}
