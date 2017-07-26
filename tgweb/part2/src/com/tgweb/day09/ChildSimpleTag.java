package com.tgweb.day09;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ChildSimpleTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		super.doTag();
		JspTag parent = getParent();
		ParentSimpleTag parentTag = (ParentSimpleTag) parent;
		String name = parentTag.getName();
		System.out.println("ChildTag: " + name);
		getJspContext().getOut().println("ChildTag:" + name + "<br/>");
	}

}
