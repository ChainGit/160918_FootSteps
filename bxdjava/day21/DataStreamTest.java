package com.bxd.day21;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class DataStreamTest {

	private static final String PATH = "E:\\Special\\Tests\\";

	public static void main(String[] args) throws Exception {
		test1();
		test2();
		test3();
	}

	public static void test3() throws Exception {
		// TODO Auto-generated method stub
		// 总共3*2=6B
		OutputStreamWriter fos = new OutputStreamWriter(new FileOutputStream(PATH + "data3.txt"), "UTF-8");
		fos.write("你好");
		fos.close();

		// 总共2*2=4B
		OutputStreamWriter fos2 = new OutputStreamWriter(new FileOutputStream(PATH + "data4.txt"), "GBK");
		fos2.write("你好");
		fos2.close();
	}

	public static void test2() throws Exception {
		// TODO Auto-generated method stub
		DataInputStream datais = new DataInputStream(new FileInputStream(PATH + "data2.txt"));

		System.out.println(datais.readUTF());// 4x2B
		System.out.println(datais.readInt());// 4B
		System.out.println(datais.read());// 1B
		System.out.println(datais.readDouble());// 8B

		datais.close();
	}

	public static void test1() throws Exception {
		// TODO Auto-generated method stub
		DataOutputStream dataos = new DataOutputStream(new FileOutputStream(PATH + "data2.txt"));

		dataos.writeUTF("你好");// 修改后的UTF-8，4x2B
		dataos.writeInt(120);// 4B
		dataos.write(97);// 1B
		dataos.writeDouble(192.12);// 8B
		// 总共21B

		dataos.close();
	}
}
