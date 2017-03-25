package com.tgjdbc.day04;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import com.tgjdbc.day00.DatabaseTool;

/**
 * 数据库的元数据
 * 
 * @author Chain
 *
 */
public class DatabaseMetaDataTest {

	public static void main(String[] args) {
		test();
	}

	private static void test() {
		Connection conn = null;
		ResultSet res = null;

		try {
			conn = DatabaseTool.getConnection();
			DatabaseMetaData dbmd = conn.getMetaData();

			System.out.println(dbmd.getDatabaseProductName());
			System.out.println(dbmd.getDatabaseProductVersion());

			res = dbmd.getCatalogs();
			while (res.next())
				System.out.println(res.getString(1));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseTool.close(res, null, conn);
		}
	}
}
