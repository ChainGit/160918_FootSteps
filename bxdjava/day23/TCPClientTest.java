package com.bxd.day23;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * TCP�ͻ���,���ӷ�����,��������������ݲ����շ��������ص�����
 * 
 * @author Chain
 *
 */
public class TCPClientTest {

	private static final int port = 10101;

	public static void main(String[] args) throws Exception {
		Socket sok = new Socket("192.168.56.1", port);
		System.out.println("������ϢΪ: " + sok.getLocalAddress() + ":" + sok.getLocalPort());
		System.out.println("���ӷ����� " + sok.getInetAddress() + ":" + sok.getPort() + " �ɹ�.");

		OutputStream outs = sok.getOutputStream();
		InputStream ins = sok.getInputStream();

		try {
			int times = 30;
			while (times-- > 0) {
				String tmp = "tcp hello " + (int) (Math.random() * 10) + " data";
				System.out.println("send: " + tmp);
				outs.write(tmp.getBytes());
				outs.flush();

				byte[] buf = new byte[64 * 1024];
				ins.read(buf);
				System.out.println("get: " + new String(buf, 0, getLength(buf)));

				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sok.close();
		}
	}

	private static int getLength(byte[] buf) {
		int len = 0;
		for (; len < buf.length; len++)
			if (buf[len] == 0)
				break;
		return len;
	}
}
