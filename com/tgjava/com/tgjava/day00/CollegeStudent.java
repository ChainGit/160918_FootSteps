package com.tgjava.day00;

public class CollegeStudent extends Student {
	public void showCollegeStudent() {
		System.out.println("college student");
	}

	@Override
	public void showData() {
		System.out.println("data in college student");
	}
}
