package com.bxd.day01;

import java.util.ArrayList;

public class ChildClass extends SuperClass {

	private static final int num = 0x0000_000E;

	static {
		final int a = 10;
		System.out.println("This is ChildClass static code block 1 and a is " + a);
	}

	{
		System.out.println("This is ChildClass code block 2");
	}

	public ChildClass() {
		this(0x0000_000F);
		System.out.println("This is ChildClass Constructor and num is " + num);
		this.testFinalArrayList();
	}

	private ChildClass(int n) {
		System.out.println("This is ChildClass Constructor with n and num is " + n);
		this.normalFun();
	}

	public static void staticFun() {
		System.out.println("This is ChildClass staticFun");
		new SuperClass().normalFun();
	}

	@Override
	public void normalFun() {
		System.out.println("This is ChildClass normalFun");
		// finalFun();
	}

	public void testFinalArrayList() {
		final ArrayList<Integer> array = new ArrayList<>();
		// 引用是常量不变的，但是容器里面的值是可以改变的
		array.add(1);
		array.add(2);
		System.out.print("The Arraylist is ");
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();

		// The final local variable array cannot be assigned. It must be blank
		// and not using a compound assignment
		// array=new ArrayList<Integer>();
	}

	// Cannot override the final method from SuperClass
	// public final void finalFun(){
	// 		System.out.println("This is SuperClass finalFun");
	// }

	static {
		System.out.println("This is ChildClass static code block 2");
	}

	{
		System.out.println("This is ChildClass code block 2");
	}

}
