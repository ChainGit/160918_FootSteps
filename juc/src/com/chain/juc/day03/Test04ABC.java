package com.chain.juc.day03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题
 * 
 * ABC交替打印
 * 
 * @author Chain
 *
 */
public class Test04ABC {

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		MyABC abc = new MyABC();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					abc.printA();
				}
			}

		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					abc.printB();
				}
			}

		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					abc.printC();
				}
			}

		}).start();
	}

}

class MyABC {

	private ReentrantLock lock = new ReentrantLock();
	private Condition conditionA = lock.newCondition();
	private Condition conditionB = lock.newCondition();
	private Condition conditionC = lock.newCondition();

	private volatile int current = 1;

	public void printA() {
		lock.lock();
		try {
			while (current != 1) {
				try {
					conditionA.await();
				} catch (InterruptedException e) {
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			System.out.println("A");
			current = 2;
			conditionB.signal();
		} finally {
			lock.unlock();
		}
	}

	public void printB() {
		lock.lock();
		try {
			while (current != 2) {
				try {
					conditionB.await();
				} catch (InterruptedException e) {
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			System.out.println("B");
			current = 3;
			conditionC.signal();
		} finally {
			lock.unlock();
		}
	}

	public void printC() {
		lock.lock();
		try {
			while (current != 3) {
				try {
					conditionC.await();
				} catch (InterruptedException e) {
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			System.out.println("C");
			current = 1;
			conditionA.signal();
		} finally {
			lock.unlock();
		}
	}

}
