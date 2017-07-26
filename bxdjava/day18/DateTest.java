package com.bxd.day18;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		Date d = new Date();
		System.out.println(d);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(sdf.format(d));

		Calendar c = Calendar.getInstance();
		// System.out.println(c);
		c.set(2010, 2, 1);
		c.add(Calendar.YEAR, 1);
		System.out.println(c.get(Calendar.YEAR));

	}
}
