package com.tgweb.spring4.day01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticCalculatorLogProxy {

	private ArithmeticCalculator target;

	public ArithmeticCalculatorLogProxy(ArithmeticCalculator target) {
		this.target = target;
	}

	public ArithmeticCalculator getArithmeticCalculatorLogProxy() {
		ArithmeticCalculator proxy = null;

		ClassLoader loader = target.getClass().getClassLoader();
		Class[] interfaces = new Class[] { ArithmeticCalculator.class };
		InvocationHandler h = new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				String methodName = method.getName();

				System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));

				Object result = method.invoke(target, args);

				System.out.println("The method " + methodName + " ends with result: " + result);
				return result;
			}
		};

		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);

		return proxy;
	}
}
