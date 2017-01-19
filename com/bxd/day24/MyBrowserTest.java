package com.bxd.day24;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * 与MyServerTest配合使用,只是简单的打印出服务器回应的数据
 * 
 * @author Chain
 * 
 */
public class MyBrowserTest {

	public static void main(String[] args) {
		System.out.print("请输入访问的IP和端口: ");
		Scanner in = new Scanner(System.in);
		String stmp = in.next();
		in.close();
		if (!stmp.contains(":"))
			return;

		String[] pas = stmp.split(":");
		if (pas.length < 2)
			return;

		int portc = Integer.parseInt(pas[1]);
		if (portc < 0 || portc > 65535)
			return;

		try {
			InetAddress inetaddr = InetAddress.getByName(pas[0]);
			String serip = inetaddr.getHostAddress();
			System.out.println("访问的主机IP和端口为: " + serip + ":" + portc);
			System.out.println();

			Socket sok = new Socket(serip, portc);

			PrintWriter pw = new PrintWriter(sok.getOutputStream());
			BufferedInputStream bufis = new BufferedInputStream(sok.getInputStream());

			pw.println("GET / HTTP/1.1");
			pw.println("Host: " + stmp);
			pw.println("Connection: keep-alive");
			pw.println("Cache-Control: max-age=0");
			pw.println("Upgrade-Insecure-Requests: 1");
			pw.println(
					"User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
			pw.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			pw.println("Accept-Encoding: gzip, deflate, sdch");
			pw.println("Accept-Language: zh-CN,zh;q=0.8");
			pw.println();
			pw.flush();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int p = -1;
			while ((p = bufis.read()) != -1)
				baos.write(p);
			System.out.println(baos.toString());

			bufis.close();
			pw.close();
			sok.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
