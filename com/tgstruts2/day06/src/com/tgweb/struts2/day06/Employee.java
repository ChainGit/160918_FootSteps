package com.tgweb.struts2.day06;

import java.util.Arrays;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

public class Employee implements RequestAware {

	private Map<String, Object> requestMap;

	private Dao dao = new Dao();

	private String name;
	private String password;
	private Integer gender;
	private Integer dept;
	private Integer[] roles;
	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getDept() {
		return dept;
	}

	public void setDept(Integer dept) {
		this.dept = dept;
	}

	public Integer[] getRoles() {
		return roles;
	}

	public void setRoles(Integer[] roles) {
		this.roles = roles;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String input() {
		requestMap.put("roles", dao.getRoles());
		requestMap.put("depts", dao.getDepartments());
		return "input-success";
	}

	public String result() {
		System.out.println(this);
		return "result-success";
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.requestMap = arg0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [requestMap=").append(requestMap).append(", dao=").append(dao).append(", name=")
				.append(name).append(", password=").append(password).append(", gender=").append(gender)
				.append(", dept=").append(dept).append(", roles=").append(Arrays.toString(roles)).append(", desc=")
				.append(desc).append("]");
		return builder.toString();
	}

}
