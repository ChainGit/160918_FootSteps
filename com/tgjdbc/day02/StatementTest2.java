package com.tgjdbc.day02;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.tgjdbc.day00.DatabaseTool;

/**
 * StatementTest 2
 * 
 * 使用PreparedStatement
 * 
 * @author Chain
 *
 */
public class StatementTest2 {

	public static void main(String[] args) {
		test();
	}

	private static void test() {
		Connection conn = null;
		PreparedStatement stat = null;

		try {
			conn = DatabaseTool.getConnection();
			String sql = "insert into stu_info (name,age) values (?,?)";
			stat = conn.prepareStatement(sql);
			stat.setString(1, "小明");
			stat.setInt(2, 20);
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseTool.close(null, stat, conn);
		}
	}
}
