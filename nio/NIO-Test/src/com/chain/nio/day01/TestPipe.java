package com.chain.nio.day01;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;

import org.junit.Test;

/**
 * 管道是2个线程之间的单向数据连接。
 * 
 * Pipe有一个source通道和一个sink通道。
 * 
 * 数据会被写到sink通道，从source通道读取。
 * 
 * @author chain
 *
 */
public class TestPipe {

	@Test
	public void test() throws IOException {
		Pipe pipe = Pipe.open();

		new Thread(() -> {
			ByteBuffer bbuf = ByteBuffer.allocate(1024);

			String str = "data";
			SinkChannel sink = pipe.sink();
			bbuf.put(str.getBytes());
			bbuf.flip();
			try {
				sink.write(bbuf);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println("thread a send: " + str);
		}).start();

		new Thread(() -> {
			ByteBuffer bbuf = ByteBuffer.allocate(1024);

			SourceChannel source = pipe.source();
			try {
				source.read(bbuf);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			bbuf.flip();
			System.out.println("thread b recv: " + new String(bbuf.array()));
		}).start();

	}

}
