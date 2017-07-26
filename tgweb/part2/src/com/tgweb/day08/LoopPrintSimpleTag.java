package com.tgweb.day08;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

public class LoopPrintSimpleTag implements SimpleTag {

	private JspContext pageContext;

	private String count = "1";
	private String value;

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		System.out.println(value + " " + count);
		JspWriter jw = pageContext.getOut();
		int times = Integer.parseInt(count);
		for (int i = 0; i < times; i++)
			jw.println((i + 1) + ":" + value + "<br/>");
	}

	@Override
	public void setParent(JspTag parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public JspTag getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setJspContext(JspContext pc) {
		// TODO Auto-generated method stub
		this.pageContext = pc;
	}

	@Override
	public void setJspBody(JspFragment jspBody) {
		// TODO Auto-generated method stub

	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
