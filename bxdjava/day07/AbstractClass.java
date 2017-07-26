package com.bxd.day07;

public abstract class AbstractClass extends A3 {
	public abstract void show();
}

abstract class A1 extends A4 {
	public static final int NUM = 12;

	public abstract void show();

}

abstract class A2 extends A1 {
	public static final int NUM = 15;

	public abstract void show();

	public abstract void study();

}

abstract class A3 extends A2 {
	public static final int NUM = 14;

	public abstract void show();

	public abstract void work();

	public void doPrint() {
		System.out.println("PPP");
	}

}

abstract class A4 {

	
	public abstract void print();
	public abstract void show();
}
