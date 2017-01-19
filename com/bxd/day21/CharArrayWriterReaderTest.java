package com.bxd.day21;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

//��ByteArrayStream��Ϊchar�ַ����飬����תΪbyte��ʹ���ַ���
public class CharArrayWriterReaderTest {

	public static void main(String[] args) throws Exception {
		test1();
	}

	public static void test1() throws Exception {
		// TODO Auto-generated method stub
		// ����һ���ַ����������ڲ���װ��һ��char[] buf���������д��������
		CharArrayWriter caw = new CharArrayWriter();
		caw.append('A');
		caw.write(97);
		caw.write(new char[] { 'X', 'x' });
		caw.write("PPP");
		caw.writeTo(new PrintWriter(new PrintStream(System.out), true));

		caw.close();// �շ���...

		char[] b1 = caw.toCharArray();
		System.out.println(new String(b1));

		// ����һ��char[] buf��һ���ַ���������������ж���������
		CharArrayReader car = new CharArrayReader(b1);
		int d1 = car.read();
		System.out.println(d1);
		System.out.println((char) d1);
		char[] b2 = new char[10];
		car.read(b2);
		System.out.println(new String(b2));

	}

}
