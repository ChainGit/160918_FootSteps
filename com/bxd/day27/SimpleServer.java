package com.bxd.day27;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(9090);
		Socket sk = ss.accept();

		System.out.println(sk.getPort());

		InputStream ins = sk.getInputStream();

		int t = -1;
		while ((t = ins.read()) != -1)
			System.out.print((char) t);
		System.out.println();

		PrintWriter prt = new PrintWriter(sk.getOutputStream());
		prt.println("<html><body>OK</body><html>");

		sk.close();
		ss.close();

	}

}
