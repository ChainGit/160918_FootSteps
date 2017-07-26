package com.bxd.day19;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

//流只能用来操作数据，不能操作文件(夹)和属性信息
//故Java的File类提供此类功能
//File类不会默认创建文件，而流操作会默认创建文件且默认覆盖
public class FileTest {

	public static void main(String[] args) {
		// test1();
		// test2();
		// test3();
		test4();
	}

	public static void test4() {
		// TODO Auto-generated method stub
		File f1 = new File("tst.txt");
		File f2 = new File("tst2.txt");

		if (f1.exists())
			f1.renameTo(f2);
		if (f2.exists())
			f2.renameTo(f1);

		File roots[] = File.listRoots();
		for (File r : roots)
			System.out.println(r);

		File f3 = new File(System.getProperty("user.dir"));
		for (String s : f3.list())
			System.out.println(s);
	}

	public static void test3() {
		File d1 = new File("dir1");
		System.out.println("file: " + d1.getName());
		System.out.println("path1: " + d1.getPath());
		System.out.println("path2: " + d1.getAbsolutePath());
		System.out.println("isDirectory: " + d1.isDirectory());
		System.out.println("isFile: " + d1.isFile());
		System.out.println("isHidden: " + d1.isHidden());
		System.out.println("isAbsolute: " + d1.isAbsolute());
		System.out.println("Parent: " + d1.getParent());
		System.out.println("LastModified: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d1.lastModified()));
		if (!d1.exists())
			d1.mkdir();

		File d2 = new File("dir1" + File.separator + "dir2" + File.separator + "dir3");
		if (!d2.exists())
			d2.mkdirs();

	}

	public static void test2() {
		// TODO Auto-generated method stub
		try {
			File f1 = new File("file1.txt");
			System.out.println("file: " + f1.getName());
			System.out.println("path: " + f1.getAbsolutePath());
			f1.deleteOnExit();
			System.out.println("create: " + f1.createNewFile());

			// File.createTempFile("test111", ".mytmp");

			File f2 = new File("file2.txt");
			System.out.println("file: " + f2.getName());
			System.out.println("path: " + f2.getAbsolutePath());
			if (!f2.exists())
				System.out.println("create: " + f2.createNewFile());
			else
				System.out.println("delete: " + f2.delete());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Thread.sleep(100);
			// System.out.println("Exited!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void test1() {
		// TODO Auto-generated method stub
		File f1 = new File("file1.txt");
		System.out.println(f1.toString());
		File f2 = new File("F:\\file2.txt");
		System.out.println(f2.toString());
		File f3 = new File("F:" + File.separator + "file3.txt");
		System.out.println(f3.toString());
		File f4 = new File("F:", "file4.txt");
		System.out.println(f4.toString());
	}
}
