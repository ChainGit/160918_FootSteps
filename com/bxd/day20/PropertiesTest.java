package com.bxd.day20;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) throws Exception {
		Properties myprop = new Properties();
		myprop.load(new FileReader("myprop.properties"));
		// int utimes = Integer.parseInt(myprop.getProperty("use.time", "0")) +
		// 1;
		// myprop.setProperty("use.time", String.valueOf(utimes));
		// if (utimes > 5)
		// throw new RuntimeException("time limits!");
		// System.out.println(myprop.getProperty("test.dir"));
		// String td = myprop.getProperty("test.dir");
		// myprop.list(new PrintWriter("myprop.properties"));

		String s = System.getProperty("test.dir");
		System.out.println(s);
		myprop.list(new PrintWriter(
				new BufferedOutputStream(new FileOutputStream(System.getProperty(s + "test.dir") + "myprop.txt")),
				true));

		/*
		 * System.out.println(myprop);
		 * 
		 * Properties myprop2 = new Properties(); myprop2.load(new
		 * FileInputStream("myprop.properties")); myprop2.setProperty("mykey",
		 * "xxxxxx"); myprop2.list(new PrintStream(System.out));
		 * myprop2.list(new PrintStream(td + File.separator + "myprop.txt"));
		 */
	}
}
