package com.tgjdbc.day06;

import java.io.FileReader;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import com.tgjdbc.day00.DatabaseTool;

public class DBCPTest {

	public static void main(String[] args) {
		try {
			// test1();
			test2();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

	private static void test2() throws Exception {
		DataSource ds = null;

		Properties prop = new Properties();
		prop.load(new FileReader("mysql.properties"));
		// InputStream is =
		// DBCPTest.class.getClassLoader().getResourceAsStream("mysql.properties");
		// prop.load(is);
		// is.close();
		ds = BasicDataSourceFactory.createDataSource(prop);

		for (int i = 0; i < 10; i++) {
			new Thread(new MyDbcpThread(ds)).start();
			Thread.sleep(1000);
		}

	}

	private static void test1() throws Exception {
		BasicDataSource bds = null;
		Connection conn = null;
		bds = new BasicDataSource();

		Properties prop = new Properties();
		prop.load(new FileReader("mysql.properties"));

		String driverClass = prop.getProperty("driver");
		String jdbcUrl = prop.getProperty("url");
		String username = prop.getProperty("user");
		String password = prop.getProperty("password");

		bds.setDriverClassName(driverClass);
		bds.setUrl(jdbcUrl);
		bds.setUsername(username);
		bds.setPassword(password);

		bds.setMaxActive(20);
		bds.setMaxIdle(50);
		bds.setMinIdle(5);
		bds.setMaxWait(1000 * 10);

		conn = bds.getConnection();

		System.out.println(conn);
	}

}

class MyDbcpThread implements Runnable {

	DataSource ds = null;

	public MyDbcpThread(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public void run() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			System.out.println(conn);
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseTool.close(null, null, conn);
		}
	}

}
