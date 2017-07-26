package com.tgweb.day08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ReadFileSimpleTag extends SimpleTagSupport {

	private String path;
	private String charset = "utf-8";

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		super.doTag();
		JspWriter jw = getJspContext().getOut();
		try {
			BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(path), charset));
			char[] buf = new char[1024];
			while ((bufr.read(buf)) != -1) {
				String b0 = new String(buf);
				String b1 = b0.replaceAll("<", "&lt;");
				String b2 = b1.replaceAll(">", "&gt;");
				jw.write(b2);
			}
			bufr.close();
		} catch (Exception e) {
			jw.println("ERR!");
		}
		jw.println("<br>");
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

}
