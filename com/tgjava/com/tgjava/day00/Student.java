package com.tgjava.day00;

public class Student extends Person {
	public int id;

	public void showStudent() {
		System.out.println("student");
	}

	public void showMsg(String msg, Integer i) {
		System.out.println(msg + "---" + i);
	}
}