package com.bxd.day23;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP接收端,循环接收数据并打印,接收到over结束
 * 
 * @author Chain
 *
 */
public class UDPTestReceive {

	private static final int port = 10101;

	public static void main(String[] args) throws Exception {
		DatagramSocket dgsok = new DatagramSocket(port);
		while (true) {
			byte[] buf = new byte[1024 * 64];
			DatagramPacket dgpak = new DatagramPacket(buf, buf.length);
			dgsok.receive(dgpak);

			String data = new String(dgpak.getData(), 0, dgpak.getLength());
			if (data.equals("over"))
				break;
			System.out.println(dgpak.getSocketAddress());
			System.out.println(data);
		}
		dgsok.close();
	}
}
