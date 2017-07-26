package com.bxd.day19;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOTest2 {

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();

		IOTest.deleteAll();
	}

	private static void test4() {
		// TODO Auto-generated method stub
		try {
			MyBufferedReader bufr = new MyBufferedReader(new FileReader("demo4.txt"));
			BufferedWriter bufw = new BufferedWriter(new FileWriter("demo4_2.txt"));
			String line = null;
			while ((line = bufr.myReadLine()) != null) {
				bufw.write(line);
				bufw.newLine();
			}
			bufw.flush();
			bufw.close();
			bufr.myClose();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void test3() {
		// TODO Auto-generated method stub
		try {
			BufferedReader bufr = new BufferedReader(new FileReader("demo4.txt"));
			BufferedWriter bufw = new BufferedWriter(new FileWriter("demo4_1.txt"));
			String line = null;
			while ((line = bufr.readLine()) != null) {
				bufw.write(line);
				bufw.newLine();
			}
			bufw.flush();
			bufw.close();
			bufr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void test2() {
		// TODO Auto-generated method stub
		FileReader fr = null;
		try {
			fr = new FileReader("demo4.txt");
			BufferedReader bufr = new BufferedReader(fr);
			String str = null;
			while ((str = bufr.readLine()) != null) {
				System.out.println(str);
			}
			bufr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void test1() {
		// TODO Auto-generated method stub
		FileWriter fw = null;
		try {
			fw = new FileWriter("demo4.txt");
			BufferedWriter bufw = new BufferedWriter(fw);
			bufw.write("TESTTESTTESTTESTTESTTESTTEST");
			bufw.newLine();
			bufw.write("WHATWHATWHATWHATWHATWHATWHAT");
			bufw.flush();
			bufw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null)
					fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}

// 装饰设计模式
class MyBufferedReader {

	private FileReader fr = null;

	public MyBufferedReader(FileReader fr) {
		this.fr = fr;
	}

	public String myReadLine() throws IOException {
		StringBuilder sb = new StringBuilder();

		int p = -1;
		while ((p = fr.read()) != -1) {
			if (p == '\r')
				continue;
			else if (p == '\n')
				return sb.toString();
			sb.append((char) p);
		}

		if (sb.length() > 0)
			return sb.toString();

		return null;
	}

	public void myClose() throws IOException {
		if (fr != null)
			fr.close();
	}
}