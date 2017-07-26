package com.tgjdbc.day01;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * ʹ��JDBC�������ݿ�
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

	// ��ͨ��ʽ(ָ��Ϊcom.mysql.jdbc.Driver��url�̶�)
	public void test1() throws Exception {
		java.sql.Driver driver = new com.mysql.jdbc.Driver();

		String url = "jdbc:mysql://192.168.56.2:3306/test";

		Properties prop = new Properties();
		prop.load(new FileReader("mysql.properties"));

		Connection conn = driver.connect(url, prop);
		System.out.println(conn);
	}

	// ���䷽ʽ(����ͨ��,ʹ��Driver��)
	public void test2() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileReader("mysql.properties"));

		String driverClass = prop.getProperty("driver");
		String jdbcUrl = prop.getProperty("url");

		Driver driver = (Driver) Class.forName(driverClass).newInstance();

		Connection conn = driver.connect(jdbcUrl, prop);
		System.out.println(conn);
	}

	// ʹ��DriverManager(���Թ�������������)
	public void test3() throws Exception {
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

	}

}
