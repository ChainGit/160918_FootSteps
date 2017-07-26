package com.bxd.day23;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP服务端,用于处理每一个TCP客户端的连接,每个客户连接由独立进程处理,应答客户端数据
 * 
 * @author Chain
 *
 */
public class TCPServerTest {

	private static final int port = 10101;

	public static void main(String[] args) throws Exception {
		ServerSocket sersok = new ServerSocket(port);
		System.out.println("服务器启动成功[" + sersok.getInetAddress() + ":" + sersok.getLocalPort() + "].");

		try {
			while (true) {
				Socket clisok = sersok.accept();
				System.out.println("get client: " + clisok.getInetAddress() + ":" + clisok.getPort());
				new Thread(new MyClientProcess(clisok)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sersok.close();
		}
	}
}

class MyClientProcess implements Runnable {

	private Socket clisok;

	public MyClientProcess(Socket s) {
		this.clisok = s;
	}

	@Override
	public void run() {
		try {
			InputStream ins = clisok.getInputStream();
			OutputStream outs = clisok.getOutputStream();
			while (true) {
				byte[] buf = new byte[1024 * 64];
				int re = ins.read(buf);
				if (re == -1)
					break;
				String wr = new String(buf, 0, getLength(buf));
				System.out.println("client " + clisok.getInetAddress() + ":" + clisok.getPort() + " data: " + wr);
				outs.write(wr.toUpperCase().getBytes());
				outs.flush();
			}
			System.out.println("client " + clisok.getInetAddress() + ":" + clisok.getPort() + " closed.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeRes();
		}
	}

	private static int getLength(byte[] buf) {
		int len = 0;
		for (; len < buf.length; len++)
			if (buf[len] == 0) {
				int i = len;
				for (; i < buf.length; i++)
					if (buf[i] != 0)
						break;
				if (i == buf.length)
					break;
			}
		return len;
	}

	private void closeRes() {
		try {
			if (clisok != null)
				clisok.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
