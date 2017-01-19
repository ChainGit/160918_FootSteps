package com.bxd.day02;

public class Convert {

	private static final char[] table = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
			'D', 'E', 'F' };

	private static char[] convert(int num, int base, int offset) {
		char[] res = new char[32];
		int i = res.length - 1;

		for (int j = 0; j < res.length; j++) {
			res[j] = '0';
		}

		while (num != 0) {
			int tp = num & base;
			res[i] = table[tp];
			i--;
			num >>>= offset;
		}

		return res;
	}

	public static String toBinaryString(int num) {
		return new String(convert(num, 1, 1));
	}

	public static String toOctalString(int num) {
		return new String(convert(num, 7, 3));
	}

	public static String toHexadecimalString(int num) {
		return new String(convert(num, 15, 4));
	}

}
