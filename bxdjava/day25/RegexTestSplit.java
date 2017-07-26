package com.bxd.day25;

/**
 * ������ʽ���ŵ�: �򻯲��� <br>
 * ������ʽ��ȱ��: �����Ķ�
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

	// ������ʽ��ĸ���,����ı��,��1��ʼ����С��,0�����������ʽ
	private static void test5() {
		String test = "assa\\dsfffl24\\sd\\\\s334\\fsdfl";
		String regex = "(.)\\1+";
		String[] arrs = test.split(regex);
		for (String s : arrs)
			System.out.println(s);
	}
}
