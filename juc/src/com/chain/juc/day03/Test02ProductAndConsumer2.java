package com.chain.juc.day03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产消费的几种模型，这里介绍Lock和Condition，await和signal方式
 * 
 * @author Chain
 *
 */
public class Test02ProductAndConsumer2 {

	public static void main(String[] args) throws InterruptedException {
		test1();
	}

	public static void test1() throws InterruptedException {
		Shop2 shop = new Shop2();
		Consumer1 consumer = new Consumer1(shop);
		Productor1 productor = new Productor1(shop);
		new Thread(consumer, "Consumer1").start();
		new Thread(productor, "Productor1").start();
		new Thread(consumer, "Consumer2").start();
		new Thread(productor, "Productor2").start();
		new Thread(consumer, "Consumer3").start();
		new Thread(productor, "Productor3").start();

		Thread.sleep(10000);
		System.out.println("in times: " + shop.inTimes);
		System.out.println("out times: " + shop.outTimes);
	}

}

class Shop2 extends AbstractShop {

	private volatile int product;

	public int inTimes;
	public int outTimes;

	private ReentrantLock lock = new ReentrantLock();
	private Condition conditionIn = lock.newCondition();
	private Condition conditionOut = lock.newCondition();

	@Override
	public void in() {
		lock.lock();
		try {
			while (now() >= MAX) {
				System.out.println("shop is full");
				try {
					conditionIn.await();
				} catch (InterruptedException e) {
				}
			}

			product++;
			inTimes++;
			System.out.println("after in, shop left is " + now());
			conditionOut.signalAll();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void out() {
		lock.lock();
		try {
			while (now() <= MIN) {
				System.out.println("shop is empty");
				try {
					conditionOut.await();
				} catch (InterruptedException e) {
				}
			}

			product--;
			outTimes++;
			System.out.println("after out, shop left is " + now());
			conditionIn.signalAll();
		} finally {
			lock.unlock();
		}
	}

	private int now() {
		return product;
	}

	private static final int MAX = 10;
	private static final int MIN = 0;
}
