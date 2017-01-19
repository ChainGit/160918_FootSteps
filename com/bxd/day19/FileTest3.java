package com.bxd.day19;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

public class FileTest3 {

	private static final String PATH = "E:" + File.separator + "Special" + File.separator + "Tests";

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		// TODO Auto-generated method stub
		File tf = new File(System.getProperty("user.dir"));
		List<File> lst = new ArrayList<>();
		file2list(tf, lst);

		for (File f : lst) {
			System.out.println(f.getAbsolutePath());
		}
		System.out.println("total java files: " + lst.size());

		try {
			BufferedWriter bufw = new BufferedWriter(new FileWriter(PATH + File.separator + "myjava.txt"));
			for (int i = 0; i < lst.size(); i++) {
				bufw.write(lst.get(i).getAbsolutePath());
				bufw.newLine();
			}
			bufw.flush();
			bufw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void file2list(File dir, List<File> lst) {
		// lst.add(dir);
		for (File f : dir.listFiles()) {
			if (f.isDirectory())
				file2list(f, lst);
			else if (f.getName().endsWith(".java"))
				lst.add(f);
		}
	}
}
