package com.bxd.day21;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

//���漰�ײ�IO��ֻ�ǽ�byte�������װ�������洢���ڴ��У�������������תΪbyte���鼴��ʹ��
public class ByteArrayStreamTest {

	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		// TODO Auto-generated method stub
		ByteArrayInputStream bais = new ByteArrayInputStream("ABCDEF".getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int by = -1;
		while ((by = bais.read()) != -1)
			baos.write(by);

		System.out.println(baos.toString());
	}

}
