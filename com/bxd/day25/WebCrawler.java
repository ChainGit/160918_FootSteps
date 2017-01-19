package com.bxd.day25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

	public static void main(String[] args) throws Exception {

		// 使用day24里的MyServerTest
		URL url = new URL("http://192.168.56.1:10101");
		URLConnection urlcon = url.openConnection();
		urlcon.connect();

		BufferedReader bufr = new BufferedReader(new InputStreamReader(urlcon.getInputStream(), "GBK"));

		String regex = "[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+)+";
		Pattern pa = Pattern.compile(regex);

		String buf = null;
		ArrayList<String> lst = new ArrayList<>();
		while ((buf = bufr.readLine()) != null) {
			Matcher ma = pa.matcher(buf);
			while (ma.find())
				lst.add(ma.group());
		}
		bufr.close();

		for (String s : lst)
			System.out.println(s);
	}
}
