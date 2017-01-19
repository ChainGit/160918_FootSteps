package com.bxd.day18;

public class RuntimeTest {

	public static void main(String[] args) throws Exception {
		Runtime r = Runtime.getRuntime();
		r.exec("calc");
	}
}
