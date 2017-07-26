package com.tgweb.struts2.day04;

public class WildCardMatchActionContextTestAction {

	public String login() {
		System.out.println("login");
		return "login-success";
	}

	public String login2() {
		System.out.println("login2");
		return "login2-success";
	}

	public String exit() {
		System.out.println("exit");
		return "exit-success";
	}

	public String update() {
		System.out.println("update");
		return "update-success";
	}

	public String test() {
		System.out.println("test");
		return "test-success";
	}

}
