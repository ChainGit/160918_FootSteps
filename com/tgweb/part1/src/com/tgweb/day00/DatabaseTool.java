package com.tgweb.day00;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库操作的常用方法(只是一个粗糙的工具)
 * 
 * @author Chain
 *
 */
public class DatabaseTool {

	public static Connection getConnection() {
		Properties prop = new Properties();
		try {
			// prop.load(new FileReader("mysql.properties"));
			InputStream is = DatabaseTool.class.getClassLoader().getResourceAsStream("mysql.properties");
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String driverClass = prop.getProperty("driver");
		String jdbcUrl = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password");

		// 加载驱动(加载类时会执行static代码块)
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("create a new connection.");

		return conn;
	}

	public static void close(ResultSet res, Statement stat, Connection conn) {
		try {
			if (res != null)
				res.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					try {
						stat.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}

		System.out.println("close resource done.");
	}
}
