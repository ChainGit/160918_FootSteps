package com.bxd.day21;

import java.io.File;
import java.io.RandomAccessFile;

//����C���Գ����е��ļ�����(ָ��)����IO���еĵ�����Ա
//getFilePointer��ȡָ��λ�� 
//seek�ı�ָ���λ��
//�ײ����ļ�������
public class RandomAccessFileTest {

	private static final String PATH = "E:\\Special\\Tests\\";

	public static void main(String[] args) throws Exception {
		File f = new File(PATH + "data.txt");
		if (f.exists() && f.isFile())
			f.delete();

		test1();
		test2();
	}

	public static void test2() throws Exception {
		// TODO Auto-generated method stub
		RandomAccessFile raf = new RandomAccessFile(PATH + "data.txt", "r");
		byte[] buf = new byte[4];
		raf.read(buf);
		System.out.println(new String(buf, 0, buf.length));// 4 B
		raf.read(buf);
		System.out.println(new String(buf, 0, buf.length));// 4 B
		raf.seek(9);
		System.out.println((char) raf.readInt());// 4 B �� 9+4=13
		System.out.println(raf.getFilePointer());// 13
		raf.skipBytes(1);// 14
		System.out.println((char) raf.read());// ��1BתΪint����
		raf.seek(8);
		System.out.println((char) raf.read());// ��1BתΪint����
		raf.close();
	}

	public static void test1() throws Exception {
		// TODO Auto-generated method stub
		RandomAccessFile raf = new RandomAccessFile(PATH + "data.txt", "rw");
		raf.write("lisi".getBytes());// 4 B
		raf.write("����".getBytes());// 4 B
		raf.write(97);// 1 B
		raf.writeInt(98);// 4 B
		raf.write(100);
		raf.write(99);
		raf.close();
	}
}
