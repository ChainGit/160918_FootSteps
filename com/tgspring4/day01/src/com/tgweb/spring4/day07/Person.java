package com.tgweb.spring4.day07;

public class Person {

	private String name;
	private Integer age;

	private Car car;
	private House house;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	// House里的Address
	private String address;

	// Car里的info
	private String info;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [name=").append(name).append(", age=").append(age).append(", car=").append(car)
				.append(", house=").append(house).append(", address=").append(address).append(", info=").append(info)
				.append("]");
		return builder.toString();
	}

	public Person() {
	}

}
