package com.tgjdbc.day06;

import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0Á¬½Ó³Ø
 * 
 * @author Chain
 *
 */
public class C3P0Test {

	public static void main(String[] args) throws Exception {
		test();
	}

	private static void test() throws Exception {
		ComboPooledDataSource spds = new ComboPooledDataSource("tg-jdbc-c3p0-config");
		Connection conn = spds.getConnection();
		System.out.println(conn);
		conn.close();
	}
}
