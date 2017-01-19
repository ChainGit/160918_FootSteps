package com.bxd.day25;

public class RegexTestReplace {

	public static void main(String[] args) {
		System.out.println(1);
		test1();
		System.out.println(1);
		test2();
	}

	private static void test1() {
		String test = "asas123214dssfs;'lsad213";
		// String regex = "\\d";
		String regex = "[0-9]";
		String remet = "#";
		System.out.println(test.replaceAll(regex, remet));
	}

	private static void test2() {
		String test = "asas1233323214dssfs;'lsad213";
		String regex = "(.)\\1+";
		String remet = "$1";
		// String remet = "#";
		System.out.println(test.replaceAll(regex, remet));
	}
}
