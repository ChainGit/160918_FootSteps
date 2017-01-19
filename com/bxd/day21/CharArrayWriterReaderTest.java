package com.bxd.day21;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

//比ByteArrayStream，为char字符数组，不用转为byte，使用字符流
public class CharArrayWriterReaderTest {

	public static void main(String[] args) throws Exception {
		test1();
	}

	public static void test1() throws Exception {
		// TODO Auto-generated method stub
		// 创建一个字符数组流，内部封装了一个char[] buf，方便进行写的流操作
		CharArrayWriter caw = new CharArrayWriter();
		caw.append('A');
		caw.write(97);
		caw.write(new char[] { 'X', 'x' });
		caw.write("PPP");
		caw.writeTo(new PrintWriter(new PrintStream(System.out), true));

		caw.close();// 空方法...

		char[] b1 = caw.toCharArray();
		System.out.println(new String(b1));

		// 传入一个char[] buf进一个字符数组流，方便进行读的流操作
		CharArrayReader car = new CharArrayReader(b1);
		int d1 = car.read();
		System.out.println(d1);
		System.out.println((char) d1);
		char[] b2 = new char[10];
		car.read(b2);
		System.out.println(new String(b2));

	}

}
