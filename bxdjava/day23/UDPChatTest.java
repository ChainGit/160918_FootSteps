package com.bxd.day23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP聊天程序<br>
 * 两个UDP套接字分两个进程,用于处理发送和接收,出现over结束
 * 
 * @author Chain
 *
 */
public class UDPChatTest {

	public static void main(String[] args) throws Exception {
		DatagramSocket soksend = new DatagramSocket();
		DatagramSocket sokget = new DatagramSocket();
		InetAddress inetaddr = InetAddress.getLocalHost();

		System.out.println("本机发送数据的IP和端口为: " + inetaddr.getHostAddress() + ":" + soksend.getLocalPort());
		System.out.println("本机接收数据的IP和端口为: " + inetaddr.getHostAddress() + ":" + sokget.getLocalPort());

		System.out.print("请输入对方接收数据的IP和端口: ");
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		String tmp = bufr.readLine();
		// bufr.close();

		if (tmp.contains(":")) {
			String[] pas = tmp.split(":");

			new Thread(new SendData(soksend, pas[0], Integer.parseInt(pas[1]))).start();
			new Thread(new GetData(sokget)).start();
		}
	}
}

class SendData implements Runnable {

	private DatagramSocket dgsok;
	private String inetip;
	private int inetport;
	private BufferedReader bufr;

	public SendData(DatagramSocket s, String ip, int port) {
		this.dgsok = s;
		this.inetip = ip;
		this.inetport = port;
		this.bufr = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public void run() {
		try {
			System.out.println("连接 " + inetip + ":" + inetport + " 成功.");
			while (true) {
				if (bufr == null)
					continue;
				String input = null;
				System.out.print(dgsok.getLocalAddress() + ":" + dgsok.getLocalPort() + " > ");
				input = bufr.readLine();
				if (input == null || input.length() < 1)
					continue;
				byte[] buf = input.getBytes();
				DatagramPacket dgpak = new DatagramPacket(buf, buf.length, InetAddress.getByName(inetip), inetport);
				dgsok.send(dgpak);
				if (input.equals("over"))
					System.exit(0);
			}
		} catch (Exception e) {
			closeRes();
			e.printStackTrace();
		}
	}

	private void closeRes() {
		try {
			if (bufr != null)
				bufr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (dgsok != null)
				dgsok.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class GetData implements Runnable {

	private DatagramSocket dgsok;

	public GetData(DatagramSocket s) {
		this.dgsok = s;
	}

	@Override
	public void run() {
		try {
			while (true) {
				byte[] buf = new byte[1024 * 64];
				DatagramPacket dgpak = new DatagramPacket(buf, buf.length);
				dgsok.receive(dgpak);

				String data = new String(dgpak.getData(), 0, dgpak.getLength());
				if (data.equals("over"))
					System.exit(0);
				System.out.println(dgsok.getLocalAddress() + ":" + dgsok.getLocalPort() + " > " + data);
			}
		} catch (Exception e) {
			closeRes();
			e.printStackTrace();
		}
	}

	private void closeRes() {
		try {
			if (dgsok != null)
				dgsok.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
