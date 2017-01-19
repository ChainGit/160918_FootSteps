package com.bxd.day24;

import java.io.IOException;
import java.net.URL;

public class URLTest {

	public static void main(String[] args) {
		URL url = null;
		try {
			url = new URL("https://www.baidu.com");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (url == null)
			return;

		System.out.println(url.getProtocol());
		System.out.println(url.getFile());
		System.out.println(url.getHost());
		System.out.println(url.getPort());
		System.out.println(url.getQuery());
		System.out.println(url.getPath());
		System.out.println(url.getUserInfo());
		System.out.println(url.getAuthority());

		try {
			System.out.println(url.getContent().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
