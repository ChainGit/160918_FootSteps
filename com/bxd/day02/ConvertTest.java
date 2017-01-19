package com.bxd.day02;

public class ConvertTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a = (int) (Math.random() * (127 - (-127)) + (-127));

		System.out.println("Decimal:     " + a);
		System.out.println("Binary:      " + Convert.toBinaryString(a));
		System.out.println("Octal:       " + Convert.toOctalString(a));
		System.out.println("Hexadecimal: " + Convert.toHexadecimalString(a));
	}

}
