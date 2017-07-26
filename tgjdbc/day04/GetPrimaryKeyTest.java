package com.tgjdbc.day04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;
import com.tgjdbc.day00.DatabaseTool;

public class GetPrimaryKeyTest {

	public static void main(String[] args) {
		test();
	}

	private static void test() {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;

		try {
			conn = DatabaseTool.getConnection();

			String sql = "insert into stu_info (stu_name,stu_age,stu_score) values (?,?,?)";
			stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stat.setString(1, "Сǿ");
			stat.setInt(2, 24);
			stat.setInt(3, 88);

			stat.executeUpdate();

			res = stat.getGeneratedKeys();

			while (res.next())
				System.out.println("primary_key=" + res.getObject(1));

		} catch (Exception e) {

		} finally {
			DatabaseTool.close(res, stat, conn);
		}
	}
}
