package com.tgweb.struts2.day02;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class ServletActionContextTestAction {

	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		ServletContext application = ServletActionContext.getServletContext();

		System.out.println("test ServletActionContextTestAction");

		return "success";
	}

}
