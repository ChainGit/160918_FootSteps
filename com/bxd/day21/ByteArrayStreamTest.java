package com.bxd.day21;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

//不涉及底层IO，只是将byte型数组封装成流，存储在内存中，其他数据类型转为byte数组即可使用
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
