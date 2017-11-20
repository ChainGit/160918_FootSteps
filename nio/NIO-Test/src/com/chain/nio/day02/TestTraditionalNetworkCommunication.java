package com.chain.nio.day02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * 传统的网络通信
 * 
 * 传统的 IO 流都是阻塞式的。因此，在完成网络通信进行 IO 操作时，由于线程会 阻塞，所以服务器端必须为每个客户端都提供一个独立的线程进行处理，
 * 当服务器端需要处理大量客户端时，性能急剧下降。
 * 
 * @author chain
 *
 */
public class TestTraditionalNetworkCommunication {

	// 先执行testUDPServer再执行testUDPClient
	@Test
	public void testUDPClient() throws IOException {
		DatagramSocket clientSocket = new DatagramSocket();

		clientSocket.connect(new InetSocketAddress("127.0.0.1", SERVER_PORT));
		String str = "hello";
		clientSocket.send(new DatagramPacket(str.getBytes(), 0, str.length()));
		System.out.println("client send: " + str);

		// 缓冲区要设置的尽量大一点
		byte[] buf = new byte[1024 * 8];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		clientSocket.receive(dp);
		buf = dp.getData();
		System.out.println("client recv: " + new String(buf, 0, buf.length));

		clientSocket.close();
	}

	@Test
	public void testUDPServer() throws IOException {
		DatagramSocket serverSocket = new DatagramSocket(new InetSocketAddress("127.0.0.1", SERVER_PORT));
		System.out.println("server listening ...");
		try {
			while (true) {
				byte[] buf = new byte[1024 * 8];
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				serverSocket.receive(dp);
				buf = dp.getData();
				System.out.println("server recv: " + new String(buf, 0, buf.length));

				serverSocket.connect(dp.getSocketAddress());
				String str = "server echo";
				serverSocket.send(new DatagramPacket(str.getBytes(), 0, str.length()));
				System.out.println("server send: " + str);

				serverSocket.disconnect();
			}
		} finally {
			serverSocket.close();
		}
	}

	private int SERVER_PORT = 8989;

	// 先启动testTCPServer再执行testTCPClient
	@Test
	public void testTCPClient() throws IOException {
		Socket clientSocket = new Socket("127.0.0.1", SERVER_PORT);

		BufferedOutputStream bos = new BufferedOutputStream(clientSocket.getOutputStream());
		BufferedInputStream bis = new BufferedInputStream(clientSocket.getInputStream());

		String str = "hello";
		bos.write(str.getBytes());
		bos.flush();
		clientSocket.shutdownOutput();
		System.out.println("client send: " + str);

		byte[] buf = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = -1;
		while ((len = bis.read(buf)) != -1)
			baos.write(buf, 0, len);
		clientSocket.shutdownInput();
		System.out.println("client recv: " + baos.toString());

		clientSocket.close();
	}

	@Test
	public void testTCPServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		System.out.println("server listening ...");
		try {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				new TCPServerThread(clientSocket).start();
			}
		} finally {
			serverSocket.close();
		}
	}

	class TCPServerThread extends Thread {

		private Socket clientSocket;

		public TCPServerThread(Socket cs) {
			this.clientSocket = cs;
		}

		@Override
		public void run() {
			try {
				BufferedOutputStream bos = new BufferedOutputStream(clientSocket.getOutputStream());
				BufferedInputStream bis = new BufferedInputStream(clientSocket.getInputStream());

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int len = -1;
				while ((len = bis.read(buf)) != -1)
					baos.write(buf, 0, len);
				clientSocket.shutdownInput();
				System.out.println("server recv: " + baos.toString());

				String str = "server echo";
				bos.write(str.getBytes());
				bos.flush();
				clientSocket.shutdownOutput();
				System.out.println("server send: " + str);

				clientSocket.close();
			} catch (Exception e) {
				throw new RuntimeException("tcp server thread");
			}
		}
	}

}
