package com.chain.juc.day02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步代码块，同步方法：synchronized（隐式锁，JVM会不断优化，还是首选的方式）
 * 
 * 同步锁：更加灵活（显式锁）ReentrantLock（可重入锁）
 * 
 * @author Chain
 *
 */
public class Test02Lock {

	public static void main(String[] args) {
		// test1();
		// test2();
		test3();
	}

	private static void test1() {
		Ticket1 ticket1 = new Ticket1();
		new Thread(ticket1, "A").start();
		new Thread(ticket1, "B").start();
		new Thread(ticket1, "C").start();
	}

	private static void test2() {
		Ticket2 ticket2 = new Ticket2();
		new Thread(ticket2, "A").start();
		new Thread(ticket2, "B").start();
		new Thread(ticket2, "C").start();
	}

	private static void test3() {
		Ticket3 ticket3 = new Ticket3();
		new Thread(ticket3, "A").start();
		new Thread(ticket3, "B").start();
		new Thread(ticket3, "C").start();
	}

}

// 使用显式锁ReetrantLock
class Ticket3 implements Runnable {
	private int ticket = 100;

	private Lock lock = new ReentrantLock();

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}

			lock.lock();
			try {
				if (this.ticket > 0) {
					sell();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
					}
					System.out.println("ticket " + Thread.currentThread().getName() + " sell one, left: " + ticket);
				} else {
					break;
				}
			} finally {
				lock.unlock();
			}
		}
	}

	public void sell() {
		this.ticket--;
	}
}

// 使用隐式锁synchronized和volatile
class Ticket2 implements Runnable {

	private volatile int ticket = 100;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			synchronized (this) {
				if (this.ticket > 0) {
					sell();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
					}
					System.out.println("ticket " + Thread.currentThread().getName() + " sell one, left: " + ticket);
				} else {
					break;
				}
			}
		}
	}

	public synchronized void sell() {
		this.ticket--;
	}

}

// 不使用线程同步的机制，会卖成负票
class Ticket1 implements Runnable {

	private int ticket = 100;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			if (this.ticket > 0) {
				sell();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
				System.out.println("ticket " + Thread.currentThread().getName() + " sell one, left: " + ticket);
			} else {
				break;
			}
		}
	}

	public void sell() {
		this.ticket--;
	}

}
