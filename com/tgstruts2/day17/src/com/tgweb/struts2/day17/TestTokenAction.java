package com.tgweb.struts2.day17;

import com.opensymphony.xwork2.ActionSupport;

public class TestTokenAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private static int count = 0;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String token() {
		return SUCCESS;
	}

	public String success() throws Exception {
		Thread.sleep(2000);
		System.out.println(username + "," + (count++));
		return super.execute();
	}

}
