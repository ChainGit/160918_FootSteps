package com.tgweb.struts2.day02;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

public class MySuperServletAwareTestAction implements ServletRequestAware, ServletResponseAware, ServletContextAware {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext application;
	private HttpSession session;

	public String execute() {

		System.out.println("test MySuperServletAwareTestAction");

		return "success";
	}

	@Override
	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub
		this.application = context;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		this.session = request.getSession();
	}

}
