package com.tgweb.struts2.day05;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class Product implements SessionAware {

	private String name;
	private double price;
	private String desc;

	public String save() {

		Product2 p2 = new Product2();
		p2.setName("yes");
		p2.setId(100001);

		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.push(p2);

		sessionMap.put("test", p2);

		// int a = 10 / 0;

		return "success";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [name=").append(name).append(", price=").append(price).append(", desc=").append(desc)
				.append("]");
		return builder.toString();
	}

	private Map<String, Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}

}
