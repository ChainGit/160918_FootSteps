package com.tgweb.day02;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class MyGenericServlet implements Servlet, ServletConfig {

	private ServletConfig servletConfig;

	// Servlet
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
		this.servletConfig = config;
		this.init();
	}

	// please override this method
	public void init() {

	}

	@Override
	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	@Override
	public abstract void service(ServletRequest req, ServletResponse res) throws ServletException, IOException;

	@Override
	public String getServletInfo() {
		return servletConfig.getServletContext().getServerInfo();
	}

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	// ServletConfig
	@Override
	public String getServletName() {
		return servletConfig.getServletName();
	}

	@Override
	public ServletContext getServletContext() {
		return servletConfig.getServletContext();
	}

	@Override
	public String getInitParameter(String name) {
		return servletConfig.getInitParameter(name);
	}

	@Override
	public Enumeration getInitParameterNames() {
		return servletConfig.getInitParameterNames();
	}

}
