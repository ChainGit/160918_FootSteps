package com.chain.juc.day01;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jdk1.5 时是分段锁 <br>
 * 之后就改成CAS操作，CAS是并发的基石，可以理解为无锁算法
 * 
 * ConcurrentHashMap可以有效解决HashMap的多线程下的并发造成的问题
 * 
 * @author Chain
 *
 */
public class Test04ConcurrentHashMap {

	public static void main(String[] args) throws InterruptedException {
		MyRunnableImpl04A runnable = new MyRunnableImpl04A();
		for (int i = 0; i < 100; i++) {
			new Thread(runnable).start();
		}

		Thread.sleep(10000);

		System.out.println("final: " + runnable.getSize());
	}

}

class MyRunnableImpl04A implements Runnable {

	private ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();

	@Override
	public void run() {
		Random random = new Random();
		for (int i = 0; i < 10000; i++) {
			concurrentHashMap.put(i, random.nextInt());
		}
		System.out.println(getSize());
	}

	public int getSize() {
		return concurrentHashMap.size();
	}

}
