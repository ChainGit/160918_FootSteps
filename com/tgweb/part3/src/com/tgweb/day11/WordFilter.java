package com.tgweb.day11;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgweb.day10.HttpFilter;

public class WordFilter extends HttpFilter {

	private List<String> keyWord;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		String initKeyWord = getFilterConfig().getServletContext().getInitParameter("keyWord");
		if (initKeyWord == null || initKeyWord.length() < 1)
			initKeyWord = "fuck";

		keyWord = Arrays.asList(initKeyWord.split(";"));
	}

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		WordReplace wr = new WordReplace(request, keyWord);
		chain.doFilter(wr, response);
	}

}
