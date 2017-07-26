package com.bxd.day05;

public class HelloWorldDemo {

	// main也是函数，所以当然也支持重载，但是虚拟机JVM只认以下格式，从这个函数入口进入
	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	//
	// }

	static int a;
	static String s;
	static float f;
	static boolean b;

	int a1;
	String s1;
	float f1;
	boolean b1;

	static HelloWorldDemo h;
	HelloWorldDemo h1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello java 1");
		main(new String("Hello"), 0);
		String a[] = { "asas", "sd", "sc2", "asx" };
		HelloWorldDemo2.main(a);
	}

	public static void main(String args, int x) {
		// TODO Auto-generated method stub
		System.out.println("hello java 2");
		main();
	}

	public static void main() {
		// TODO Auto-generated method stub
		System.out.println("hello java 3");
		new HelloWorldDemo().fun();
	}

	public void fun() {
		System.out.println("hello java 4");
		printStatic();
		printNormal();
		printParted();
	}

	public void printStatic() {
		System.out.println(a + " " + s + " " + f + " " + b + " " + h);
	}

	public void printNormal() {
		System.out.println(a1 + " " + s1 + " " + f1 + " " + b1 + " " + h1);
	}

	public void printParted() {
		// The local variable a1 may not have been initialized
		int a1 = 0;
		String s1 = null;
		float f1 = 0;
		boolean b1 = false;
		HelloWorldDemo h1 = null;
		System.out.println(a1 + " " + s1 + " " + f1 + " " + b1 + " " + h1);
	}

}

class HelloWorldDemo2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (String s : args) {
			System.out.println(s);
		}
		
		String str[]=new String[0];
		for (String s :str) {
			System.out.println(s);
		}
		System.out.println(str);
		System.out.println("OVER");
	}
}
