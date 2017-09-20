package com.chain.juc.day05;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池，第四种获取线程的方法
 * 
 * 一、线程池：提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁额外开销，提高了响应的速度。
 * 
 * 二、线程池的体系结构： java.util.concurrent.Executor : 负责线程的使用与调度的根接口
 * |--**ExecutorService 子接口: 线程池的主要接口 |--ThreadPoolExecutor 线程池的实现类
 * |--ScheduledExecutorService 子接口：负责线程的调度 |--ScheduledThreadPoolExecutor ：继承
 * ThreadPoolExecutor， 实现 ScheduledExecutorService
 * 
 * 三、工具类 : Executors ExecutorService newFixedThreadPool() : 创建固定大小的线程池
 * ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
 * ExecutorService newSingleThreadExecutor() : 创建单个线程池。线程池中只有一个线程
 * 
 * ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程，可以延迟或定时的执行任务。
 * 
 * Executer 和 Future
 * 
 * @author Chain
 *
 */
public class Test02ThreadPool {

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		ArrayList<Future<Long>> list = new ArrayList<>();

		// 固定大小的线程池，如果线程池满，会有等待队列
		ExecutorService pool = Executors.newFixedThreadPool(10);

		try {
			for (int i = 0; i < 20; i++) {
				Callable<Long> c = new Callable<Long>() {

					@Override
					public Long call() throws Exception {
						long sum = 0;
						for (long i = 0; i < 100; i++) {
							Thread.sleep(100);
							sum += i;
						}
						return sum;
					}
				};

				// 将线程提交到线程池种，并返回Future的实例
				Future<Long> future = pool.submit(c);
				list.add(future);
			}

			for (Future<Long> f : list) {
				Long value = null;
				try {
					// 会阻塞获得结果
					value = f.get();
				} catch (InterruptedException | ExecutionException e) {
				}
				System.out.println(value);
			}
		} finally {
			pool.shutdown();
		}
	}
}
