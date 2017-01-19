package com.bxd.day25;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

	public static void main(String[] args) {
		test1();
		System.out.println();
		test2();
		System.out.println();
		test3();
	}

	private static void test3() {
		String mail = "abc@123.com";
		// String mail = "a;bc@123com";
		// String mail = "a;bc@123.com";
		String regex = "[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+)+";
		// String regex = "\\w+@\\w+(\\.\\w+)+";
		System.out.println(mail.matches(regex));
	}

	private static void test2() {
		String ipn = "192.168.10.23 12121.12.23 123.34.12.32     12.23.34.12   0.23.12.0 23.as1.12.      12.32.  12..32.12a  12  12.23.123.34";
		System.out.println(ipn);

		System.out.println();
		String regex = "[ ]+";
		String[] ips = ipn.split(regex);
		for (String s : ips)
			System.out.println(s);

		regex = "\\b([0-9]{1,3}[.]){3}[0-9]{1,3}\\b";
		ArrayList<String> ip2 = new ArrayList<>();
		for (String s : ips) {
			Pattern pa = Pattern.compile(regex);
			Matcher ma = pa.matcher(s);
			if (ma.matches())
				ip2.add(s);
		}

		System.out.println();
		for (String s : ip2)
			System.out.println(s);

		System.out.println();
		regex = "(\\d+)";
		for (int i = 0; i < ip2.size(); i++) {
			ip2.set(i, ip2.get(i).replaceAll(regex, "00$1"));
			System.out.println(ip2.get(i));
		}

		System.out.println();
		regex = "0*(\\d{3})";
		TreeSet<String> set = new TreeSet<>();
		for (String s : ip2) {
			s = s.replaceAll(regex, "$1");
			set.add(s);
			System.out.println(s);
		}

		System.out.println();
		for (String s : set)
			System.out.println(s);

		System.out.println();
		ArrayList<String> ip3 = new ArrayList<>();
		regex = "0*([0-9])";
		for (String s : set) {
			s = s.replaceAll(regex, "$1");
			ip3.add(s);
			System.out.println(s);
		}

		System.out.println();
		for (String s : ip3)
			System.out.println(s);

		// return ip3;
	}

	private static void test1() {
		String test = "我我...爱...爱爱......我我我....的....祖国国";
		System.out.println(test);
		String regex = "\\.+";
		String tmp = test.replaceAll(regex, "");
		System.out.println(tmp);
		regex = "(.)\\1+";
		System.out.println(tmp.replaceAll(regex, "$1"));
	}
}
