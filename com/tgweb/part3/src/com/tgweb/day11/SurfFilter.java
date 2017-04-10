package com.tgweb.day11;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgweb.day10.HttpFilter;

public class SurfFilter extends HttpFilter {

	private String redirectUrl;
	private String uncheckedUrls;
	private List<String> uncheckedUrlsLst;
	private String contextPath;

	@Override
	public void init() {
		this.contextPath = getFilterConfig().getServletContext().getContextPath();
		this.redirectUrl = getFilterConfig().getInitParameter("redirectUrl");
		this.uncheckedUrls = getFilterConfig().getInitParameter("uncheckedUrls");

		if (redirectUrl == null || redirectUrl.length() < 1)
			redirectUrl = "/filter2/index.html";

		if (uncheckedUrls == null || uncheckedUrls.length() < 1)
			uncheckedUrls = "/filter2/index.html";

		uncheckedUrlsLst = Arrays.asList(uncheckedUrls.split(";"));
	}

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String servletPath = request.getServletPath();
		System.out.println(contextPath + servletPath);

		String loginStatus = (String) request.getSession().getAttribute("LOGINSTATUS");

		if (loginStatus != null && loginStatus.equals("TRUE"))
			chain.doFilter(request, response);
		else if (uncheckedUrlsLst.contains(servletPath))
			chain.doFilter(request, response);
		else
			response.sendRedirect(contextPath + redirectUrl);
	}

}
