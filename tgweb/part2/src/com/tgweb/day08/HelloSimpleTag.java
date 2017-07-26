package com.tgweb.day08;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

public class HelloSimpleTag implements SimpleTag {

	private JspContext pageContext;

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doTag");
		JspWriter pw = pageContext.getOut();
		pw.println("hello");
		pw.println("<br/>");
		// pw.close();
	}

	@Override
	public void setParent(JspTag parent) {
		// TODO Auto-generated method stub
		System.out.println("setParent");
	}

	@Override
	public JspTag getParent() {
		// TODO Auto-generated method stub
		System.out.println("getParent");
		return null;
	}

	@Override
	public void setJspContext(JspContext pc) {
		// TODO Auto-generated method stub
		System.out.println("setJspContext");
		this.pageContext = pc;
	}

	@Override
	public void setJspBody(JspFragment jspBody) {
		// TODO Auto-generated method stub
		System.out.println("setJspBody");
	}

}
