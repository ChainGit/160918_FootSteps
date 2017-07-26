package com.bxd.day27;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ���䣺��չ��(���Ե�PCI�ӿ�Ϊ��)
 * 
 * @author Chain
 *
 */
public class ReflactTest2 {

	public static void main(String[] args) {
		Properties p = new Properties();

		try {
			p.load(new FileInputStream("src/com/bxd/day27/pcis.properties"));
		} catch (IOException e) {
			e.printStackTrace();

		}

		MotherBoard m = new MotherBoard(p);

		try {
			m.work();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
