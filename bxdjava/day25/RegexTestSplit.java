package com.bxd.day25;

/**
 * 正则表达式的优点: 简化操作 <br>
 * 正则表达式的缺点: 不易阅读
 * 
 * @author Chain
 *
 */
public class RegexTestSplit {

	public static void main(String[] args) {
		System.err.println(1);
		test1();
		System.out.println(2);
		test2();
		System.out.println(3);
		test3();
		System.out.println(4);
		test4();
		System.out.println(5);
		test5();
	}

	private static void test1() {
		String test = "assa,;dsfl243;sd;54,s3423d;fs;dfl";
		String regex = ";";
		String[] arrs = test.split(regex);
		for (String s : arrs)
			System.out.println(s);
	}

	private static void test2() {
		String test = "assa,;    dsfl243      sd;54    s3423d    fsdfl";
		String regex = "[ ]+";
		String[] arrs = test.split(regex);
		for (String s : arrs)
			System.out.println(s);
	}

	private static void test3() {
		String test = "assa.dsfl24.sd;s34.fsdfl";
		// String regex = "[.]";
		String regex = "\\.";
		String[] arrs = test.split(regex);
		for (String s : arrs)
			System.out.println(s);
	}

	private static void test4() {
		String test = "assa\\dsfl24\\sd\\\\s34\\fsdfl";
		// String regex = "[\\\\]+";
		String regex = "\\\\+";
		String[] arrs = test.split(regex);
		for (String s : arrs)
			System.out.println(s);
	}

	// 正则表达式组的概念,有组的标号,从1开始代表小组,0代表整个表达式
	private static void test5() {
		String test = "assa\\dsfffl24\\sd\\\\s334\\fsdfl";
		String regex = "(.)\\1+";
		String[] arrs = test.split(regex);
		for (String s : arrs)
			System.out.println(s);
	}
}
