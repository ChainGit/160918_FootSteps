package com.bxd.day19;

import java.io.File;

public class FileTest2 {

	private static final String PATH = "E:" + File.separator + "Special" + File.separator + "Tests";

	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		File tf = new File(PATH);

		System.out.println("path: " + tf.getAbsolutePath());
		System.out.println("parents: " + tf.getParent());
		System.out.println("name: " + tf.getName());

		System.out.println();
		System.out.println("list all files: ");
		listFiles(tf, 0);

		System.out.println();
		System.out.println("delete all files:");
		deleteFiles(tf);
	}

	// ɾ�����е��ļ����ļ��У����ɾ���ļ���(��ʱ�Ѿ��ǿ��ļ���)
	private static void deleteFiles(File tf) {
		// TODO Auto-generated method stub
		for (File f : tf.listFiles()) {
			if (f.isDirectory())
				deleteFiles(f);
			else
				System.out.println("file: " + f.getName() + " : " + f.delete());
		}

		if (!tf.getName().equals("Tests"))// ��ɾ�����ļ���
			System.out.println("dir: " + tf.getName() + " : " + tf.delete());
	}

	public static String getLevel(int lv) {
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < lv; x++)
			sb.append(": ");
		sb.append("|-");
		return sb.toString();
	}

	// ��ӡ�������ļ����ļ��У�����ʾ�ļ���
	private static void listFiles(File tf, int lv) {
		// TODO Auto-generated method stub
		System.out.print(getLevel(lv) + tf.getName());
		if (tf.isDirectory())
			System.out.println("*");
		else
			System.out.println();
		lv++;
		for (File f : tf.listFiles()) {
			if (f.isDirectory())
				listFiles(f, lv);
			else
				System.out.println(getLevel(lv) + f.getName());
		}
	}
}
