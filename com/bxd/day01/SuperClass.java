package com.bxd.day01;

public class SuperClass {

	private static final int num = 0x0000_000A;

	static {
		//Illegal modifier for the variable staticInt; only final is permitted
		// static final int a= 10;
		final int a = 10;
		System.out.println("This is SuperClass static code block 1 and a is "+a);
	}

	{
		System.out.println("This is SuperClass code block 2");
	}

	public SuperClass() {
		this(0x0000_000B);
		System.out.println("This is SuperClass Constructor and num is " + num);
	}
	
	private SuperClass(int n){
		System.out.println("This is SuperClass Constructor with n and num is " + n);
		this.normalFun();
		//错误：无限调用new，死循环
		//this.staticFun();
	}

	public static void staticFun(){
		System.out.println("This is SuperClass staticFun");
		new SuperClass().normalFun();
	}
	
	public void normalFun(){
		System.out.println("This is SuperClass normalFun");
		finalFun();
	}
	
	public final void finalFun(){
		System.out.println("This is SuperClass finalFun");
	}

	static {
		System.out.println("This is SuperClass static code block 2");
	}

	{
		System.out.println("This is SuperClass code block 2");
	}

}
