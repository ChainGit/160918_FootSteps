package com.tgweb.struts2.day02;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class ActionContextTestAction {

	public String execute() {
		// ActionContext ���� ThreadLocal
		ActionContext ac = ActionContext.getContext();

		// Application����
		Map<String, Object> amap = ac.getApplication();
		amap.put("appKey", "appValue");

		// Session����
		Map<String, Object> smap = ac.getSession();
		smap.put("sessKey", "sessValue");

		// Request����
		@SuppressWarnings("unchecked")
		Map<String, Object> rmap = (Map<String, Object>) ac.get("request");
		System.out.println(rmap.get("address"));
		rmap.put("reqKey", "reqValue");

		// ��ַ��Parameter����
		// Parameters���޸�ֻ�Ե�ǰ�Ľ�����Ч������ThreadLocal,Ҳ����˵Parametersֻ�������޸�
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
