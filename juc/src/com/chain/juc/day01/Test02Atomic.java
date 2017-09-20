package com.chain.juc.day01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * i++的原子性，三步操作
 * 
 * <br>
 * 
 * 1、java.util.concurrent.atomic 2、CAS
 * 
 * CAS是得到硬件上支持的，CPU直接支持
 * 
 * CAS 包含了三个操作数： ①内存值 V ②预估值 A ③更新值 B 当且仅当 V == A 时， V = B; 否则，不会执行任何操作。
 * 
 * 源码中的compareAndSet实际是调用了unsafe的compareAndSwapInt方法
 * 
 * @author Chain
 *
 */
public class Test02Atomic {

	public static void main(String[] args) {
		test1();
		// test2();
	}

	public static void test2() {
		MyRunnableImpl02B runnable = new MyRunnableImpl02B();

		for (int i = 0; i < 100; i++) {
			new Thread(runnable).start();
		}

	}

	public static void test1() {
		MyRunnableImpl02A runnable = new MyRunnableImpl02A();

		for (int i = 0; i < 100; i++) {
			new Thread(runnable).start();
		}

	}
}

class MyRunnableImpl02B implements Runnable {

	// 使用原子变量，确保多个进程同时访问时的原子性
	private AtomicInteger num = new AtomicInteger();

	@Override
	public void run() {
		// 如果使用println的话，可能会刷新线程中的数据到主内存中
		// System.out.println(getAndIncrementNumber());
		// i++分三步操作
		// int temp = i;
		// i = i + 1;
		// i = temp;
		// 这样的三步操作可能会在多线程里被分开执行
		int value = getAndIncrementNumber();
		System.out.println(value);
	}

	private int getAndIncrementNumber() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}

		// CAS操作
		return num.getAndIncrement();
	}

	public int getNum() {
		return num.intValue();
	}

}

class MyRunnableImpl02A implements Runnable {

	private int num;

	@Override
	public void run() {
		// 如果使用println的话，可能会刷新线程中的数据到主内存中
		// System.out.println(getAndIncrementNumber());
		// i++分三步操作
		// int temp = i;
		// i = i + 1;
		// i = temp;
		// 这样的三步操作可能会在多线程里被分开执行
		int value = getAndIncrementNumber();
		System.out.println(value);
	}

	private int getAndIncrementNumber() {
		// return num++;

		int temp = num;
		num = num + 1;

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}

		return temp;
	}

	public int getNum() {
		return num;
	}

}
