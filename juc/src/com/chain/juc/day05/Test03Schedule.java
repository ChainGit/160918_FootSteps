package com.chain.juc.day05;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程的调度，定时任务或延时任务
 * 
 * @author Chain
 *
 */
public class Test03Schedule {

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);

		try {
			for (int i = 0; i < 10; i++) {
				Runnable r = new Runnable() {

					@Override
					public void run() {
						System.out.println((int) (Math.random() * 101));
					}

				};

				// 延时执行一次
				// pool.schedule(r, 5, TimeUnit.SECONDS);

				// 定时执行，并规定第一次执行的延时
				pool.scheduleAtFixedRate(r, 1, 3, TimeUnit.SECONDS);
			}

			while (true) {
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
				}
			}
		} finally {
			pool.shutdown();
		}
	}

}
