package com.bxd.day25;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式判断是否是数字,不能以0开始,数字长度达到10000000位
 * 
 * @author Chain
 *
 */
public class RegexTestMatch {

	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		String num = makenum();
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));

		begin = System.currentTimeMillis();
		System.out.println(checknum1(num));
		end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));

		begin = System.currentTimeMillis();
		System.out.println(checknum2(num));
		end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));

		begin = System.currentTimeMillis();
		System.out.println(checknum3(num));
		end = System.currentTimeMillis();
		System.out.println("time: " + (end - begin));
	}

	private static String makenum() {
		StringBuilder sb = new StringBuilder();
		// sb.append(0);
		int times = 10000000;
		Random rand = new Random(times);
		while (times-- > 0)
			sb.append(rand.nextInt(10));
		System.out.println("num length: " + sb.length());
		return sb.toString();
	}

	// 使用String方法判断
	private static boolean checknum1(String num) {
		if (num.startsWith("0"))
			return false;
		char[] buf = num.toCharArray();
		int i = 0;
		for (; i < buf.length; i++)
			if (buf[i] < '0' && buf[i] > '9')
				break;
		if (i == buf.length)
			return true;
		return false;
	}

	// 使用Integer转换函数
	private static boolean checknum2(String num) {
		if (num.startsWith("0"))
			return false;
		int pos = 0;
		int lens = num.length();
		while (true) {
			String tmp = null;
			if (pos + 9 < lens) {
				tmp = num.substring(pos, pos + 9);
				pos += 9;
			} else if (pos < lens) {
				tmp = num.substring(pos, lens);
				pos = lens;
				break;
			}
			try {
				Integer.parseInt(tmp);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		if (pos == lens)
			return true;
		return false;
	}

	// 使用正则表达式
	private static boolean checknum3(String num) {
		// String regex = "^[1-9][0-9]*";
		String regex = "^[1-9]\\d*";
		Pattern ptn = Pattern.compile(regex);
		Matcher mtr = ptn.matcher(num);
		return mtr.matches();
	}
}
