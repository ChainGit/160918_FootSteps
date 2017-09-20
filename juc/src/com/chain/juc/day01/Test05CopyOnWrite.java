package com.chain.juc.day01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制，适合读多写少的高并发情况
 * 
 * 写时复制不适合写多的情况，因为一旦发生修改会复制所有的数据到新的区域，开销比较大
 * 
 * 比如CopyOnWriteArrayList支持迭代时添加删除元素，不会抛出异常，因为是写时复制
 * 
 * @author Chain
 *
 */
public class Test05CopyOnWrite {

	public static void main(String[] args) {
		// test1();
		test2();
	}

	private static void test2() {
		MyRunnableImpl05B runnable = new MyRunnableImpl05B();
		for (int i = 0; i < 10; i++) {
			new Thread(runnable).start();
		}
	}

	private static void test1() {
		MyRunnableImpl05A runnable = new MyRunnableImpl05A();
		for (int i = 0; i < 10; i++) {
			new Thread(runnable).start();
		}
	}

}

class MyRunnableImpl05B implements Runnable {

	private List<Integer> list = new CopyOnWriteArrayList<>();

	{
		list.add(123);
		list.add(234);
		list.add(345);
	}

	@Override
	public void run() {
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			System.out.println(Thread.currentThread().getName() + ": " + next);

			// 不会 抛出java.util.ConcurrentModificationException
			list.add((int) (Math.random() * 101));
		}
	}

}

class MyRunnableImpl05A implements Runnable {

	private ArrayList<Integer> list = new ArrayList<>();

	{
		list.add(123);
		list.add(234);
		list.add(345);
	}

	@Override
	public void run() {
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			System.out.println(Thread.currentThread().getName() + ": " + next);

			// 抛出java.util.ConcurrentModificationException
			// list.add((int) (Math.random() * 101));
		}
	}

}
