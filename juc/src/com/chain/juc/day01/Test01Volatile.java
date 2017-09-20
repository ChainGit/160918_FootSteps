package com.chain.juc.day01;

/**
 * 内存可见性和volatile，内存栅栏，重排序，比较于synchronized的同步锁而言比较轻量<br>
 * 但是没有互斥性，不能保证原子性
 * 
 * @author Chain
 *
 */
public class Test01Volatile {

	public static void main(String[] args) {
		// test1();
		test2();
	}

	public static void test2() {
		MyRunnableImpl01B runnable = new MyRunnableImpl01B();

		new Thread(runnable).start();

		while (true) {
			boolean flag = runnable.isFlag();
			// 解决内存可见性的问题
			// System.out.println("flag in main read is " + flag);
			if (flag) {
				System.out.println("flag now is " + flag);
				break;
			}
		}
	}

	public static void test1() {
		MyRunnableImpl01A runnable = new MyRunnableImpl01A();

		new Thread(runnable).start();

		while (true) {
			boolean flag = runnable.isFlag();
			// 内存可见性，如果不调用println的话，那么两个线程之间的flag是互相不可见的
			// 因此这个while循环会一直执行下去
			// System.out.println("flag in main read is " + flag);
			if (flag) {
				System.out.println("flag now is " + flag);
				break;
			}
		}

	}
}

class MyRunnableImpl01A implements Runnable {

	private boolean flag = false;

	@Override
	public void run() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		flag = true;
		System.out.println("flag has changed to " + flag);
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}

class MyRunnableImpl01B implements Runnable {

	// 使用volatile可以解决内存不可见的问题，使得不断的执行内存栅栏操作，保证内存中的共享变量的值一直得到更新
	private volatile boolean flag = false;

	@Override
	public void run() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		flag = true;
		System.out.println("flag has changed to " + flag);
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
