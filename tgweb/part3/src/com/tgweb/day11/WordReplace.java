package com.tgweb.day11;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class WordReplace extends HttpServletRequestWrapper {

	private List<String> keyWord;

	public WordReplace(HttpServletRequest request, List<String> keyWord) {
		super(request);
		this.keyWord = keyWord;
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		String val = super.getParameter(name);
		if (val != null)
			for (String s : keyWord) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < s.length(); i++)
					sb.append('*');
				val = val.replaceAll(s, sb.toString());
			}
		return val;
	}

}
