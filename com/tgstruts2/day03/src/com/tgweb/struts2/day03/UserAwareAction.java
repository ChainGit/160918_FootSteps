package com.tgweb.struts2.day03;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class UserAwareAction implements ApplicationAware, SessionAware, RequestAware, ParameterAware {

	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	private Map<String, String[]> parameters;

	public String checkLogin() {
		System.out.println("checkLogin");

		User user = (User) session.get("login_user");
		if (user == null)
			return "no-login";
		else
			return "in-login";
	}

	public String loginIn() {
		System.out.println("loginIn");

		String userName = parameters.get("username")[0];
		if (userName == null || userName.length() < 1)
			return "login-in-error";

		User user = (User) session.get("login_user");
		if (user == null) {
			user = new User();
			user.setUserName(userName);
			session.put("login_user", user);
		} else
			return "login-in-already";

		Integer count = (Integer) application.get("login_users_count");
		if (count == null || count < 0)
			count = 0;

		count++;

		application.put("login_users_count", count);

		return "login-in-success";
	}

	public String loginOut() {
		System.out.println("loginOut");

		((SessionMap<String, Object>) session).invalidate();

		boolean loginOutStatus = true;
		Integer count = (Integer) application.get("login_users_count");
		if (count == null || count < 1) {
			count = 0;
			loginOutStatus = false;
		} else {
			count--;
		}

		application.put("login_users_count", count);

		return loginOutStatus ? "login-out-success" : "login-out-error";
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		// TODO Auto-generated method stub
		this.application = application;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		// TODO Auto-generated method stub
		this.parameters = parameters;
	}

}
