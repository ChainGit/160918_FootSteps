package com.bxd.day19;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class StreamTest2 {

	public static void main(String[] args) throws Exception {
		test1();
		test2();
		test3();
		test4();
		test5();
	}

	public static void test5() {
		Properties prop = System.getProperties();
		try {
			prop.list(new PrintStream("sysinfo.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void test4() {
		try {
			System.setErr(new PrintStream("err.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			int a = 1;
			int b = 0;
			System.out.println(a / b);
		} catch (Exception e) {
			System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
		}
	}

	public static void test3() throws Exception {
		System.setIn(new FileInputStream("src.txt"));
		System.setOut(new PrintStream("dst2.txt"));

		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = null;
		while ((line = bufr.readLine()) != null) {
			bufw.write(line);
			bufw.newLine();
		}
		bufw.flush();
		bufr.close();
		bufw.close();
	}

	public static void test2() throws Exception {
		BufferedInputStream bufis = new BufferedInputStream(new FileInputStream("src.jpg"));
		BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream("dst.jpg"));
		byte[] data = new byte[1024];
		while (bufis.read(data) != -1) {
			bufos.write(data);
		}
		bufos.flush();
		bufis.close();
		bufos.close();
	}

	public static void test1() {
		BufferedReader bufr = null;
		BufferedWriter bufw = null;
		try {
			bufr = new BufferedReader(new InputStreamReader(new FileInputStream("src.txt"), "GBK"));
			bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("dst.txt"), "UTF-8"));
			String line = null;
			while ((line = bufr.readLine()) != null) {
				bufw.write(line);
				bufw.newLine();
				bufw.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (bufr != null)
					bufr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (bufw != null)
					bufw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
