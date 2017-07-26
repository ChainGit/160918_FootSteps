package com.bxd.day21;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedTest {

	// 多线程实现管道流，单线程容易导致死锁
	public static void main(String[] args) throws Exception {
		test1();
		test2();
	}

	private static void test1() throws Exception {
		// TODO Auto-generated method stub
		PipedOutputStream pos = new PipedOutputStream();
		PipedInputStream pis = new PipedInputStream();

		pis.connect(pos);

		Thread t1 = new Thread(new WriteRunnable(pos));
		Thread t2 = new Thread(new ReadRunnable(pis));
		t1.start();
		t2.start();
	}

	private static void test2() {
		// TODO Auto-generated method stub

	}

	static class WriteRunnable implements Runnable {

		private PipedOutputStream pos;

		public WriteRunnable(PipedOutputStream p) {
			this.pos = p;
		}

		@Override
		public void run() {
			try {
				while (true) {
					System.out.println("begin to write..");

					try {
						Thread.sleep(3000);
					} catch (Exception e) {
						e.printStackTrace();
					}

					byte[] buf = new String("DATADATA").getBytes();

					pos.write(buf);

					System.out.println("write end!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pos != null)
						pos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class ReadRunnable implements Runnable {

		private PipedInputStream pis;

		public ReadRunnable(PipedInputStream p) {
			this.pis = p;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while (true) {
					byte[] buf = new byte[1024];
					int len = -1;

					System.out.println("wait to read..");

					len = pis.read(buf);
					if (len != -1)
						System.out.println(new String(buf, 0, len));

					System.out.println("read end!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pis != null)
						pis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
