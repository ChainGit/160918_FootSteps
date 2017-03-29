package com.tgweb.day01;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class HelloServlet implements Servlet {

	private ServletConfig servletConfig;

	public HelloServlet() {
		super();
		System.out.println("constructor");
	}

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public ServletConfig getServletConfig() {
		System.out.println("config");
		return null;
	}

	@Override
	public String getServletInfo() {
		System.out.println("info");
		return null;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		System.out.println("init");

		System.out.println(servletConfig.getServletName());

		Enumeration<String> em = servletConfig.getInitParameterNames();
		while (em.hasMoreElements()) {
			String key = em.nextElement();
			System.out.println(key + "[" + servletConfig.getInitParameter(key) + "]");
		}

		ServletContext sc = servletConfig.getServletContext();
		Enumeration<String> em2 = sc.getInitParameterNames();
		while (em2.hasMoreElements()) {
			String key = em2.nextElement();
			System.out.println(key + "[" + sc.getInitParameter(key) + "]");
		}

		System.out.println(sc.getRealPath("./index.jsp"));
		System.out.println(sc.getContextPath());

		InputStream isa = this.getClass().getClassLoader().getResourceAsStream("mysql.properties");
		System.out.println(isa);

		InputStream isb = sc.getResourceAsStream("/WEB-INF/classes/mysql.properties");
		// InputStream isb =
		// sc.getResourceAsStream("WEB-INF/classes/mysql.properties");
		System.out.println(isb);

		this.servletConfig = servletConfig;

	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("service");

		request.setCharacterEncoding("UTF-8");

		System.out.println("########");
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String ints = request.getParameter("interesting");
		System.out.println(user + "[" + pass + "]" + "{" + ints + "}");

		System.out.println("$$$$$$$$");
		String[] params = request.getParameterValues("interesting");
		for (String s : params)
			System.out.println(s);

		System.out.println("%%%%%%%%");
		Enumeration<String> em = request.getParameterNames();
		while (em.hasMoreElements()) {
			String key = em.nextElement();
			String[] val = request.getParameterValues(key);
			System.out.println(key + ":" + Arrays.asList(val));
		}

		System.out.println("@@@@@@@@");
		Map<String, String[]> map = request.getParameterMap();
		for (Map.Entry<String, String[]> v : map.entrySet()) {
			String key = v.getKey();
			List<String> val = Arrays.asList(v.getValue());
			System.out.println(key + ":" + val);
		}

		HttpServletRequest hsr = (HttpServletRequest) request;
		System.out.println(hsr.getMethod());
		System.out.println(hsr.getRequestURL());
		System.out.println(hsr.getQueryString());
		System.out.println(hsr.getProtocol());
		System.out.println(hsr.getCharacterEncoding());
		System.out.println(hsr.getContextPath());
		System.out.println(hsr.getRemoteHost());
		System.out.println(hsr.getRemotePort());
		System.out.println(hsr.getRemoteAddr());

		// OutputStream os = response.getOutputStream();
		// os.write(new String("helloworld..").getBytes());
		// os.close();

		// response.setContentType("application/ms-word");
		PrintWriter pw = response.getWriter();
		// pw.write("helloworld2...");
		String usercfg = servletConfig.getInitParameter("user");
		String passcfg = servletConfig.getInitParameter("pass");
		if (user.equals(usercfg) && pass.equals(passcfg))
			pw.write("hello");
		else
			pw.write("sorry");
		pw.close();
	}

}
