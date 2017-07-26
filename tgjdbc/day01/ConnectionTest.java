package com.tgjdbc.day01;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 使用JDBC连接数据库
 * 
 * @author Chain
 *
 */
public class ConnectionTest {

	public static void main(String[] args) throws Exception {
		// new FirstTest().test1();
		// new FirstTest().test2();
		new ConnectionTest().test3();
	}

	// 普通方式(指定为com.mysql.jdbc.Driver且url固定)
	public void test1() throws Exception {
		java.sql.Driver driver = new com.mysql.jdbc.Driver();

		String url = "jdbc:mysql://192.168.56.2:3306/test";

		Properties prop = new Properties();
		prop.load(new FileReader("mysql.properties"));

		Connection conn = driver.connect(url, prop);
		System.out.println(conn);
	}

	// 反射方式(更加通用,使用Driver类)
	public void test2() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileReader("mysql.properties"));

		String driverClass = prop.getProperty("driver");
		String jdbcUrl = prop.getProperty("url");

		Driver driver = (Driver) Class.forName(driverClass).newInstance();

		Connection conn = driver.connect(jdbcUrl, prop);
		System.out.println(conn);
	}

	// 使用DriverManager(可以管理多个驱动程序)
	public void test3() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileReader("mysql.properties"));

		String driverClass = prop.getProperty("driver");
		String jdbcUrl = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password");

		// 加载驱动(加载类时会执行static代码块)
		Class.forName(driverClass);

		Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);

		System.out.println(conn);

	}

}
