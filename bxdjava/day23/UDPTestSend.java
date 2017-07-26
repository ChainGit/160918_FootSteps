package com.bxd.day23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP发送端,循环输入并发送数据,输入over结束
 * 
 * @author Chain
 *
 */
public class UDPTestSend {

	private static final int port = 10101;

	public static void main(String[] args) throws Exception {
		DatagramSocket dgsok = new DatagramSocket();
		while (true) {
			BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
			String input = null;
			input = bufr.readLine();
			if (input == null || input.length() < 1)
				continue;
			byte[] buf = input.getBytes();
			DatagramPacket dgpak = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), port);
			System.out.println("send data: " + new String(buf));
			dgsok.send(dgpak);
			if (input.equals("over"))
				break;
		}
		dgsok.close();
	}
}
