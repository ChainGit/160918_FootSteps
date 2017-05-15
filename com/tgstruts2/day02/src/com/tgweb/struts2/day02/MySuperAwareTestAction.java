package com.tgweb.struts2.day02;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

//Awareģʽ�ĺô����ڿ���֧�����ö��actionMethod������Ҫ�ظ���ȡrequest,session,application,parameters
//Struts2�����ĸ�����ͳһתΪMap������д���,���ʽ����
//ע�뷽ʽ
public class MySuperAwareTestAction implements RequestAware, SessionAware, ApplicationAware, ParameterAware {

	private Map<String, String[]> parameters;
	private Map<String, Object> application;
	private Map<String, Object> session;
	private Map<String, Object> request;

	public String execute() {
		System.out.println("execute");
		System.out.println(parameters.get("name")[0]);
		request.put("address", "guangzhou");
		return "success";
	}

	public String test() {
		System.out.println("save");
		System.out.println(parameters.get("name")[0]);
		return "success";
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		// TODO Auto-generated method stub
		this.parameters = parameters;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		// TODO Auto-generated method stub
		this.application = application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

}
