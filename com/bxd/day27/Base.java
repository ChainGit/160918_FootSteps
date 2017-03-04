package com.bxd.day27;

/**
 * ”√”⁄∑¥…‰
 * 
 * @author Chain
 *
 */
public class Base {

	private int age;
	private static String name;
	public int id;

	public Base() {
		System.out.println("Constructor");
	}

	public Base(String name, int age) {
		Base.name = name;
		this.age = age;
		System.out.println("Constructor args");
	}

	public int getAge() {
		System.out.println("getAge");
		return age;
	}

	public void setAge(int age) {
		System.out.println("setAge");
		this.age = age;
	}

	public String getName() {
		System.out.println("getName");
		return name;
	}

	public void setName(String name) {
		System.out.println("setName");
		Base.name = name;
	}

	@SuppressWarnings("unused")
	private void getPerson() {
		System.out.println("getPerson: " + name + " " + age);
	}

	public static void getPersonStatic() {
		System.out.println("getPersonStatic: " + name);
	}

}
