package com.tgweb.struts2.day18;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class HelloInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		System.out.println("before HelloInterceptor");

		// AOP���˼��,����������
		// ��������½��֤�ȿ��Բ���������invocation.invoke()
		String resultCode = invocation.invoke();

		System.out.println("after HelloInterceptor");

		return resultCode;
	}

}
