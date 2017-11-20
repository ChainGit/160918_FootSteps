package com.chain.nio.day02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.junit.Test;

/**
 * 阻塞式使用NIO完成网络通信
 * 
 * @author chain
 *
 */
public class TestBlockingNIO {

	private int SERVER_PORT = 8989;
	private int CLIENT_PORT = 18989;

	@Test
	public void testUDPClient() throws IOException {
		DatagramChannel dc = DatagramChannel.open().bind(new InetSocketAddress("127.0.0.1", CLIENT_PORT));

		ByteBuffer bbuf = ByteBuffer.allocate(1024);
		String str = "hello";
		bbuf.put(str.getBytes());
		bbuf.flip();
		dc.send(bbuf, new InetSocketAddress("127.0.0.1", SERVER_PORT));

		System.out.println("client send: " + str);

		bbuf.clear();
		dc.receive(bbuf);
		bbuf.flip();

		System.out.println("client recv: " + new String(bbuf.array()));

		dc.disconnect();

		dc.close();
	}

	@Test
	public void testUDPServer() throws IOException {
		DatagramChannel dc = DatagramChannel.open().bind(new InetSocketAddress("127.0.0.1", SERVER_PORT));

		System.out.println("server listening ...");

		try {
			while (true) {
				ByteBuffer bbuf = ByteBuffer.allocate(1024);

				// 等待接收，注意不是read
				dc.receive(bbuf);
				bbuf.flip();

				System.out.println("server recv: " + new String(bbuf.array()));

				String str = "server echo";
				bbuf.clear();
				bbuf.put(str.getBytes());
				bbuf.flip();

				// UDP是无连接的，所以是不能直接write的，需要重新connect客户端
				dc.send(bbuf, new InetSocketAddress("127.0.0.1", CLIENT_PORT));

				System.out.println("server send: " + str);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			dc.close();
		}
	}

	@Test
	public void testTCPClient() throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("127.0.0.1", SERVER_PORT));

		ByteBuffer bbuf = ByteBuffer.allocate(1024);
		String str = "hello";
		bbuf.put(str.getBytes());
		bbuf.flip();

		socketChannel.write(bbuf);
		socketChannel.shutdownOutput();

		System.out.println("client send: " + str);

		bbuf.clear();
		socketChannel.read(bbuf);
		bbuf.flip();

		System.out.println("client recv: " + new String(bbuf.array()));

		socketChannel.close();
	}

	@Test
	public void testTCPServer() throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()
				.bind(new InetSocketAddress("127.0.0.1", SERVER_PORT));

		System.out.println("server listening ...");

		ByteBuffer bbuf = ByteBuffer.allocate(1024);

		SocketChannel socketChannel = serverSocketChannel.accept();
		socketChannel.read(bbuf);

		bbuf.flip();
		System.out.println("server recv: " + new String(bbuf.array()));

		bbuf.flip();
		bbuf.clear();
		String str = "server echo";
		bbuf.put(str.getBytes());
		bbuf.flip();

		socketChannel.write(bbuf);
		socketChannel.shutdownOutput();

		System.out.println("server send: " + str);

		socketChannel.close();
	}

}
