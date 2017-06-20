package com.tgweb.struts2.day13;

import com.opensymphony.xwork2.ActionSupport;

public class TestValidationAcion extends ActionSupport {

	private static final long serialVersionUID = -2220292480523662926L;

	private Integer age;
	private Integer weight;
	private Integer height;

	private String password;
	private String password2;

	private String idcard;

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

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

	public String show() {
		System.out.println(age);
		System.out.println(weight);
		System.out.println(height);
		System.out.println(password);
		System.out.println(password2);
		return "show-success";
	}

}
