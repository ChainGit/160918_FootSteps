package com.chain.juc.day05;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * java8的优化for循环，fork-join，和stream方式
 * 
 * @author Chain
 *
 */
public class Test04ForkJoin {

	public static void main(String[] args) {
		// test1();
		// test2();
		test3();
	}

	// Java8的Stream和Lambda
	private static void test3() {
		Instant start = Instant.now();
		long sum = LongStream.rangeClosed(0L, 500_0000_0000L).parallel().reduce(0L, Long::sum);
		Instant end = Instant.now();
		System.out.println(sum);
		System.out.println("spend time: " + Duration.between(start, end).getSeconds());
	}

	// 传统的for循环
	private static void test2() {
		Long start = System.currentTimeMillis();
		long sum = 0;
		for (long i = 0; i < 500_0000_0000L; i++) {
			sum += i;
		}
		long end = System.currentTimeMillis();
		System.out.println(sum);
		System.out.println("spend time: " + (end - start) / 1000);
	}

	// fork-join框架
	private static void test1() {
		Instant start = Instant.now();
		ForkJoinTask<Long> task = new MyForkJoinTest(0L, 500_0000_0000L);
		ForkJoinPool pool = new ForkJoinPool();
		Long value = pool.invoke(task);
		Instant end = Instant.now();
		System.out.println(value);
		System.out.println("spend time: " + Duration.between(start, end).getSeconds());
	}

}

class MyForkJoinTest extends RecursiveTask<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int THRESHOLD = 100_0000;

	private long start;
	private long end;

	public MyForkJoinTest(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		if (end - start <= THRESHOLD) {
			long sum = 0;
			for (long i = start; i <= end; i++) {
				sum += i;
			}
			return sum;
		} else {
			// 防止溢出的做法
			long mid = (end - start) / 2 + start;
			// 分拆任务
			MyForkJoinTest fk1 = new MyForkJoinTest(start, mid);
			fk1.fork();
			MyForkJoinTest fk2 = new MyForkJoinTest(mid + 1, end);
			fk2.fork();
			return fk1.join() + fk2.join();
		}
	}

}
