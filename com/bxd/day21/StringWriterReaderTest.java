package com.bxd.day21;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

//内部封装了一个StringBuffer
public class StringWriterReaderTest {

	public static void main(String[] args) throws Exception {
		test1();
	}

	private static void test1() throws Exception {
		// TODO Auto-generated method stub
		StringWriter strw = new StringWriter();
		strw.append((char) 97);
		strw.append('A');
		strw.write("TEST");
		String str = strw.toString();
		StringBuffer sb = strw.getBuffer();
		System.out.println(sb.toString());

		try {
			strw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 空方法...

		StringReader strr = new StringReader(str);
		int d1 = strr.read();
		System.out.println(d1);
		strr.skip(1);
		char[] buf = new char[10];
		strr.read(buf);
		System.out.println(buf);
	}
}
