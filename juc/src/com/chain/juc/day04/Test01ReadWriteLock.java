package com.chain.juc.day04;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 
 * 写写/读写要加锁
 * 
 * 读读不用加锁
 * 
 * 写锁是独占的，换句话说是只能有一个线程能写数据
 * 
 * 读锁是共享的
 * 
 * @author Chain
 *
 */
public class Test01ReadWriteLock {

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		MyReadWriteLockTest rw = new MyReadWriteLockTest();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						rw.get();
					}
				}

			}).start();
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					rw.set();
				}
			}

		}).start();

	}

}

class MyReadWriteLockTest {

	private ReadWriteLock lock = new ReentrantReadWriteLock();

	private volatile int data;

	public void set() {
		lock.writeLock().lock();
		try {
			data = (int) (Math.random() * 101);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public void get() {
		lock.readLock().lock();
		try {
			System.out.println(data);
		} finally {
			lock.readLock().unlock();
		}
	}
}
