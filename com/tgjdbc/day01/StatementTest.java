package com.tgjdbc.day01;

import java.sql.Connection;
import java.sql.Statement;

import com.tgjdbc.day00.DatabaseTool;

/**
 * 
 * 操作数据库语句
 * 
 * @author Chain
 *
 */
public class StatementTest {

	public static void main(String[] args) {
		// test1();
		// test2();
		test3();
	}

	// 查询操作(ResultSet),使用DatabaseTool
	private static void test3() {
		DatabaseTool.query("select * from stu_info");
	}

	// 使用DatabaseTool
	@SuppressWarnings("unused")
	private static void test2() {
		DatabaseTool.update("insert into stu_info (name,age) values ('小明',22),('小芳',21),('小刚',20)");
	}

	// 完整的经典操作方法(Update)
	@SuppressWarnings("unused")
	private static void test1() {
		Connection conn = null;
		Statement stat = null;

		String sql = "insert into stu_info (name,age) values ('小明',22),('小芳',21)";
		// String sql = "delete from stu_info where id = 2";

		try {
			// 获得数据库的连接
			conn = DatabaseTool.getConnection();
			// 创建一个Statement对象
			stat = conn.createStatement();
			// 执行Update操作,可以对应insert/update/delete
			stat.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (conn != null)
						try {
							conn.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
		}

		System.out.println("done.");
	}

}
