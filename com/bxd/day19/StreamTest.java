package com.bxd.day19;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 字节流和字符流
 * 
 * 关键词：File Buffered Stream
 * 
 * 字符流：主要用来处理字符
 * 
 * 相关类：Reader Writer FileReader FileWriter BufferedReader BufferedWriter ...
 * 
 * 字节流：主要用来处理字节
 * 
 * 相关类：InputStream OutputStream FileInputStream FileOutputStream
 * BufferedOutputStream BufferedInputStream ...
 * 
 * 桥梁：InputStreamReader OutputStreamWriter ...
 * 
 * @author Chain
 *
 */
public class StreamTest {

	public static void main(String[] args) {
		// test1();
		// test2();
		// test3();
		// test4();

		// IOTest.deleteAll();

		// test5();
		// test6();
		// test7();
		// test8();
		test9();
	}

	public static void test2() {
		// TODO Auto-generated method stub
		try {
			FileInputStream fis = new FileInputStream("demo5.txt");
			byte[] bt = new byte[1024];
			fis.read(bt);
			System.out.println(new String(bt));
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void test1() {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fos = new FileOutputStream("demo5.txt");
			// fos.write(new byte[] { 'a', 'b', 'c' });
			fos.write(new byte[] { 'a', 'b', 'c', '\r', '\n' });
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void test3() {
		try {
			FileInputStream fis = new FileInputStream("1.jpg");
			FileOutputStream fos = new FileOutputStream("1_1.jpg");
			byte[] bt = new byte[1024];
			while (fis.read(bt) != -1) {// 注意返回的是int型
				fos.write(bt);// write会取int的低8位
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void test4() {
		try {
			BufferedInputStream bufis = new BufferedInputStream(new FileInputStream("1.jpg"));
			BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream("1_2.jpg"));
			int p = -1;// 为什么read方法返回是int，而不是字节byte?
						// 因为若数据中存在11111111的情况会使得返回的为-1，而int型前3个字节补0
			while ((p = bufis.read()) != -1) {
				bufos.write(p);
			}
			bufis.close();
			bufos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void test5() {
		try {
			InputStream in = System.in;
			int p = -1;
			while ((p = in.read()) != -1) {
				if (p == '#')
					break;
				System.out.println(p);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void test6() {
		try {
			InputStream in = System.in;
			StringBuilder sb = new StringBuilder();
			while (true) {
				int p = -1;
				String str = null;
				while ((p = in.read()) != -1) {
					if (p == '\r')
						continue;
					else if (p == '\n') {
						str = sb.toString();
						break;
					}
					sb.append((char) p);
				}
				if (str != null && str.equals("over"))
					break;
				System.out.println(str);
				sb.delete(0, sb.length());
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void test7() {
		InputStream in = System.in;
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader bufr = new BufferedReader(isr);

		String line = null;

		try {
			while ((line = bufr.readLine()) != null)
				if (!line.equals("over"))
					System.out.println(line);
				else
					break;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (isr != null)
					isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (bufr != null)
					bufr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void test8() {
		InputStream in = System.in;
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader bufr = new BufferedReader(isr);

		OutputStream out = System.out;
		OutputStreamWriter osw = new OutputStreamWriter(out);
		BufferedWriter bufw = new BufferedWriter(osw);

		String line = null;
		try {
			while ((line = bufr.readLine()) != null) {
				if (!line.equals("over")) {
					bufw.write(line);
					bufw.newLine();
					bufw.flush();
				} else
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				isr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bufw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				osw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void test9() {
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(System.out));

		String s = null;
		try {
			while ((s = bufr.readLine()) != null)
				if (!s.equals("over")) {
					bufw.write(s);
					bufw.newLine();
					bufw.flush();
				} else
					break;

		} catch (Exception e) {
			try {
				bufr.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				bufw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
