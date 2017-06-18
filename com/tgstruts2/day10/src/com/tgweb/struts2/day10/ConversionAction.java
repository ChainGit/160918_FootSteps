package com.tgweb.struts2.day10;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class ConversionAction extends ActionSupport implements RequestAware {

	private static final long serialVersionUID = 3448815714069719777L;

	private Integer age;
	private Map<String, Object> requestMap;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String execute() {
		return "success";
	}

	public String conversion() {
		return "conversion-success";
	}

	public String show() {
		System.out.println("age: " + age);
		requestMap.put("xyz", age);
		return "show-success";
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.requestMap = arg0;
	}

}
