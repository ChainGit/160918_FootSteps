package com.tgweb.day09;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class WhenSimpleTag extends SimpleTagSupport {

	private String test;

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		super.doTag();
		ChooseSimpleTag parentTag = (ChooseSimpleTag) getParent();

		if (!parentTag.isFlag())
			return;

		if (test.equals("true")) {
			getJspBody().invoke(null);
			parentTag.setFlag(false);
		}
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
}
