package com.chain.juc.day01;

/**
 * 模拟实现CAS
 * 
 * CAS 包含了三个操作数： ①内存值 V ②预估值 A ③更新值 B 当且仅当 V == A 时， V = B; 否则，不会执行任何操作。
 * 
 * @author Chain
 *
 */
public class Test03CAS {

	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		MyRunnableImpl03A runnable = new MyRunnableImpl03A();

		for (int i = 0; i < 100; i++) {
			new Thread(runnable).start();
		}

	}

}

class MyRunnableImpl03A implements Runnable {

	// 使用原子变量，确保多个进程同时访问时的原子性
	private MyAtomicInteger num = new MyAtomicInteger();

	@Override
	public void run() {
		// 如果使用println的话，可能会刷新线程中的数据到主内存中
		// System.out.println(getAndIncrementNumber());
		// i++分三步操作
		// int temp = i;
		// i = i + 1;
		// i = temp;
		// 这样的三步操作可能会在多线程里被分开执行
		int value = getAndIncrementNumber();
		System.out.println(value);
	}

	private int getAndIncrementNumber() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}

		// CAS操作
		return num.getAndIncrement();
	}

	public int getNum() {
		return num.intValue();
	}

}

class MyAtomicInteger {

	private volatile int value;

	public int intValue() {
		return get();
	}

	// 阻塞式
	public int getAndIncrement() {
		int oldValue = this.get();
		while (true) {
			if (compareAndSet(oldValue, oldValue + 1))
				break;
		}

		return oldValue;
	}

	// 同步获取
	private synchronized int get() {
		return this.value;
	}

	// 比较值
	private int compareAndSwap(int expectValue, int newValue) {
		// 独处内存中存放的值
		int oldValue = this.get();

		// 和理论上的原来的值进行比较
		// 当且仅当相等时，将值替换成新值
		if (oldValue == expectValue) {
			this.value = newValue;
		}

		// 返回内存中的值
		return oldValue;
	}

	// 返回比较并更新的结果（非阻塞，立即返回）
	public boolean compareAndSet(int expectValue, int newValue) {
		// 比较和set
		return expectValue == compareAndSwap(expectValue, newValue);
	}
}
