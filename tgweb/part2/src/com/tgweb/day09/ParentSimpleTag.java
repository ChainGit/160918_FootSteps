package com.tgweb.day09;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ParentSimpleTag extends SimpleTagSupport {

	private String name = "name";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		super.doTag();
		System.out.println("ParentTag: " + name);
		JspWriter pw = getJspContext().getOut();
		pw.println("ParentTag: " + name);
		pw.println("<br/>");
		getJspBody().invoke(pw);
	}

}
