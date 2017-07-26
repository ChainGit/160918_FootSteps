package com.bxd.day20;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class SequenceTest {

	private static final String PATH = "E:\\Special\\Tests";

	public static void main(String[] args) throws Exception {
		// test1();
		// test2();
		test3();
	}

	public static void test3() throws Exception {
		ArrayList<String> lst = new ArrayList<>();
		File tf = new File(PATH);
		for (File f : tf.listFiles()) {
			if (f.isFile() && f.getName().endsWith(".part"))
				lst.add(f.getAbsolutePath());
		}
		Collections.sort(lst);

		System.out.println(lst);

		ArrayList<FileInputStream> lstf = new ArrayList<>();
		for (String s : lst) {
			lstf.add(new FileInputStream(s));
		}

		Iterator<FileInputStream> it = lstf.iterator();

		Enumeration<FileInputStream> en = new Enumeration<FileInputStream>() {
			public boolean hasMoreElements() {
				return it.hasNext();
			}

			public FileInputStream nextElement() {
				return it.next();
			}
		};

		SequenceInputStream seqis = new SequenceInputStream(en);

		BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream(PATH + "\\2.jpg"));

		int len = -1;
		byte[] buf = new byte[1024];
		while ((len = seqis.read(buf)) != -1)
			bufos.write(buf, 0, len);

		bufos.close();
		seqis.close();

	}

	public static void test2() throws Exception {
		FileInputStream fis = new FileInputStream(PATH + "\\1.jpg");
		FileOutputStream fos = null;

		byte[] buf = new byte[1024 * 500];
		for (int len = -1, i = 0; (len = fis.read(buf)) != -1; i++) {
			fos = new FileOutputStream(PATH + "\\" + i + ".part");
			fos.write(buf, 0, len);
			fos.flush();
		}

		if (fos != null)
			fos.close();
		fis.close();
	}

	public static void test1() throws FileNotFoundException, IOException {
		Vector<FileInputStream> vct = new Vector<>();
		vct.add(new FileInputStream(PATH + "\\1.txt"));
		vct.add(new FileInputStream(PATH + "\\2.txt"));
		vct.add(new FileInputStream(PATH + "\\3.txt"));

		Enumeration<FileInputStream> en = vct.elements();
		SequenceInputStream seqis = new SequenceInputStream(en);

		BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream(PATH + "\\4.txt"));

		byte[] buf = new byte[1024];
		int p = -1;
		while ((p = seqis.read(buf)) != -1)
			bufos.write(buf, 0, p);

		seqis.close();
		bufos.close();
	}
}
