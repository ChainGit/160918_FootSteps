package com.chain.juc.day02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 创建线程的方式三：Callable，相较于Runnable，可以有返回值和有异常抛出
 * 
 * 和FutureTask协同工作，需要实现Future接口
 * 
 * FutureTask也可以实现CountDownLatch的闭锁功能
 * 
 * @author Chain
 *
 */
public class Test01Callable {

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		MyRunnableImpl01A runnable = new MyRunnableImpl01A();
		FutureTask<Integer> task = new FutureTask<>(runnable);
		new Thread(task).start();

		try {
			// 如果超过了5s，会抛出TimeoutException，可以做进一步处理，比如等一会再获取等等
			// TimeUnit是一个枚举类
			Integer result = task.get(5, TimeUnit.SECONDS);
			System.out.println(result);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}

class MyRunnableImpl01A implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			sum += i;
		}
		return sum;
	}

}
