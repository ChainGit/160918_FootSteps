package com.tgjdbc.day01;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.junit.Test;

/**
 * JUnit�Ļ���ʹ��
 * 
 * @author Chain
 *
 */
public class JUnit_TgJDBC {

	@Test
	public void test() throws Exception {
		getConnection();
	}

	public Connection getConnection() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileReader("mysql.properties"));

		String driverClass = prop.getProperty("driver");
		String jdbcUrl = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password");

		// ��������(������ʱ��ִ��static�����)
		Class.forName(driverClass);

		Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);

		System.out.println(conn);

		return conn;
	}

}
