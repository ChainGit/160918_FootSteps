package com.tgweb.day10;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter extends HttpFilter {

	private String charset;

	@Override
	public void init() {
		super.init();
		charset = getFilterConfig().getServletContext().getInitParameter("charset");
		if (charset == null || charset.length() < 1)
			charset = "UTF-8";
	}

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("EncodingFilter doFilter");

		String reqEncoding = request.getCharacterEncoding();
		if (reqEncoding == null || !reqEncoding.equals(charset))
			request.setCharacterEncoding(charset);

		chain.doFilter(request, response);
	}

}
