package com.tgweb.day08;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class UpperWordSimpleTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		super.doTag();
		JspFragment jspContext = getJspBody();
		StringWriter sw = new StringWriter();
		jspContext.invoke(sw);
		getJspContext().getOut().println(sw.toString().toUpperCase());
	}
}
