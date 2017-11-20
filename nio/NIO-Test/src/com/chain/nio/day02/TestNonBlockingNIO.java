package com.chain.nio.day02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.junit.Test;

/**
 * 非阻塞式使用NIO完成网络通信
 * 
 * @author chain
 *
 */
public class TestNonBlockingNIO {

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

		dc.configureBlocking(false);

		Selector selector = Selector.open();

		dc.register(selector, SelectionKey.OP_READ);

		System.out.println("server inited ...");

		while (selector.select() > 0) {
			
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			
			while (iterator.hasNext()) {
				SelectionKey next = iterator.next();
			
				if (next.isReadable()) {
					ByteBuffer bbuf = ByteBuffer.allocate(1024);
					DatagramChannel channel = (DatagramChannel) next.channel();
					channel.receive(bbuf);
					bbuf.flip();
					System.out.println("server recv: " + new String(bbuf.array()));

					channel.register(selector, SelectionKey.OP_WRITE);
					iterator.remove();
				} else if (next.isWritable()) {
					ByteBuffer bbuf = ByteBuffer.allocate(1024);
					String str = "server echo";
					bbuf.put(str.getBytes());
					bbuf.flip();
					DatagramChannel channel = (DatagramChannel) next.channel();
					channel.send(bbuf, new InetSocketAddress("127.0.0.1", CLIENT_PORT));
					System.out.println("server send: " + str);
					
					channel.register(selector, SelectionKey.OP_READ);
					iterator.remove();
				}
			}
		}

	}

	@Test
	public void testTCPClient() throws IOException {
		SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", SERVER_PORT));

		// 改为非阻塞式
		sc.configureBlocking(false);

		ByteBuffer bbuf = ByteBuffer.allocate(1024);
		String str = "hello";
		bbuf.put(str.getBytes());
		bbuf.flip();

		sc.write(bbuf);
		sc.shutdownOutput();

		System.out.println("client send: " + str);

		bbuf.clear();
		sc.read(bbuf);
		bbuf.flip();

		System.out.println("client recv: " + new String(bbuf.array()));

		sc.close();
	}

	@Test
	public void testTCPServer() throws IOException, InterruptedException {
		ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress("127.0.0.1", SERVER_PORT));

		// 改为非阻塞式
		ssc.configureBlocking(false);

		// 新建选择器
		Selector selector = Selector.open();

		// 注册该通道到选择器上
		ssc.register(selector, SelectionKey.OP_ACCEPT);

		// 交由另一个线程处理
		Thread p = new TCPServerThread(selector, ssc);
		p.start();

		p.join();

		// 做一些别的事
		// ...

	}

	private class TCPServerThread extends Thread {

		private Selector selector;
		private ServerSocketChannel serverSocketChannel;

		public TCPServerThread(Selector selector, ServerSocketChannel ssc) {
			this.selector = selector;
			this.serverSocketChannel = ssc;
		}

		@Override
		public void run() {
			System.out.println("server inited ...");
			try {
				// 轮询式（循环）的获取选择器上已经“准备就绪”的事件
				while (selector.select() > 0) {
					System.out.println("selector changed ...");

					// 获取当前选择器中所有注册的“选择键(已就绪的监听事件)”
					Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

					// 获取选择键的类型
					while (iterator.hasNext()) {
						// 8. 获取准备“就绪”的是事件
						SelectionKey next = iterator.next();

						// 如果是acceptable，即用户连接已经建立好了
						if (next.isAcceptable()) {
							SocketChannel sc = serverSocketChannel.accept();

							// 切换成非阻塞式
							sc.configureBlocking(false);

							// 尽管已经accepted，但是客户端的数据不一定准备就绪可读，需要再注册
							sc.register(selector, SelectionKey.OP_READ);

							iterator.remove();
						}
						// 如果是readable，即通道数据已经准备好了
						else if (next.isReadable()) {
							// 获取当前选择器上“读就绪”状态的通道
							SocketChannel sc = (SocketChannel) next.channel();
							ByteBuffer bbuf = ByteBuffer.allocate(1024);
							sc.read(bbuf);
							bbuf.flip();

							System.out.println("server recv: " + new String(bbuf.array()));

							// 读操作进行完毕后再进行写操作
							sc.register(selector, SelectionKey.OP_WRITE);

							iterator.remove();
						}
						// 如果是writable，即通道已经可以写操作
						else if (next.isWritable()) {
							// 获取当前选择器上“写就绪”状态的通道
							SocketChannel sc = (SocketChannel) next.channel();

							ByteBuffer bbuf = ByteBuffer.allocate(1024);
							String str = "server echo";
							bbuf.put(str.getBytes());
							bbuf.flip();

							sc.write(bbuf);
							sc.shutdownOutput();

							System.out.println("server send: " + str);

							// 如果提前关闭Input则连接也会提前关闭
							sc.shutdownInput();

							// 读写均完成后就可以关闭通道，将该socket channel 移除
							sc.close();

							iterator.remove();
						}
					}

				}
			} catch (Exception e) {
				throw new RuntimeException("tcp server thread", e);
			}
		}

	}
}
