package com.tgweb.day08;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MaxSimpleTag extends SimpleTagSupport {

	private String num1;
	private String num2;

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		super.doTag();
		JspWriter jw = getJspContext().getOut();
		try {
			int n1 = Integer.parseInt(num1);
			int n2 = Integer.parseInt(num2);
			int max = n1 > n2 ? n1 : n2;
			jw.println(max);
		} catch (Exception e) {
			jw.println("err");
		}
		jw.println("<br/>");
	}

	public String getNum1() {
		return num1;
	}

	public void setNum1(String num1) {
		this.num1 = num1;
	}

	public String getNum2() {
		return num2;
	}

	public void setNum2(String num2) {
		this.num2 = num2;
	}

}
