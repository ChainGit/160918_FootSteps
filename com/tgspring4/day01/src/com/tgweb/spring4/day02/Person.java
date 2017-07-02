package com.tgweb.spring4.day02;

import java.util.List;

import com.tgweb.spring4.day01.Car;

public class Person {

	private String name;
	private List<Car> cars;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void show() {
		System.out.println(this);
	}

	public Person() {
	}

	public Person(String name, List<Car> cars) {
		super();
		this.name = name;
		this.cars = cars;
	}

	public List<Car> getCars() {
		return cars;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [name=").append(name).append(", cars=").append(cars).append("]");
		return builder.toString();
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

}
