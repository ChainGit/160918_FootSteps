package com.bxd.day23;

import java.net.InetAddress;

/**
 * 获取本机的IP和主机名<br>
 * 获取远端主机如百度的IP和主机名
 * 
 * @author Chain
 *
 */
public class NetTest {

	public static void main(String[] args) throws Exception {
		InetAddress i = InetAddress.getLocalHost();
		System.out.println(i.toString());
		System.out.println(i.getHostName());
		System.out.println(i.getHostAddress());
		System.out.println(i.getCanonicalHostName());

		InetAddress i2 = InetAddress.getByName("www.baidu.com");
		System.out.println(i2.toString());
		System.out.println(i2.getHostName());
		System.out.println(i2.getHostAddress());
	}
}
