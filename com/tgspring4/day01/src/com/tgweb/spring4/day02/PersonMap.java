package com.tgweb.spring4.day02;

import java.util.Map;

import com.tgweb.spring4.day01.Car;

public class PersonMap {

	private String name;
	private Map<String, Car> cars;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Car> getCars() {
		return cars;
	}

	public void setCars(Map<String, Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonMap [name=").append(name).append(", cars=").append(cars).append("]");
		return builder.toString();
	}

	public PersonMap(String name, Map<String, Car> cars) {
		super();
		this.name = name;
		this.cars = cars;
	}

	public PersonMap() {
	}

	public void show() {
		System.out.println(this);
	}

}
