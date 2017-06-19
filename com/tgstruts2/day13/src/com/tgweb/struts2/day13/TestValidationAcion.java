package com.tgweb.struts2.day13;

import com.opensymphony.xwork2.ActionSupport;

public class TestValidationAcion extends ActionSupport {

	private static final long serialVersionUID = -2220292480523662926L;

	private Integer age;
	private Integer weight;

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String vali() {
		return "vali-success";
	}

	public String vali2() {
		return "vali2-success";
	}

	public String show() {
		System.out.println(age);
		System.out.println(weight);
		return "show-success";
	}

}
