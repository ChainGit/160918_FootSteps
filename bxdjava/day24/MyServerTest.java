package com.bxd.day24;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * 简单的Java文件服务器,可以用IE/Chrome等浏览器访问
 * 
 * @author Chain
 *
 */
public class MyServerTest {

	public static void main(String[] args) {
		int port = 10101;
		ServerSocket sersok = null;
		while (port < 65535)
			try {
				sersok = new ServerSocket(port++);
				if (sersok != null)
					break;
			} catch (Exception e) {
				continue;
			}

		try {
			System.out.println("服务器IP和端口为: " + sersok.getInetAddress() + ":" + sersok.getLocalPort());
		} catch (Exception e) {
			e.printStackTrace();
		}

		new Thread(new ServerThread(sersok)).start();
	}
}

class ServerThread implements Runnable {

	private ServerSocket sersok;

	public ServerThread(ServerSocket ss) {
		if (ss != null)
			this.sersok = ss;
	}

	@Override
	public void run() {
		if (sersok == null)
			return;
		try {
			while (true) {
				Socket sok = sersok.accept();
				new Thread(new ClientThread(sok)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeRes();
		}
	}

	private void closeRes() {
		try {
			if (sersok != null)
				sersok.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ClientThread implements Runnable {

	public static final String file = "E:\\Special\\Tests\\data.html";
	public static int times = 0;

	private Socket sok;

	public ClientThread(Socket s) {
		if (s != null)
			this.sok = s;
	}

	@Override
	public void run() {
		try {
			System.out.println("检测到第" + (times++) + "次连接: " + sok.getInetAddress() + ":" + sok.getPort());

			BufferedInputStream bufisf = new BufferedInputStream(new FileInputStream(file));

			BufferedInputStream bufis = new BufferedInputStream(sok.getInputStream());
			byte[] buf = new byte[1024 * 64];
			System.out.println();

			bufis.read(buf);
			System.out.println(new String(buf, 0, getActualLength(buf)));

			PrintWriter pw = new PrintWriter(sok.getOutputStream(), true);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int p = -1;
			while ((p = bufisf.read()) != -1)
				baos.write(p);
			bufisf.close();
			pw.write("HTTP/1.1 200 OK\r\nServer: MyServerTest\r\nContent-length: " + baos.size()
					+ "\r\nContent-type: text/html\r\n\r\n");// In Windows
			pw.write(baos.toString());
			pw.flush();
			pw.close();
			sok.close();

			System.out.println("成功断开连接.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (sok != null)
					sok.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private int getActualLength(byte[] buf) {
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

}