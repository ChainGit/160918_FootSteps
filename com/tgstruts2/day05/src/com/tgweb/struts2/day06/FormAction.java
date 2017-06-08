package com.tgweb.struts2.day06;

import java.util.List;

public class FormAction {

	private int id;
	private String name;
	private String pass;
	private boolean married;
	private int gender;
	private List<String> cities;
	private int age;

	public String execute() {
		System.out.println(this);

		/*
		 * FormAction fa = new FormAction(); fa.setName("ABC"); fa.setId(10001);
		 * fa.setPass("1234");
		 * ActionContext.getContext().getValueStack().push(fa);
		 */
		return "success";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FormAction [id=").append(id).append(", name=").append(name).append(", pass=").append(pass)
				.append(", married=").append(married).append(", gender=").append(gender).append(", cities=")
				.append(cities).append(", age=").append(age).append("]");
		return builder.toString();
	}

}
