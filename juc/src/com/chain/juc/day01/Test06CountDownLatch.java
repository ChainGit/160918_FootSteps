package com.chain.juc.day01;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁：只有当所有的线程都完成了工作，才进行下一步工作，可以实现线程的同步协作
 * 
 * @author Chain
 *
 */
public class Test06CountDownLatch {

	public static void main(String[] args) throws InterruptedException {
		test1();
	}

	public static void test1() throws InterruptedException {
		// 门栓，实现线程的协调，类似可以实现发令枪的效果，同样可以使用Fork-Join框架实现
		final CountDownLatch countDownLatch = new CountDownLatch(10);

		MyRunnbaleImpl06A runnable = new MyRunnbaleImpl06A(countDownLatch);

		for (int i = 0; i < 10; i++) {
			new Thread(runnable).start();
		}

		while (true) {
			Thread.sleep(2000);
			if (countDownLatch.getCount() == 0) {
				System.out.println("all work done");
				break;
			}
		}
	}

}

class MyRunnbaleImpl06A implements Runnable {

	private CountDownLatch countDownLatch;

	public MyRunnbaleImpl06A(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		try {
			long sum = 0L;
			for (long i = 0; i < 500_0000_0000L; i++) {
				sum += i;
			}
			System.out.println(sum);
		} finally {
			countDownLatch.countDown();
		}
	}

}
