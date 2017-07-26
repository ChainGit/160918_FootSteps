package com.tgweb.struts2.day11;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ComplexPropertiesAction extends ActionSupport implements ModelDriven<Department> {

	private static final long serialVersionUID = 7364384970443452455L;
	private Department dept;

	@Override
	public Department getModel() {
		dept = new Department();
		return dept;
	}

	public String input() {
		return "input-success";
	}

	public String show() {
		System.out.println(dept);
		return "show-success";
	}

}
