package com.chain.juc.day03;

/**
 * 生产消费的几种模型，这里介绍synchronized和wait和notify
 * 
 * 虚假唤醒问题
 * 
 * 正确的生产消费模型应该是当仓库满时不应该再放货，仓库空时不应该再消费，有一个通知机制才是正确的
 * 
 * @author Chain
 *
 */
public class Test01ProductAndConsumer {

	public static void main(String[] args) throws InterruptedException {
		test1();
	}

	public static void test1() throws InterruptedException {
		Shop1 shop = new Shop1();
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

abstract class AbstractShop {
	public abstract void in();

	public abstract void out();
}

class Shop1 extends AbstractShop {

	// 使用volatile保证可见性
	// private int product;
	private volatile int product;

	// 统计进货和出货的总数，如果不用多线程会造成数量不符合预计
	public int inTimes;
	public int outTimes;

	@Override
	public synchronized void in() {
		// 注意需要使用while，因为notifyAll唤醒的是所有的线程，因此也会唤醒productor线程，而不仅仅是consumer，否则会造成虚假唤醒
		// 具体的表现就是：仓库爆仓，超过最大的10
		while (now() >= MAX) {
			System.out.println("shop is full");
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}

		product++;
		inTimes++;
		System.out.println("after in, shop left is " + now());
		this.notifyAll();
	}

	@Override
	public synchronized void out() {
		// 同理out，需要使用while，否则会造成虚假唤醒
		// 具体的表现就是：仓库为负，出现负数
		while (now() <= MIN) {
			System.out.println("shop is empty");
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}

		product--;
		outTimes++;
		System.out.println("after out, shop left is " + now());
		this.notifyAll();
	}

	private synchronized int now() {
		return product;
	}

	private static final int MAX = 10;
	private static final int MIN = 0;

}

class Consumer1 implements Runnable {

	private AbstractShop shop;

	public Consumer1(AbstractShop shop) {
		this.shop = shop;
	}

	public void buy() {
		shop.out();
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				// 可以使用随机数
				Thread.sleep(300);// 修改Productor的Sleep时间，可以模拟生产快消费慢可能爆仓，或者生产慢消费快可能负仓的情况
			} catch (InterruptedException e) {
			}

			buy();
			System.out.println("consumer " + Thread.currentThread().getName() + " buy one");
		}
	}
}

class Productor1 implements Runnable {

	private AbstractShop shop;

	public Productor1(AbstractShop shop) {
		this.shop = shop;
	}

	public void put() {
		shop.in();
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}

			put();
			System.out.println("productor " + Thread.currentThread().getName() + " put one");
		}
	}
}
