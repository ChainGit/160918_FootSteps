package com.tgweb.basic.ajax.day07;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBManager {

	private static ComboPooledDataSource cpds;

	private Connection conn;

	static {
		cpds = new ComboPooledDataSource("test");
	}

	public Connection getConnection() throws SQLException {
		if (conn == null)
			conn = cpds.getConnection();
		return conn;
	}

	public void close() throws SQLException {
		if (conn != null)
			conn.close();
	}

}
