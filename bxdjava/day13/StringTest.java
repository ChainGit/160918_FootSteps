package com.bxd.day13;

import java.util.Locale;

public class StringTest {

	public static void main(String[] args) {
		String a = "123";
		String b = new String("123");
		String c = a;

		System.out.println(a == b);
		System.out.println(a.equals(b));
		System.out.println(a == c);
		System.out.println(a.length());

		String s = "abcdef012345";

		System.out.println(s.charAt(10));
		// StringIndexOutOFBoundsException
		// System.out.println(s.charAt(20));

		String d = "1234";
		String e = "125";
		System.out.println(a.compareTo(b));
		System.out.println(a.compareTo(d));
		System.out.println(a.compareTo(e));

		String f = "ABC";
		String g = "ABc";

		System.out.println(f.compareToIgnoreCase(g));
		System.out.println(f.equalsIgnoreCase(g));
		System.out.println(f.concat(g) + " " + f + " " + g);
		System.out.println(d.contains(a));

		char c1[] = { 'a', 'b', 'c' };

		System.out.println(String.copyValueOf(c1));
		System.out.println(String.copyValueOf(c1, 1, 2));

		System.out.println(f.endsWith("5"));

		byte b1[] = new byte[f.length()];
		b1 = f.getBytes();
		System.out.println(b1);
		for (byte x : b1) {
			System.out.println(x + " " + (char) x);
		}

		char c2[] = new char[f.length()];
		f.getChars(0, f.length(), c2, 0);
		System.out.println(c2[1]);

		System.out.println(f.hashCode());

		System.out.println(f.indexOf("B"));
		System.out.println(f.isEmpty());
		System.out.println(f.lastIndexOf("B"));
		System.out.println(f.matches("[a-zA-Z]+"));
		System.out.println(f.replace('B', 'D'));
		System.out.println(f.substring(1, f.length()));
		System.out.println(String.format(Locale.getDefault(),"%d%.2f",1,0.343));

		char c3[] = null;
		c3 = f.toCharArray();
		System.out.println(c3[1]);
		
		
		System.out.println(g.toLowerCase()+g.toUpperCase());
		System.out.println(new String("Ò»¶þÈý").toUpperCase(Locale.CHINA));
		
		String h = "123#12.12/23";
		System.out.println(h.split("\\d"));
		
		String i = "  asas  s sdsc    ";
		System.out.println(i.trim()+" "+i.length()+" "+i.trim().length());
		
		System.out.println(String.valueOf(1));
		
	}

}
