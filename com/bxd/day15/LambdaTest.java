package com.bxd.day15;

public class LambdaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(() -> {
			while (true)
				System.out.println("Hello1");
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true)
					System.out.println("Hello2");
			}

		}).start();
	}

}
