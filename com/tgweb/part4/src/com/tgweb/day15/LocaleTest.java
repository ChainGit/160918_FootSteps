package com.tgweb.day15;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class LocaleTest {

	@Test
	public void testLocale() {
		Locale locale = Locale.CHINA;
		System.out.println(locale.getDisplayName());
		System.out.println(locale.getLanguage());

		locale = new Locale("en", "us");
		System.out.println(locale.getDisplayName());
		System.out.println(locale.getLanguage());
	}

	@Test
	public void testDateFormat() {
		Locale locale = Locale.CHINA;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM-dd hh:mm:ss", locale);
		String str = "2016/11-23 13:33:12";
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date);

		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM, locale);
		str = df.format(date);
		System.out.println(str);
	}

	@Test
	public void testNumberFormat() {
		Locale locale = Locale.CHINA;
		NumberFormat nf1 = NumberFormat.getNumberInstance(locale);
		NumberFormat nf2 = NumberFormat.getCurrencyInstance(locale);
		NumberFormat nf3 = NumberFormat.getPercentInstance(locale);

		double d = 123.456d;
		String s = "£¤123.456";

		try {
			// System.out.println((Double) nf1.parse(s));
			System.out.println((Double) nf2.parse(s));
			// System.out.println((Double) nf3.parse(s));

			System.out.println(nf1.format(d));
			System.out.println(nf2.format(d));
			System.out.println(nf3.format(d));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void testMessageFormat(String base, Locale locale) {
		String name = "Ð¡Ã÷";
		double salary = 12345.67d;
		String sSalary = NumberFormat.getCurrencyInstance(locale).format(salary);
		Date date = new Date();
		String sDate = SimpleDateFormat.getDateInstance(DateFormat.LONG, locale).format(date);

		String res = MessageFormat.format(base, name, sSalary, sDate);
		System.out.println(res);
	}

	@Test
	public void testResourceBundle() {
		// Locale locale = Locale.CHINA;
		Locale locale = Locale.US;
		String base = "{0}";

		ResourceBundle rb = ResourceBundle.getBundle("lang", locale);

		String nameConfig = rb.getString("name");
		String sarlayConfig = rb.getString("salary");
		String dateConfig = rb.getString("date");

		String nameLocale = MessageFormat.format(base, nameConfig);
		String sarlayLocale = MessageFormat.format(base, sarlayConfig);
		String dateLocale = MessageFormat.format(base, dateConfig);

		base = nameLocale + "£º{0} \n" + sarlayLocale + "£º{1} \n" + dateLocale + "£º{2} \n";
		testMessageFormat(base, locale);
	}

}
