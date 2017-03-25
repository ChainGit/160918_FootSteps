package com.tgjdbc.day01;

import java.sql.Connection;
import java.sql.Statement;

import com.tgjdbc.day00.DatabaseTool;

/**
 * 
 * �������ݿ����
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

	// ��ѯ����(ResultSet),ʹ��DatabaseTool
	private static void test3() {
		DatabaseTool.query("select * from stu_info");
	}

	// ʹ��DatabaseTool
	@SuppressWarnings("unused")
	private static void test2() {
		DatabaseTool.update("insert into stu_info (name,age) values ('С��',22),('С��',21),('С��',20)");
	}

	// �����ľ����������(Update)
	@SuppressWarnings("unused")
	private static void test1() {
		Connection conn = null;
		Statement stat = null;

		String sql = "insert into stu_info (name,age) values ('С��',22),('С��',21)";
		// String sql = "delete from stu_info where id = 2";

		try {
			// ������ݿ������
			conn = DatabaseTool.getConnection();
			// ����һ��Statement����
			stat = conn.createStatement();
			// ִ��Update����,���Զ�Ӧinsert/update/delete
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
