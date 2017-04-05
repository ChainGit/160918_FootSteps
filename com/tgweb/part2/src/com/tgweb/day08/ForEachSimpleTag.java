package com.tgweb.day08;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ForEachSimpleTag extends SimpleTagSupport {

	private Collection<?> items;
	private String var;

	public void setItems(Collection<?> col) {
		this.items = col;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspContext pageContext = getJspContext();
		JspWriter jw = pageContext.getOut();
		try {
			JspFragment jspContext = getJspBody();
			for (Object obj : items) {
				pageContext.setAttribute(var, obj);
				jspContext.invoke(jw);
				jw.println("<br/>");
			}
		} catch (Exception e) {
			jw.println("ERROR!");
		}
		jw.println("<br>");
	}
}
