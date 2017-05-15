package com.tgweb.struts2.day02;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class ActionContextTestAction {

	public String execute() {
		// ActionContext 基于 ThreadLocal
		ActionContext ac = ActionContext.getContext();

		// Application对象
		Map<String, Object> amap = ac.getApplication();
		amap.put("appKey", "appValue");

		// Session对象
		Map<String, Object> smap = ac.getSession();
		smap.put("sessKey", "sessValue");

		// Request对象
		@SuppressWarnings("unchecked")
		Map<String, Object> rmap = (Map<String, Object>) ac.get("request");
		System.out.println(rmap.get("address"));
		rmap.put("reqKey", "reqValue");

		// 地址栏Parameter参数
		// Parameters的修改只对当前的进程有效，基于ThreadLocal,也就是说Parameters只读，假修改
		Map<String, Object> param = ac.getParameters();
		for (Map.Entry<String, Object> pe : param.entrySet())
			System.out.println(pe.getKey() + " : " + pe.getValue());
		System.out.println(((String[]) param.get("name"))[0]);
		System.out.println(((String[]) param.get("age"))[0]);
		// System.out.println(((String[]) param.get("address"))[0]);
		param.put("name", new String[] { "ABC" });
		param.put("age", new String[] { "100" });
		System.out.println(((String[]) param.get("name"))[0]);
		System.out.println(((String[]) param.get("age"))[0]);

		param.put("myparam", new String[] { "myvalue" });
		System.out.println(((String[]) param.get("myparam"))[0]);

		return "success";
	}
}
