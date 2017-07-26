package com.bxd.day19;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class IOTest {

	public static void main(String[] args) {
		try {
			test1();
		} catch (Exception e) {
			e.printStackTrace();
		}

		test2();
		test3();

		try {
			test4();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			test5();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		deleteAll();
	}

	public static void test5() throws Exception {
		FileReader fr = new FileReader("demo2.txt");
		FileWriter fw = new FileWriter("demo2_1.txt");
		char[] chs = new char[1024];
		int p = -1;
		int i = 0;
		while ((p = fr.read()) != -1)
			chs[i++] = (char) p;
		fw.write(chs);
		System.out.println(chs);
		fr.close();
		fw.close();
	}

	private static void test4() throws Exception {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("demo1.txt");
		FileWriter fw = new FileWriter("demo1_1.txt");
		int p = -1;
		while ((p = fr.read()) != -1) {
			fw.write((char) p);
			System.out.print((char) p);
		}
		System.out.println();
		fr.close();
		fw.close();
	}

	public static void test3() {
		try (FileWriter fw = new FileWriter("demo3.txt")) {
			fw.write("TEST");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void test2() {
		// TODO Auto-generated method stub
		FileWriter fw = null;
		try {
			fw = new FileWriter("demo2.txt");
			fw.write("TEST");
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

	private static void test1() throws IOException {
		FileWriter fw = new FileWriter("demo1.txt");

		fw.write("TEST");
		fw.flush();
		fw.close();
	}

	public static void deleteAll() {

		try {
			System.out.println("cleaning..");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File f = new File(System.getProperty("user.dir"));
		String[] str = f.list();
		for (int i = 1; i < str.length; i++) {
			if (!(str[i].contains("demo") && str[i].contains(".txt")))
				continue;
			File fp = new File(str[i]);
			if (fp.exists() && (!fp.isDirectory()))
				fp.delete();

			try {
				System.out.println("file " + str[i] + " has been deleted!");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("cleaned!");
	}
}
