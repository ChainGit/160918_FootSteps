package com.bxd.day21;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class CharsetTest {

	public static void main(String[] args) throws Exception {
		test1();
		System.out.println();
		test2();
		System.out.println();
		test3();
		System.out.println();
		test4();
		System.out.println();
		test5();
		System.out.println();
	}

	public static void test5() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String s = "联通";
		System.out.println(s);// gbk
		byte[] b1 = s.getBytes();
		System.out.println(Arrays.toString(s.getBytes()));
		for (byte b : b1)
			System.out.println(Integer.toBinaryString(b & 0xff));

		System.out.println(new String(s.getBytes(), "utf-8"));

	}

	public static void test4() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String s1 = "你好";
		byte[] b1 = s1.getBytes("utf-8");
		System.out.println(Arrays.toString(b1));
		String s2 = new String(b1, "gbk");
		System.out.println(s2);
		byte[] b2 = s2.getBytes("utf-8");
		System.out.println(Arrays.toString(b1));
		System.out.println(new String(b2));
		System.out.println(new String(b1));// 默认是GBK
		System.out.println(new String(b1, "utf-8"));
	}

	public static void test3() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String s = "你好";
		byte[] b1 = s.getBytes("GBK");
		System.out.println(Arrays.toString(b1));
		String sp = new String(b1, "utf-8");
		System.out.println(sp);
		byte[] bp = sp.getBytes("gbk");
		System.out.println(Arrays.toString(b1));
		System.out.println(new String(bp));
	}

	public static void test2() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String s = "你好";
		byte[] b1 = s.getBytes("UTF-8");
		System.out.println(Arrays.toString(b1));
		System.out.println(new String(b1, "utf-8"));
	}

	public static void test1() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String s = "你好";
		byte[] b1 = s.getBytes("GBK");
		System.out.println(Arrays.toString(b1));
		System.out.println(new String(b1, "GBK"));
	}
}
