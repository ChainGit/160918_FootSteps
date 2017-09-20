package com.chain.juc.day03;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 使用阻塞队列实现的生产者消费者模型
 * 
 * @author Chain
 *
 */
public class Test03ProductAndConsumer3 {

	public static void main(String[] args) throws InterruptedException {
		test1();
	}

	public static void test1() throws InterruptedException {
		Shop3 shop = new Shop3();
		Consumer1 consumer = new Consumer1(shop);
		Productor1 productor = new Productor1(shop);
		new Thread(consumer, "Consumer1").start();
		new Thread(productor, "Productor1").start();
		new Thread(consumer, "Consumer2").start();
		new Thread(productor, "Productor2").start();
		new Thread(consumer, "Consumer3").start();
		new Thread(productor, "Productor3").start();
	}

}

class Shop3 extends AbstractShop {

	private MyBlockArrayQueue queue = new MyBlockArrayQueue(10);

	@Override
	public void in() {
		queue.add();
	}

	@Override
	public void out() {
		queue.poll();
	}

}

class MyBlockArrayQueue {

	private volatile int limit;

	private Queue<Integer> queue;

	public MyBlockArrayQueue(int limit) {
		this.limit = limit;
		this.queue = new ArrayDeque<>(limit);
	}

	public synchronized void add() {
		while (queue.size() >= limit) {
			System.out.println("the queue is full");
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		queue.add(1);
		System.out.println("after add, the queue left is " + queue.size());
		notifyAll();
	}

	public synchronized int poll() {
		// 在线程拿到锁之后，不断循环的方式直到满足条件继续执行下面的语句
		while (queue.size() <= 0) {
			System.out.println("the queue is empty");
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		int value = queue.poll();
		notifyAll();
		System.out.println("after poll, the queue left is " + queue.size());
		return value;
	}

	public int size() {
		return queue.size();
	}

}
