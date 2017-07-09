package com.tgweb.hibernate.day03;

public class Student extends Person {

	private String school;

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [school=").append(school).append(", getId()=").append(getId()).append(", getName()=")
				.append(getName()).append(", getAge()=").append(getAge()).append("]");
		return builder.toString();
	}

	public Student(String name, Integer age, String school) {
		super(name, age);
		this.school = school;
	}

	public Student() {
	}
}
