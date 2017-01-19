package com.bxd.day18;

import java.util.Properties;

public class SystemTest {

	public static void main(String[] args) throws Exception {
		Properties prop = System.getProperties();
		System.setProperty("MYKEY", "YESYES");
		for (Object o : prop.keySet()) {
			System.out.println(o + " : " + prop.get(o));
		}
	}
}
