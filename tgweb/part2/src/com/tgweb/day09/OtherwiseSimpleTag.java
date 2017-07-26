package com.tgweb.day09;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class OtherwiseSimpleTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		super.doTag();
		ChooseSimpleTag parentTag = (ChooseSimpleTag) getParent();

		if (!parentTag.isFlag())
			return;

		getJspBody().invoke(null);
		parentTag.setFlag(false);
	}

}
