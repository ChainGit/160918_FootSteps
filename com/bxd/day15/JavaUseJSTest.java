package com.bxd.day15;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaUseJSTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScriptEngineManager scriptEngineMgr = new ScriptEngineManager();
		ScriptEngine jsEngine = scriptEngineMgr.getEngineByName("JavaScript");
		StringBuffer sb = new StringBuffer();
		// 组装js代码，也可以从文件读入脚本到sb变量
		// jsEngine.put("myContext", myContext);// 放置其他参数
		try {
			String name = "test";
			sb.append("function say(){ return 'hello," + name + "'; }");
			jsEngine.eval(sb.toString());
			Invocable inv2 = (Invocable) jsEngine;
			String res = (String) inv2.invokeFunction("say", name);
			System.out.println(res);
		} catch (ScriptException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 解析、执行脚本
	}

}
